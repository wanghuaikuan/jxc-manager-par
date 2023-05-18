package com.lzj.admin.service;

import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.pojo.GoodsType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类别表 服务类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:43
 */
public interface IGoodsTypeService extends IService<GoodsType> {

    List<TreeDto> queryAllGoodsTypes(Integer typeId);

    List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId);

    Map<String, Object> goodsTypeList();

    void saveGoodsType(GoodsType goodsType);

    void deleteGoodsType(Integer id);
}
