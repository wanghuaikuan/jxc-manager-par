package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.OverflowListGoods;
import com.lzj.admin.mapper.OverflowListGoodsMapper;
import com.lzj.admin.pojo.OverflowListGoods;
import com.lzj.admin.query.OverflowListGoodQuery;
import com.lzj.admin.service.IOverflowListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.PageResultUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 报溢单商品表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
@Service
public class OverflowListGoodsServiceImpl extends ServiceImpl<OverflowListGoodsMapper, OverflowListGoods> implements IOverflowListGoodsService {

    @Override
    public Map<String, Object> overflowListGoods(OverflowListGoodQuery overflowListGoodQuery) {
        IPage<OverflowListGoods> page=new Page<>(overflowListGoodQuery.getPage(),overflowListGoodQuery.getLimit());
        QueryWrapper<OverflowListGoods> queryWrapper=new QueryWrapper<>();
        if(null !=overflowListGoodQuery.getOverflowListId()){
            queryWrapper.eq("overflow_list_id",overflowListGoodQuery.getOverflowListId());
        }
        page=this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }
}
