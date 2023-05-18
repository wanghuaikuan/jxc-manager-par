package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.Goods;
import com.lzj.admin.mapper.GoodsMapper;
import com.lzj.admin.query.GoodsQuery;
import com.lzj.admin.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.PageResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:04
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    private IGoodsTypeService goodsTypeService;
    @Resource
    private ISaleListGoodsService saleListGoodsService;
    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;
    @Override
    public Map<String, Object> goodsList(GoodsQuery goodsQuery) {
        IPage<Goods> page=new Page<>(goodsQuery.getPage(),goodsQuery.getLimit());
        if(null!=goodsQuery.getTypeId()){
            goodsQuery.setTypeIds(goodsTypeService.queryAllSubTypeIdsByTypeId(goodsQuery.getTypeId()));
        }
       page=this.baseMapper.queryGoodsByParams(page,goodsQuery);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveGoods(Goods goods) {
        /**
         *  1.参数的校验
         *   商品名，类别，单位非空
         *  2.设置商品的唯一编码
         *  3.设置默认库存为0，商品状态0，采购价格0 is_del 0
         */
        AssertUtil.isTrue(StringUtils.isBlank(goods.getName()),"请添加商品名称！");
        AssertUtil.isTrue(null==goods.getTypeId(),"请指定商品类别！");
        AssertUtil.isTrue(StringUtils.isBlank(goods.getUnit()),"请指定商品单位！");
        goods.setCode(genGoodsCode());
        goods.setIsDel(0);
        goods.setState(0);
        goods.setLastPurchasingPrice(0.0);
        goods.setInventoryQuantity(0);
        AssertUtil.isTrue(!(this.save(goods)),"记录添加失败！");
    }

    @Override
    public String genGoodsCode() {
        String maxGoodsCode =this.baseMapper.selectOne(new QueryWrapper<Goods>().select("max(code) as code")).getCode();
        if(StringUtils.isNotEmpty(maxGoodsCode)){
            int code=Integer.parseInt(maxGoodsCode)+1;
            StringBuilder codes= new StringBuilder(Integer.toString(code));
            int length=codes.length();
            for (int i = 4; i >length ; i--) {
                codes.insert(0, "0");
            }
            return codes.toString();

        }else{
            return "0001";
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateGoods(Goods goods) {
        /**
         * 1.参数校验
         * 2.
         */
        AssertUtil.isTrue(StringUtils.isBlank(goods.getName()),"请添加商品名称！");
        AssertUtil.isTrue(null==goods.getTypeId(),"请指定商品类别！");
        AssertUtil.isTrue(StringUtils.isBlank(goods.getUnit()),"请指定商品单位！");
        AssertUtil.isTrue(!(this.updateById(goods)),"商品更新失败！");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteGoods(Integer id) {
        /**
         * 1.记录必须存在
         * 2.不可删除跳进
         *    如果商品已经期初入库，不可删除
         *    商品已经发生单据，不能删除
         */
        Goods temp=this.getById(id);
        AssertUtil.isTrue(null==temp,"待删除的商品不存在！");
        assert temp != null;
        AssertUtil.isTrue(temp.getState()==1,"该商品已经期初入库，不能删除！");
        AssertUtil.isTrue(temp.getState()==2,"该商品已经发生单据，不能删除！");
        temp.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(temp)),"商品删除失败！");

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateStock(Goods goods) {
        /**
         * 库存量>0
         *
         */
        Goods temp=this.getById(goods.getId());
        AssertUtil.isTrue(null==temp,"待更新的商品不存在！");
        AssertUtil.isTrue(goods.getInventoryQuantity()<=0,"库存量必须大于0");
        AssertUtil.isTrue(goods.getPurchasingPrice()<=0,"成本价必须大于0");
        AssertUtil.isTrue(!(this.updateById(goods)),"商品更新失败！");

    }

    @Override
    public void deleteStock(Integer id) {
        Goods temp=this.getById(id);
        AssertUtil.isTrue(null==temp,"待删除的商品不存在！");
        AssertUtil.isTrue(temp.getState()==2,"该商品已经发生单据，不能删除！");
        temp.setInventoryQuantity(0);
        AssertUtil.isTrue(!(this.updateById(temp)),"商品删除失败！");
    }

    @Override
    public Goods getGoodsInfoById(Integer gid) {
        return this.baseMapper.getGoodsInfoById( gid);
    }

    @Override
    public Map<String, Object> stockList(GoodsQuery goodsQuery) {
        IPage<Goods> page=new Page<>(goodsQuery.getPage(),goodsQuery.getLimit());
        if(null!=goodsQuery.getTypeId()){
            goodsQuery.setTypeIds(goodsTypeService.queryAllSubTypeIdsByTypeId(goodsQuery.getTypeId()));
        }
        page=this.baseMapper.queryGoodsByParams(page,goodsQuery);
        List<Goods> goodsList=page.getRecords();
        goodsList.forEach(g->
                g.setSaleTotal(saleListGoodsService.getSaleTotalByGoodsId(
                        g.getId()-customerReturnListGoodsService.getReturnTotalByGoodsId(g.getId()))
                )
        );
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }
}
