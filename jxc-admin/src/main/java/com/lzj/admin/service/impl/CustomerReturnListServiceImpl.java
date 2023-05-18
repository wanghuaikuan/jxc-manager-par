package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.*;
import com.lzj.admin.mapper.CustomerReturnListMapper;
import com.lzj.admin.pojo.CustomerReturnList;
import com.lzj.admin.query.CustomerReturnListQuery;
import com.lzj.admin.service.ICustomerReturnListGoodsService;
import com.lzj.admin.service.ICustomerReturnListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.service.IGoodsService;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.DateUtil;
import com.lzj.admin.utils.PageResultUtils;
import com.lzj.admin.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户退货单表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:01
 */
@Service
public class CustomerReturnListServiceImpl extends ServiceImpl<CustomerReturnListMapper, CustomerReturnList> implements ICustomerReturnListService {

    @Resource
    private IGoodsService goodsService;
    @Resource
    private ICustomerReturnListGoodsService customerReturnListGoodsService;
    @Override
    public String getNextCustomerReturnNumber() {
        try{
            StringBuilder stringBuffer=new StringBuilder();
            stringBuffer.append("TH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            System.out.println(DateUtil.getCurrentDateStr());
            String purchaseNumber=this.baseMapper.getNextCustomerReturnNumber();
            if(null !=purchaseNumber){
                stringBuffer.append(StringUtil.formatCode(purchaseNumber));
            }else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void saveCustomerReturnList(CustomerReturnList customerReturnList, List<CustomerReturnListGoods> plgList) {
        AssertUtil.isTrue(plgList==null,"请确保已经选择商品！");
        AssertUtil.isTrue(!(this.save(customerReturnList)),"记录添加失败！");
        CustomerReturnList temp=this.getOne(new QueryWrapper<CustomerReturnList>().eq("customer_return_number",customerReturnList.getCustomerReturnNumber()));
        plgList.forEach(customerReturnList1 -> {
            customerReturnList1.setCustomerReturnListId(temp.getId());
            Goods goods=goodsService.getById(customerReturnList1.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()+customerReturnList1.getNum());
            goods.setLastPurchasingPrice(customerReturnList1.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(customerReturnListGoodsService.save(customerReturnList1)),"记录添加失败！");
        });
    }

    @Override
    public Map<String, Object> customerReturnList(CustomerReturnListQuery customerReturnListQuery) {
        IPage<CustomerReturnList> page=new Page<>(customerReturnListQuery.getPage(),customerReturnListQuery.getLimit());
        page=this.baseMapper.customerReturnList(page,customerReturnListQuery);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public void deleteCustomerReturnList(Integer id) {
        AssertUtil.isTrue(!(customerReturnListGoodsService.remove(new QueryWrapper<CustomerReturnListGoods>().eq("customer_return_list_id",id))),"记录删除失败！");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败！");
    }
}
