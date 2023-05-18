package com.lzj.admin.mapper;

import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品类别表 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:43
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<TreeDto> queryAllGoodsTypes();
}
