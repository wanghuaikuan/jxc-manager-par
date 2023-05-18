package com.lzj.admin.service;

import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.UserQuery;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:02:28
 */
public interface IUserService extends IService<User> {

    void updateUserInfo(User user);

    void updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword) ;

    Map<String, Object> userList(UserQuery userQuery);

    void saveUser(User user) ;

    void updateUser(User user);

    void deleteUser(Integer[] ids) ;

    User findUserByUserName(String name);
}
