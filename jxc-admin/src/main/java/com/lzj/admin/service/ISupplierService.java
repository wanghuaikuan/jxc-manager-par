package com.lzj.admin.service;

import com.lzj.admin.pojo.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.SupplierQuery;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-15 08:19:57
 */
public interface ISupplierService extends IService<Supplier> {

    Map<String, Object> supplierList(SupplierQuery userQuery);

    void saveSupplier(Supplier supplier);
    Supplier findSupplierByName(String name);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(Integer[] ids);
}
