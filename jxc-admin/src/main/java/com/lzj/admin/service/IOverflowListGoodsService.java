package com.lzj.admin.service;

import com.lzj.admin.pojo.OverflowListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.OverflowListGoodQuery;

import java.util.Map;

/**
 * <p>
 * 报溢单商品表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
public interface IOverflowListGoodsService extends IService<OverflowListGoods> {

    Map<String, Object> overflowListGoods(OverflowListGoodQuery overflowListGoodQuery);
}
