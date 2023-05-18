package com.lzj.admin.service;

import com.lzj.admin.pojo.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-14 07:05:35
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
