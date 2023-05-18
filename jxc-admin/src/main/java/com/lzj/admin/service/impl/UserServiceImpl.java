package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.pojo.User;
import com.lzj.admin.mapper.UserMapper;
import com.lzj.admin.pojo.UserRole;
import com.lzj.admin.query.UserQuery;
import com.lzj.admin.service.IUserRoleService;
import com.lzj.admin.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.PageResultUtils;
import com.lzj.admin.utils.StringUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:02:28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private IUserRoleService userRoleService;

    @Override
    public User findUserByUserName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("is_del",0).eq("user_name",userName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserInfo(User user)  {
        /**
         * 用户名
         *    非空
         *    唯一
         */
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUsername()),"用户名不能为空!");
        User temp = this.findUserByUserName(user.getUsername());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(user.getId())),"用户名已存在!");
        AssertUtil.isTrue(!(this.updateById(user)),"用户信息更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword)  {
        /**
         * 用户名非空 必须存在
         * 原始密码 新密码 确认密码 均不能为空
         * 原始密码必须正确
         * 新密码 与 确认密码必须一致  并且不能与原始密码相同
         */
        User user=null;
        user = this.findUserByUserName(userName);
        AssertUtil.isTrue(null==user,"用户不存在或未登录!");
        AssertUtil.isTrue(StringUtil.isEmpty(oldPassword),"请输入原始密码!");
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword),"请输入新密码!");
        AssertUtil.isTrue(StringUtil.isEmpty(confirmPassword),"请输入确认密码!");
        assert user != null;
        AssertUtil.isTrue(!(passwordEncoder.matches(oldPassword,user.getPassword())),"原始密码输入错误!");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)),"新密码输入不一致!");
        AssertUtil.isTrue(newPassword.equals(oldPassword),"新密码与原始密码不能一致!");
        user.setPassword(passwordEncoder.encode(newPassword));
        AssertUtil.isTrue(!(this.updateById(user)),"用户密码更新失败!");

    }

    @Override
    public Map<String, Object> userList(UserQuery userQuery) {
        IPage<User> page = new Page<User>(userQuery.getPage(),userQuery.getLimit());
        QueryWrapper<User> queryWrapper =new QueryWrapper<User>();
        queryWrapper.eq("is_del",0);
        if(StringUtils.isNotBlank(userQuery.getUserName())){
            queryWrapper.like("user_name",userQuery.getUserName());
        }
        page =  this.baseMapper.selectPage(page,queryWrapper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveUser(User user) {
        /**
         * 用户名
         *   非空  不可重复
         * 用户密码默认123456
         * 用户默认有效
         */
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()),"用户名不能为空!");
        AssertUtil.isTrue(null !=this.findUserByUserName(user.getUsername()),"用户名已存在!");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setIsDel(0);
        AssertUtil.isTrue(!(this.save(user)),"用户记录添加失败!");
        // 重新查询用户记录
        User temp =this.findUserByUserName(user.getUsername());
        /**
         * 给用户分配角色
         */
        relationUserRole(temp.getId(),user.getRoleIds());
    }

    private void relationUserRole(Integer userId, String roleIds) throws ParamsException {
        /**
         * 核心表  t_user_role
         * 添加时
         *如果角色存在则批量添加
         * 更新时
         *   如果存在 直接删除原来用户角色记录即可，重新添加新的用户角色
         *   如果不存在 直接执行添加即可
         *
         *  实现思路
         *  首先查询角色原始分配角色
         *      如果存在 直接删除原来用户角色记录即可，重新添加新的用户角色
         *           如果不在 直接执行添加即可
         */

        long count=  userRoleService.count(new QueryWrapper<UserRole>().eq("user_id",userId));
         if (count>0){

             AssertUtil.isTrue(!(userRoleService.remove(new QueryWrapper<UserRole>().eq("user_id",userId))),"用户角色分配失败！");
         }
         if(StringUtils.isNotBlank(roleIds)){
             List<UserRole> userRoles=new ArrayList<>();
             for(String s: roleIds.split(",")){
                 UserRole userRole=new UserRole();
                 userRole.setUserId(userId);
                 userRole.setRoleId(Integer.parseInt(s));
                 userRoles.add(userRole);
             }
             AssertUtil.isTrue(!(userRoleService.saveBatch(userRoles)),"用户角色分配失败！");
         }
    }

    @Override
    public void updateUser(User user)  {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()),"用户名不能为空!");
        User temp =this.findUserByUserName(user.getUsername());
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(user.getId())),"用户名已存在!");
        relationUserRole(user.getId(),user.getRoleIds());
        AssertUtil.isTrue(!(this.updateById(user)),"用户记录更新失败!");
    }

    @Override
    public void deleteUser(Integer[] ids) throws ParamsException {
        AssertUtil.isTrue(null == ids || ids.length==0,"请选择待删除的记录id!");
        List<User> users =new ArrayList<User>();
        assert ids != null;
        for (Integer id : ids) {
            User temp =this.getById(id);
            temp.setIsDel(1);
            users.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(users)),"用户记录删除失败!");
    }



}
