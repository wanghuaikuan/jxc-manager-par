package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.pojo.Role;
import com.lzj.admin.mapper.RoleMapper;
import com.lzj.admin.pojo.RoleMenu;
import com.lzj.admin.query.RoleQuery;
import com.lzj.admin.service.IRoleMenuService;
import com.lzj.admin.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.PageResultUtils;
import com.lzj.admin.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private IRoleMenuService roleMenuService;
    @Override
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        IPage<Role> page = new Page<Role>(roleQuery.getPage(),roleQuery.getLimit());
        QueryWrapper<Role> queryWrapper =new QueryWrapper<Role>();
        queryWrapper.eq("is_del",0);
        if(StringUtils.isNotBlank(roleQuery.getRoleName())){
            queryWrapper.like("name",roleQuery.getRoleName());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    public void updateRoleInfo(Role role)  {
        AssertUtil.isTrue(StringUtil.isEmpty(role.getName()),"用户名不能为空!");
        Role temp = this.findRoleByRoleName(role.getName());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(role.getId())),"用户名已存在!");
        AssertUtil.isTrue(!(this.updateById(role)),"角色信息更新失败!");
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveRole(Role role) {
        /**
         * 用户名
         *   非空  不可重复
         * 用户密码默认123456
         * 用户默认有效
         */
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入角色名!");
        AssertUtil.isTrue(null !=this.findRoleByRoleName(role.getName()),"角色名已存在!");
        role.setIsDel(0);
        AssertUtil.isTrue(!(this.save(role)),"角色记录添加失败!");
    }
    @Override
    public Role findRoleByRoleName(String name) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().eq("is_del",0).eq("name",name));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        AssertUtil.isTrue(null == id, "请选择待删除的记录id!");
        Role temp = this.getById(id);
        AssertUtil.isTrue(null == temp, "待删除记录不存在！");
        assert temp != null;
        temp.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(temp)), "角色记录删除失败!");
    }


    @Override
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return this.baseMapper.queryAllRoles(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addGrant(Integer roleId, Integer[] mids)  {
        /**
         * 1.参数校验
         *   roleId 非空 必须存在
         *   2.授权
         *   2.1 第一次授权  直接批量添加
         *   2.2如果存在原始权限  删除原始权限  然后添加新的权限
         *   如果不存在，直接批量添加即可
         *   合并2.1和2.2 原始权限不管是否存在， 先执行权限记录查询，如果存在，根据角色id直接删除，执行批量添加
         */

        Role role=this.getById(roleId);
        AssertUtil.isTrue(null==role,"待授权的角色记录不存在！");
        long count=roleMenuService.count(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
        if(count>0){
           AssertUtil.isTrue(!(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id",roleId))),"用户角色授权失败！");
        }
        if(null!=mids&&mids.length>0){
            List<RoleMenu> roleMenus=new ArrayList<>();
            for (Integer mid:
                 mids) {
                RoleMenu roleMenu=new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(mid);
                roleMenus.add(roleMenu);
            }
          AssertUtil.isTrue( !(roleMenuService.saveBatch(roleMenus)),"用户角色授权失败！");
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateRole(Role role){
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入角色名!");
        Role temp=this.findRoleByRoleName(role.getName());
        AssertUtil.isTrue(null !=temp&&!(temp.getId().equals(role.getId())),"角色名已存在!");
        role.setIsDel(0);
        AssertUtil.isTrue(!(this.updateById(role)),"角色更新失败!");
    }

}
