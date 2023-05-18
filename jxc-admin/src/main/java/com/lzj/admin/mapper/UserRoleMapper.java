package com.lzj.admin.mapper;

import com.lzj.admin.pojo.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-13 10:31:02
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> findRolesByUserName(String userName);
}
