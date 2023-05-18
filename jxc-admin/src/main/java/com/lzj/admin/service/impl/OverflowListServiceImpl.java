package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.*;
import com.lzj.admin.pojo.OverflowList;
import com.lzj.admin.mapper.OverflowListMapper;
import com.lzj.admin.query.OverflowListQuery;
import com.lzj.admin.service.IOverflowListGoodsService;
import com.lzj.admin.service.IGoodsService;
import com.lzj.admin.service.IOverflowListGoodsService;
import com.lzj.admin.service.IOverflowListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.DateUtil;
import com.lzj.admin.utils.PageResultUtils;
import com.lzj.admin.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
@Service
public class OverflowListServiceImpl extends ServiceImpl<OverflowListMapper, OverflowList> implements IOverflowListService {

   @Resource
   private IOverflowListGoodsService overflowListGoodsService;
    @Resource
    private IGoodsService goodsService;
    @Override
    public String getNextOverflowNumber() {
        try{
            StringBuilder stringBuffer=new StringBuilder();
            stringBuffer.append("OF");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String overflowNumber=this.baseMapper.getNextOverflowNumber();
            if(null !=overflowNumber){
                stringBuffer.append(StringUtil.formatCode(overflowNumber));
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
    public void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(overflowList)),"记录添加失败！");
        OverflowList temp=this.getOne(new QueryWrapper<OverflowList>().eq("overflow_number",overflowList.getOverflowNumber()));
        plgList.forEach(overflowList1 -> {
            overflowList1.setOverflowListId(temp.getId());
            Goods goods=goodsService.getById(overflowList1.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+overflowList1.getNum());
            goods.setLastPurchasingPrice(overflowList1.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(overflowListGoodsService.save(overflowList1)),"记录添加失败！");
        });
    }

    @Override
    public Map<String, Object> overflowList(OverflowListQuery overflowListQuery) {
        IPage<OverflowList> page=new Page<>(overflowListQuery.getPage(),overflowListQuery.getLimit());
        page=this.baseMapper.overflowList(page,overflowListQuery);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }
}
