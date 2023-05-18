package com.lzj.admin.service;

import com.lzj.admin.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.query.GoodsQuery;

import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:04
 */
public interface IGoodsService extends IService<Goods> {

    Map<String, Object> goodsList(GoodsQuery goodsQuery);

    void saveGoods(Goods goods);
    String genGoodsCode();

    void updateGoods(Goods goods);

    void deleteGoods(Integer id);

    void updateStock(Goods goods);

    void deleteStock(Integer id);

    Goods getGoodsInfoById(Integer gid);

    Map<String, Object> stockList(GoodsQuery goodsQuery);
}
