package com.lzj.admin.service;

import com.lzj.admin.pojo.OverflowList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.admin.pojo.OverflowListGoods;
import com.lzj.admin.query.OverflowListQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 报溢单表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:52:20
 */
public interface IOverflowListService extends IService<OverflowList> {

    String getNextOverflowNumber();

    void saveOverflowList(OverflowList overflowList, List<OverflowListGoods> plgList);

    Map<String, Object> overflowList(OverflowListQuery overflowListQuery);
}
