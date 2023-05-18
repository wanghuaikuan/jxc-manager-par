package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.DamageListGoods;
import com.lzj.admin.mapper.DamageListGoodsMapper;
import com.lzj.admin.pojo.DamageListGoods;
import com.lzj.admin.query.DamageListGoodQuery;
import com.lzj.admin.service.IDamageListGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.PageResultUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 报损单商品表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:56
 */
@Service
public class DamageListGoodsServiceImpl extends ServiceImpl<DamageListGoodsMapper, DamageListGoods> implements IDamageListGoodsService {

    @Override
    public Map<String, Object> damageListGoods(DamageListGoodQuery damageListGoodQuery) {
        IPage<DamageListGoods> page=new Page<DamageListGoods>(damageListGoodQuery.getPage(),damageListGoodQuery.getLimit());
        QueryWrapper<DamageListGoods> queryWrapper=new QueryWrapper<>();
        if(null !=damageListGoodQuery.getDamageListId()){
            queryWrapper.eq("damage_list_id",damageListGoodQuery.getDamageListId());
        }
        page=this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }
}
