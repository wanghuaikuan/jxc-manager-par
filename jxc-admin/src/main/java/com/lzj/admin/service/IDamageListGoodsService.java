package com.lzj.admin.service;

import com.lzj.admin.pojo.DamageListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.DamageListGoodQuery;

import java.util.Map;

/**
 * <p>
 * 报损单商品表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 08:27:56
 */
public interface IDamageListGoodsService extends IService<DamageListGoods> {

    Map<String, Object> damageListGoods(DamageListGoodQuery damageListGoodQuery);
}
