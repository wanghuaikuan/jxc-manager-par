package com.lzj.admin.service;

import com.lzj.admin.pojo.SaleListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.SaleListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 销售单商品表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:59:14
 */
public interface ISaleListGoodsService extends IService<SaleListGoods> {

   Integer getSaleTotalByGoodsId(Integer id);

    Map<String, Object> saleGoodsList(SaleListGoodsQuery saleListGoodsQuery);



}
