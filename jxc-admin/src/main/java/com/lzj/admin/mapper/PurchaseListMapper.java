package com.lzj.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lzj.admin.model.CountResultModel;
import com.lzj.admin.pojo.PurchaseList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.admin.query.PurchaseListQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 进货单 Mapper 接口
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-17 02:59:48
 */
public interface PurchaseListMapper extends BaseMapper<PurchaseList> {

    String getNextPurchaseNumber();

    IPage<PurchaseList> purchaseList(IPage<PurchaseList> page, @Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);

    Long countPurchaseTotal(@Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);

    List<CountResultModel> countPurchaseList  (@Param("purchaseListQuery") PurchaseListQuery purchaseListQuery);
}
