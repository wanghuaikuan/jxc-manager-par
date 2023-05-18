package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.ReturnListGoods;
import com.lzj.admin.pojo.ReturnListGoods;
import com.lzj.admin.mapper.ReturnListGoodsMapper;
import com.lzj.admin.query.ReturnListGoodQuery;
import com.lzj.admin.query.ReturnListGoodQuery;
import com.lzj.admin.service.IReturnListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.PageResultUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 退货单商品表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 07:23:35
 */
@Service
public class ReturnListGoodsServiceImpl extends ServiceImpl<ReturnListGoodsMapper, ReturnListGoods> implements IReturnListGoodsService {

    @Override
    public Map<String, Object> returnListGoods(ReturnListGoodQuery returnListGoodQuery) {
        IPage<ReturnListGoods> page=new Page<>(returnListGoodQuery.getPage(),returnListGoodQuery.getLimit());
        QueryWrapper<ReturnListGoods> queryWrapper=new QueryWrapper<>();
        if(null !=returnListGoodQuery.getReturnListId()){
            queryWrapper.eq("return_list_id",returnListGoodQuery.getReturnListId());
        }
        page=this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());

    }
}
