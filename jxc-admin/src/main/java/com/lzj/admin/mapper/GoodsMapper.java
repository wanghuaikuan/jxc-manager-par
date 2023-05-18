package com.lzj.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzj.admin.pojo.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.admin.query.GoodsQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:04
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    IPage<Goods> queryGoodsByParams(IPage<Goods> page, @Param("goodsQuery") GoodsQuery goodsQuery);

    Goods getGoodsInfoById(Integer gid);
}
