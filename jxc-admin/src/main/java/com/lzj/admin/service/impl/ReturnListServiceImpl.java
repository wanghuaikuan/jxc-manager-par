package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.Goods;
import com.lzj.admin.pojo.ReturnListGoods;
import com.lzj.admin.pojo.ReturnList;
import com.lzj.admin.pojo.ReturnListGoods;

import com.lzj.admin.mapper.ReturnListMapper;

import com.lzj.admin.query.ReturnListQuery;

import com.lzj.admin.service.IGoodsService;
import com.lzj.admin.service.IReturnListGoodsService;
import com.lzj.admin.service.IReturnListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.DateUtil;
import com.lzj.admin.utils.PageResultUtils;
import com.lzj.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 退货单表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 07:02:37
 */
@Service
public class ReturnListServiceImpl extends ServiceImpl<ReturnListMapper, ReturnList> implements IReturnListService {
    @Resource
    IReturnListGoodsService returnListGoodsService;

    @Resource
    private IGoodsService goodsService;
    @Override
    public String getNextReturnNumber() {
        try {
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append("TH");
            stringBuffer.append(DateUtil.getCurrentDateStr());
            System.out.println(DateUtil.getCurrentDateStr());
            String returnNumber = this.baseMapper.getNextReturnNumber();
            if (null != returnNumber) {
                stringBuffer.append(StringUtil.formatCode(returnNumber));
            } else {
                stringBuffer.append("0001");
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveReturnList(ReturnList returnList, List<ReturnListGoods> rlgList) {
        AssertUtil.isTrue(!(this.save(returnList)),"记录添加失败!");
        ReturnList  temp = this.getOne(new QueryWrapper<ReturnList>().eq("return_number",returnList.getReturnNumber()));
        rlgList.forEach(rlg->{
            rlg.setReturnListId(temp.getId());
            Goods goods =goodsService.getById(rlg.getGoodsId());
            goods.setInventoryQuantity(goods.getInventoryQuantity()-rlg.getNum());
            goods.setState(2);
            goodsService.updateById(goods);

        });
        AssertUtil.isTrue(!(returnListGoodsService.saveBatch(rlgList)),"记录添加失败!");
    }

    @Override
    public Map<String, Object> returnList(ReturnListQuery returnListQuery) {
        IPage<ReturnList> page= new Page<>(returnListQuery.getPage(), returnListQuery.getLimit());
        page=this.baseMapper.returnList(page,returnListQuery);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteReturnList(Integer id) {
        /**
         * 1.进货单商品品记录删除
         * 2，进货单记录删除
         */
        AssertUtil.isTrue(!(returnListGoodsService.remove(new QueryWrapper<ReturnListGoods>().eq("return_list_id",id))),"记录删除失败！");
        AssertUtil.isTrue(!(this.removeById(id)),"记录删除失败！");
    }
}
