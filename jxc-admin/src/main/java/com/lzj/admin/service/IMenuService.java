package com.lzj.admin.service;

import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-12 09:49:13
 */
public interface IMenuService extends IService<Menu> {

    List<TreeDto> queryAllMenus(Integer roleId);

    Map<String, Object> menuList();

    void saveMenu(Menu menu);
    Menu findMenuByAclValue(String aclVal);
    Menu findMenuById(Integer id);
    Menu findMenuByGradeAndUrl(String url,Integer grade);
    Menu findMenuByNameAndGradeAndPId(String menuName,Integer grade,Integer pId);
    void updateMenu(Menu menu);

    void deleteMenuById(Integer id);
}
