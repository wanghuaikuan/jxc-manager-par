package com.lzj.admin.service;

import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.pojo.User;
import com.lzj.admin.query.RoleQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:27
 */
public interface IRoleService extends IService<Role> {

    Map<String, Object> roleList(RoleQuery roleQuery);

    void updateRoleInfo(Role role) throws ParamsException;

    void saveRole(Role role) throws ParamsException;

    void updateRole(Role role) throws ParamsException;

    Role findRoleByRoleName(String name);

    void deleteRole(Integer id) throws ParamsException;

    List<Map<String, Object>> queryAllRoles(Integer userId);

    void addGrant(Integer roleId, Integer[] mids) throws ParamsException;


}
