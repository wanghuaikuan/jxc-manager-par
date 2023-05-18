package com.lzj.admin.service;

import com.lzj.admin.pojo.DamageList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.pojo.DamageListGoods;
import com.lzj.admin.query.DamageListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报损单表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:56
 */
public interface IDamageListService extends IService<DamageList> {

    String getNextDamageNumber();

    void saveDamageList(DamageList damageList, List<DamageListGoods> plgList);

    Map<String, Object> damageList(DamageListQuery damageListQuery);
}
