package com.lzj.admin.service;

import com.lzj.admin.pojo.ReturnListGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.ReturnListGoodQuery;

import java.util.Map;

/**
 * <p>
 * 退货单商品表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 07:23:35
 */

public interface IReturnListGoodsService extends IService<ReturnListGoods> {

    Map<String, Object> returnListGoods(ReturnListGoodQuery returnListGoodQuery);
}
