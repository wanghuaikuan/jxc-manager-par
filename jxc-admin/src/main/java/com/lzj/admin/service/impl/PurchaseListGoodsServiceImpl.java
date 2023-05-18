package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.PurchaseList;
import com.lzj.admin.pojo.PurchaseListGoods;
import com.lzj.admin.mapper.PurchaseListGoodsMapper;
import com.lzj.admin.query.PurchaseListGoodQuery;
import com.lzj.admin.service.IPurchaseListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.PageResultUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-17 06:40:05
 */
@Service
public class PurchaseListGoodsServiceImpl extends ServiceImpl<PurchaseListGoodsMapper, PurchaseListGoods> implements IPurchaseListGoodsService {

    @Override
    public Map<String, Object> purchaseListGoods(PurchaseListGoodQuery purchaseListGoodQuery) {
        IPage<PurchaseListGoods> page=new Page<PurchaseListGoods>(purchaseListGoodQuery.getPage(),purchaseListGoodQuery.getLimit());
        QueryWrapper<PurchaseListGoods> queryWrapper=new QueryWrapper<>();
        if(null !=purchaseListGoodQuery.getPurchaseListId()){
            queryWrapper.eq("purchase_list_id",purchaseListGoodQuery.getPurchaseListId());
        }
        page=this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());

    }
}
