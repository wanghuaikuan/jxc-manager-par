package com.lzj.admin.mapper;
import com.lzj.admin.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Map<String, Object>> queryAllRoles(Integer userId);
}
