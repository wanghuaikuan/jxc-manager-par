package com.lzj.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzj.admin.pojo.DamageList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.admin.query.DamageListQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 报损单表 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:56
 */
public interface DamageListMapper extends BaseMapper<DamageList> {

    String getNextDamageNumber();

    IPage<DamageList> damageList(IPage<DamageList> page, @Param("damageListQuery") DamageListQuery damageListQuery);
}
