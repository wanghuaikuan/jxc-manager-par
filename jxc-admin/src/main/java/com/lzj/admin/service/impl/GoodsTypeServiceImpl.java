package com.lzj.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lzj.admin.dto.TreeDto;
import com.lzj.admin.pojo.GoodsType;
import com.lzj.admin.mapper.GoodsTypeMapper;
import com.lzj.admin.pojo.Menu;
import com.lzj.admin.service.IGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.PageResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品类别表 服务实现类
 * </p>
 *
 * @author 王怀宽
 * @since 2023-04-16 10:14:43
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {

    @Override
    public List<TreeDto> queryAllGoodsTypes(Integer typeId) {
        List<TreeDto> treeDtos= this.baseMapper.queryAllGoodsTypes();
        if(null!=typeId){
            for (TreeDto treeDto: treeDtos) {
                if(treeDto.getId().equals(typeId)){
                    treeDto.setChecked(true);
                    break;
                }
            }
        }
        return treeDtos;
    }

    @Override
    public List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId) {
        GoodsType goodsType=this.getById(typeId);
        if(goodsType.getPId()==-1){
            return this.list().stream().map(GoodsType::getId).collect(Collectors.toList());
        }
        List<Integer> result=new ArrayList<>();
        result.add(typeId);
        return getSubTypeIds(typeId,result);

    }

    @Override
    public Map<String, Object> goodsTypeList() {
        List<GoodsType> menus = this.list();
        return PageResultUtils.getResult((long) menus.size(),menus);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveGoodsType(GoodsType goodsType) {
        /**
         * 商品类别不能为空
         * 上级类别不能为空
         * 设置父类别
         */
        AssertUtil.isTrue(StringUtils.isBlank(goodsType.getName()),"商品姓名不能为空！");
        AssertUtil.isTrue(null==goodsType.getPId(),"请指定父亲节点！");
        goodsType.setState(0);
        AssertUtil.isTrue(!(this.save(goodsType)),"商品类别添加失败！");
        GoodsType parent=this.getById(goodsType.getPId());
        if(parent.getState()==0){
            parent.setState(1);
        }
        AssertUtil.isTrue(!(this.updateById(parent)),"商品类别添加失败！");

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteGoodsType(Integer id) {
        /**
         * 1.删除记录必须存在
         * 2.如果存在子类别不允许删除
         * 3.如果节点删除后，上级节点没有子节点，设置父节点
         *
         */
        GoodsType goodsType=this.getById(id);
        AssertUtil.isTrue(null==goodsType,"请指定商品类别！");
        long count=this.count(new QueryWrapper<GoodsType>().eq("p_id",id));
        AssertUtil.isTrue(count>0,"暂不支持级联删除！");

        count=this.count(new QueryWrapper<GoodsType>().eq("p_id",goodsType.getPId()));
       if(count==1){
           AssertUtil.isTrue(!(this.update(new UpdateWrapper<GoodsType>().set("state",0).eq("id",goodsType.getPId()))),"删除失败！");

       }
        AssertUtil.isTrue(!(this.removeById(goodsType)),"删除失败！");
    }

    private List<Integer> getSubTypeIds(Integer typeId, List<Integer> result) {
        List<GoodsType> goodsTypes=this.baseMapper.selectList(new QueryWrapper<GoodsType>().eq("p_id",typeId));
        if(CollectionUtils.isNotEmpty(goodsTypes)){
            goodsTypes.forEach(gt -> {
                result.add(gt.getId());
                getSubTypeIds(gt.getId(),result);
            });
        }
        return result;
    }
}
