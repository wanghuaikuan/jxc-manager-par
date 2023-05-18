package com.lzj.admin.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.model.SaleCount;
import com.lzj.admin.pojo.SaleList;
import com.lzj.admin.pojo.SaleListGoods;
import com.lzj.admin.query.SaleListQuery;
import com.lzj.admin.service.ISaleListService;
import com.lzj.admin.service.IUserService;
import com.lzj.admin.utils.DateUtil;
import com.lzj.admin.utils.MathUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 销售单表 前端控制器
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-18 10:16:30
 */
@Controller
@RequestMapping("/sale")
public class SaleListController {
    @Resource
    private ISaleListService saleListService;

    @Resource
    private IUserService userService;

    @RequestMapping("index")
    public String index(Model model) {
        model.addAttribute("saleNumber", saleListService.getNextSaleNumber());

        return "sale/sale";
    }

    @RequestMapping("save")
    @ResponseBody
    public RespBean save(SaleList saleList, String goodsJson, Principal principal) {
        String userName = principal.getName();
        saleList.setUserId(userService.findUserByUserName(userName).getId());
        List<SaleListGoods> plgList = new Gson().fromJson(goodsJson, new TypeToken<List<SaleListGoods>>() {
        }.getType());
        saleListService.saveSaleList(saleList, plgList);
        return RespBean.success("商品销售出货成功！");
    }

    @RequestMapping("searchPage")
    public String searchPage() {

        return "sale/sale_search";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> saleList(SaleListQuery saleListQuery) {
        return saleListService.saleList(saleListQuery);

    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean delete(Integer id) {
        saleListService.deleteSaleList(id);
        return RespBean.success("删除成功！");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean save(Integer id) {
        saleListService.updateSaleListState(id);
        return RespBean.success("结算成功！");
    }

    @RequestMapping("countSale")
    @ResponseBody
    public Map<String, Object> countSale(SaleListQuery saleListQuery) {
        return saleListService.countSale(saleListQuery);

    }

    /**
     * 日销售统计
     *
     * @param
     * @return
     */
    @RequestMapping("countSaleByDay")
    @ResponseBody
    public Map<String, Object> countSaleByDay(String begin, String end) {
        Map<String, Object> result = new HashMap<>();
        List<SaleCount> saleCounts = new ArrayList<>();
        List<Map<String, Object>> list = saleListService.countSaleByDay(begin, end);
        /**
         * 根据传入的时间段，生成日期列表
         */
        List<String> datas = DateUtil.getRangeDates(begin, end);

        for (String data : datas) {
            SaleCount saleCount = new SaleCount();
            saleCount.setDate(data);
            boolean flag = true;
            for (Map<String, Object> map : list) {
                String dd = map.get("saleDate").toString().substring(0, 10);
                if(data.equals(dd)) {
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    saleCount.setAmountProfit(MathUtil.format2Bit((saleCount.getAmountSale() - saleCount.getAmountCost())));
                    flag = false;
                }
            }
            if(flag){
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
                saleCount.setAmountProfit(0F);
            }
            saleCounts.add(saleCount);
        }
        result.put("count", saleCounts.size());
        result.put("data", saleCounts);
        result.put("code", 0);
        result.put("msg", "");
        return result;

    }
//    /**
//     * 日销售统计
//     *
//     * @param
//     * @return
//     */
    @RequestMapping("countSaleByMonth")
    @ResponseBody
    public Map<String, Object> countSaleByMonth(String begin, String end) {
        Map<String, Object> result = new HashMap<>();
        List<SaleCount> saleCounts = new ArrayList<>();
        List<Map<String, Object>> list = saleListService.countSaleByMonth(begin, end);
        /**
         * 根据传入的时间段，生成日期列表
         */
        List<String> datas = DateUtil.getRangeMonth(begin, end);

        for (String data : datas) {
            SaleCount saleCount = new SaleCount();
            saleCount.setDate(data);
            boolean flag = true;
            for (Map<String, Object> map : list) {
                String dd = map.get("saleDate").toString().substring(0, 7);
                if(data.equals(dd)) {
                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
                    saleCount.setAmountProfit(MathUtil.format2Bit((saleCount.getAmountSale() - saleCount.getAmountCost())));
                    flag = false;
                }
            }
            if(flag){
                saleCount.setAmountSale(0F);
                saleCount.setAmountCost(0F);
                saleCount.setAmountProfit(0F);
            }
            saleCounts.add(saleCount);
        }
        result.put("count", saleCounts.size());
        result.put("data", saleCounts);
        result.put("code", 0);
        result.put("msg", "");
        return result;

    }
//    @RequestMapping("countSaleByMonth")
//    @ResponseBody
//    public Map<String,Object> countSaleByMonth(String begin,String end){
//        Map<String,Object> result =new HashMap<>();
//        List<SaleCount> saleCounts =new ArrayList<>();
//        /**
//         * 2021-03  -  2021-03
//         */
//        List<Map<String,Object>> list = saleListService.countSaleByMonth(begin,end);
//        /**
//         * 根据传入的时间段 生成日期列表
//         */
//        List<String> datas = DateUtil.getRangeMonth(begin,end);
//        for (String data : datas) {
//            SaleCount saleCount =new SaleCount();
//            saleCount.setDate(data);
//            boolean flag =true;
//            for(Map<String,Object> map:list){
//                String dd = map.get("saleDate").toString().substring(0,7);
//                if(data.equals(dd)){
//                    saleCount.setAmountCost(MathUtil.format2Bit(Float.parseFloat(map.get("amountCost").toString())));
//                    saleCount.setAmountSale(MathUtil.format2Bit(Float.parseFloat(map.get("amountSale").toString())));
//                    saleCount.setAmountProfit(MathUtil.format2Bit(saleCount.getAmountSale()-saleCount.getAmountCost()));
//                    flag =false;
//                }
//            }
//            if(flag){
//                saleCount.setAmountProfit(0F);
//                saleCount.setAmountSale(0F);
//                saleCount.setAmountCost(0F);
//            }
//            saleCounts.add(saleCount);
//        }
//
//        result.put("count",saleCounts.size());
//        result.put("data",saleCounts);
//        result.put("code",0);
//        result.put("msg","");
//        return result;
//    }
//


}
