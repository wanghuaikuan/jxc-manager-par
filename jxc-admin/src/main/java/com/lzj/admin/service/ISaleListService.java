package com.lzj.admin.service;

import com.lzj.admin.pojo.SaleList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.pojo.SaleListGoods;
import com.lzj.admin.query.SaleListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:16:30
 */
public interface ISaleListService extends IService<SaleList> {

    String getNextSaleNumber();

    void saveSaleList(SaleList saleList, List<SaleListGoods> plgList);

    Map<String, Object> saleList(SaleListQuery saleListQuery);

    void deleteSaleList(Integer id);

    void updateSaleListState(Integer id);

    Map<String, Object> countSale(SaleListQuery saleListQuery);

    List<Map<String, Object>> countSaleByDay(String begin, String end);

    List<Map<String, Object>> countSaleByMonth(String begin, String end);
}
