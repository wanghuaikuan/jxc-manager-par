package com.lzj.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzj.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.admin.query.OverflowListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报溢单表 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
public interface OverflowListMapper extends BaseMapper<OverflowList> {

    String getNextOverflowNumber();

    IPage<OverflowList> overflowList(IPage<OverflowList> page, @Param("overflowListQuery") OverflowListQuery overflowListQuery);
}
