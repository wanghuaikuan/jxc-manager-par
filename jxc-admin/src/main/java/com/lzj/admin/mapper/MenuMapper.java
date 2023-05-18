package com.lzj.admin.mapper;

import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:13
 */
public interface MenuMapper extends BaseMapper<Menu> {

   List<TreeDto> queryAllMenus();
}
