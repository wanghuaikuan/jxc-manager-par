package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.*;
import com.lzj.admin.mapper.DamageListMapper;
import com.lzj.admin.pojo.DamageList;
import com.lzj.admin.query.DamageListQuery;
import com.lzj.admin.service.IDamageListGoodsService;
import com.lzj.admin.service.IDamageListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.service.IGoodsService;
import com.lzj.admin.service.IPurchaseListGoodsService;
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
 * 报损单表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:56
 */
@Service
public class DamageListServiceImpl extends ServiceImpl<DamageListMapper, DamageList> implements IDamageListService {

    @Resource
    private IGoodsService goodsService;
    @Resource
    private IDamageListGoodsService damageListGoodsService;
    @Override
    public String getNextDamageNumber() {
        try{
            StringBuilder stringBuffer=new StringBuilder();
            stringBuffer.append("BS");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            String damageNumber=this.baseMapper.getNextDamageNumber();
            if(null !=damageNumber){
                stringBuffer.append(StringUtil.formatCode(damageNumber));
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
    public void saveDamageList(DamageList damageList, List<DamageListGoods> plgList) {
        AssertUtil.isTrue(!(this.save(damageList)),"记录添加失败！");
        DamageList temp=this.getOne(new QueryWrapper<DamageList>().eq("damage_number",damageList.getDamageNumber()));
        plgList.forEach(damageList1 -> {
            damageList1.setDamageListId(temp.getId());
            Goods goods=goodsService.getById(damageList1.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-damageList1.getNum());
            goods.setLastPurchasingPrice(damageList1.getPrice());
            goods.setState(2);
            goodsService.updateById(goods);
            AssertUtil.isTrue(!(goodsService.updateById(goods)),"记录添加失败!");
            AssertUtil.isTrue(!(damageListGoodsService.save(damageList1)),"记录添加失败！");
        });
    }

    @Override
    public Map<String, Object> damageList(DamageListQuery damageListQuery) {
        IPage<DamageList>page=new Page<>(damageListQuery.getPage(),damageListQuery.getLimit());
        page=this.baseMapper.damageList(page,damageListQuery);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }
}
