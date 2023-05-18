package com.lzj.admin.service;

import com.lzj.admin.pojo.PurchaseListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.PurchaseListGoodQuery;

import java.util.Map;

/**
 * <p>
 * 进货单商品表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-17 06:40:05
 */
public interface IPurchaseListGoodsService extends IService<PurchaseListGoods> {

    Map<String, Object> purchaseListGoods(PurchaseListGoodQuery purchaseListGoodQuery);
}
