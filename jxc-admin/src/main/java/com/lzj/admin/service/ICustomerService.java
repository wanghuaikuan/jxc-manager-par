package com.lzj.admin.service;

import com.lzj.admin.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.pojo.Supplier;
import com.lzj.admin.query.CustomerQuery;
import com.lzj.admin.query.SupplierQuery;

import java.util.Map;

/**
 * <p>
 * 客户表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 09:51:53
 */
public interface ICustomerService extends IService<Customer> {
  

    Map<String, Object> customerList(CustomerQuery customerQuery);

    void saveCustomer(Customer customer);

    void deleteCustomer(Integer[] ids);

    void updateCustomer(Customer customer);
    Customer findCustomerByName(String name);
}
