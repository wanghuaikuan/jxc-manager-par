package com.lzj.admin.service;

import com.lzj.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.pojo.PurchaseListGoods;
import com.lzj.admin.query.PurchaseListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 进货单 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-17 02:59:48
 */
public interface IPurchaseListService extends IService<PurchaseList> {

    String getNextPurchaseNumber() throws Exception;

    void savePurchaseList(PurchaseList purchaseList, List<PurchaseListGoods> plgList);

    Map<String, Object> purchaseList(PurchaseListQuery purchaseListQuery);

    void deletePurchaseList(Integer id);

    void updatePurchaseListStates(Integer pid);

    Map<String, Object> countPurchase(PurchaseListQuery purchaseListQuery);
}
