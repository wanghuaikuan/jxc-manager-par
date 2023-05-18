package com.lzj.admin.service;

import com.lzj.admin.pojo.CustomerReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.CustomerReturnListGoodsQuery;

import java.util.Map;

/**
 * <p>
 * 客户退货单商品表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:01
 */
public interface ICustomerReturnListGoodsService extends IService<CustomerReturnListGoods> {

    Integer getReturnTotalByGoodsId(Integer id);

    Map<String, Object> customerReturnGoodsList(CustomerReturnListGoodsQuery customerReturnListGoodsQuery);
}
