package com.lzj.admin.service;

import com.lzj.admin.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-13 10:31:02
 */
public interface IUserRoleService extends IService<UserRole> {

    List<String> findRolesByUserName(String userName);
}
