package com.lzj.admin.service;

import com.lzj.admin.pojo.CustomerReturnList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.pojo.CustomerReturnListGoods;
import com.lzj.admin.query.CustomerReturnListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:01
 */
public interface ICustomerReturnListService extends IService<CustomerReturnList> {

    String getNextCustomerReturnNumber();

    void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> plgList);

    Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery);

    void deleteCustomerReturnList(Integer id);
}
