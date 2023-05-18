package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.Customer;
import com.lzj.admin.mapper.CustomerMapper;
import com.lzj.admin.pojo.Customer;
import com.lzj.admin.query.CustomerQuery;
import com.lzj.admin.query.CustomerQuery;
import com.lzj.admin.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.PageResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 09:51:53
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
    @Override
    public Map<String, Object> customerList(CustomerQuery customerQuery) {
        IPage<Customer> page=new Page<>(customerQuery.getPage(), customerQuery.getLimit());
        QueryWrapper<Customer> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_del",0);
        if(StringUtils.isNotBlank(customerQuery.getCustomerName())){
            queryWrapper.like("name",customerQuery.getCustomerName());
        }
        page=this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveCustomer(Customer customer) {
        /**
         * 客户名称非空，联系人，联系电话都非空
         * 名称不可重复
         * is_del为0
         */
        checkParams(customer.getName(),customer.getContact(),customer.getNumber());
        AssertUtil.isTrue(null!=this.findCustomerByName(customer.getName()),"客户已存在！");
        customer.setIsDel(0);
        AssertUtil.isTrue(!(this.save(customer)),"记录添加失败！");
    }

    @Override
    public Customer findCustomerByName(String name) {
        return this.getOne(new QueryWrapper<Customer>().eq("is_del",0).eq("name",name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateCustomer(Customer customer) {
        AssertUtil.isTrue(null == this.getById(customer.getId()),"请选择客户记录!");
        checkParams(customer.getName(),customer.getContact(),customer.getNumber());
        Customer temp = this.findCustomerByName(customer.getName());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(customer.getId())),"客户已存在!");
        AssertUtil.isTrue(!(this.updateById(customer)),"记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteCustomer(Integer[] ids) {
        /**
         * 1.确保客户存在
         *C
         */

        AssertUtil.isTrue(null==ids||ids.length==0,"请选择待删除的记录！");
        List<Customer> customerList=new ArrayList<>();
        for (Integer id:
                ids) {
            Customer temp=this.getById(id);
            temp.setIsDel(1);
            customerList.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(customerList)),"客户删除成功！");
    }

    private void checkParams(String name, String contact, String number) {
        AssertUtil.isTrue(StringUtils.isBlank(name),"请输入客户名称！");
        AssertUtil.isTrue(StringUtils.isBlank(contact),"请输入联系人！");
        AssertUtil.isTrue(StringUtils.isBlank(number),"请输入联系电话！");
    }
}
