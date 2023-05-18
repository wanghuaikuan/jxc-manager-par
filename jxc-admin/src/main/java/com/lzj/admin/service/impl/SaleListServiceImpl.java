package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.model.CountResultModel;
import com.lzj.admin.pojo.Goods;
import com.lzj.admin.pojo.SaleList;
import com.lzj.admin.pojo.SaleList;
import com.lzj.admin.mapper.SaleListMapper;
import com.lzj.admin.pojo.SaleListGoods;
import com.lzj.admin.query.SaleListQuery;
import com.lzj.admin.service.IGoodsService;
import com.lzj.admin.service.IGoodsTypeService;
import com.lzj.admin.service.ISaleListGoodsService;
import com.lzj.admin.service.ISaleListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.DateUtil;
import com.lzj.admin.utils.PageResultUtils;
import com.lzj.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:16:30
 */
@Service
public class SaleListServiceImpl extends ServiceImpl<SaleListMapper, SaleList> implements ISaleListService {

    @Resource
    private IGoodsTypeService goodsTypeService;
    @Resource
    private IGoodsService goodsService;
    @Resource
    private ISaleListGoodsService saleListGoodsService;
    @Override
    public String getNextSaleNumber()  {
        try{
            StringBuilder stringBuffer=new StringBuilder();
            stringBuffer.append("XS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            System.out.println(DateUtil.getCurrentDateStr());
            String saleNumber=this.baseMapper.getNextSaleNumber();
            if(null !=saleNumber){
                stringBuffer.append(StringUtil.formatCode(saleNumber));
            }else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }

    }

    @Override
    public void saveSaleList(SaleList saleList, List<SaleListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(saleList)),"记录添加失败！");
        SaleList temp=this.getOne(new QueryWrapper<SaleList>().eq("sale_number",saleList.getSaleNumber()));
        plgList.forEach(saleList1 -> {
            saleList1.setSaleListId(temp.getId());
            Goods goods=goodsService.getById(saleList1.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+saleList1.getNum());
            goods.setLastPurchasingPrice(saleList1.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(saleListGoodsService.save(saleList1)),"记录添加失败！");
        });

    }

    @Override
    public Map<String, Object> saleList(SaleListQuery saleListQuery) {
        IPage<SaleList> page=new Page<>(saleListQuery.getPage(),saleListQuery.getLimit());
        page=this.baseMapper.saleList(page,saleListQuery);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteSaleList(Integer id) {
        /**
         * 1.进货单商品品记录删除
         * 2，进货单记录删除
         */
        AssertUtil.isTrue(!(saleListGoodsService.remove(new QueryWrapper<SaleListGoods>().eq("sale_list_id",id))),"记录删除失败！");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateSaleListState(Integer id) {
        SaleList saleList=this.getById(id);
        AssertUtil.isTrue(null==saleList,"待结算的销售单记录不存在！");
        AssertUtil.isTrue(saleList.getState()==1,"该销售单已结算！");
        saleList.setState(1);
        AssertUtil.isTrue(!(this.updateById(saleList)),"销售单结算失败！");

    }

    @Override
    public Map<String, Object> countSale(SaleListQuery saleListQuery) {
        if(null!=saleListQuery.getTypeId()){
            List<Integer> typeIds=goodsTypeService.queryAllSubTypeIdsByTypeId(saleListQuery.getTypeId());
            saleListQuery.setTypeIds(typeIds);
        }

        /**
         * page
         * 1-->0
         * 2-->10
         * 3-->20
         */
        saleListQuery.setIndex((saleListQuery.getPage()-1)*saleListQuery.getLimit());
        Long count=this.baseMapper.countSaleTotal(saleListQuery);
        List<CountResultModel> list=this.baseMapper.countSaleList(saleListQuery);
        return PageResultUtils.getResult(count,list);
    }

    @Override
    public List<Map<String, Object>> countSaleByDay(String begin, String end) {
        return this.baseMapper.countSaleByDay(begin,end);
    }

    @Override
    public List<Map<String, Object>> countSaleByMonth(String begin, String end) {
        return this.baseMapper.countSaleByMonth(begin,end);
    }
}
