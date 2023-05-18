package com.lzj.admin.service.impl;

import com.lzj.admin.pojo.RoleMenu;
import com.lzj.admin.mapper.RoleMenuMapper;
import com.lzj.admin.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-14 07:05:35
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId) {
        return this.baseMapper.queryRoleHasAllMenusByRoleId( roleId);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return this.baseMapper. findAuthoritiesByRoleName( roleNames);
    }
}
