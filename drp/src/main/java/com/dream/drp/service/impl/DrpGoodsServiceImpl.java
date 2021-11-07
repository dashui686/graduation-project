package com.dream.drp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.drp.Exception.GoodsIsExist;
import com.dream.drp.domain.DrpGoods;
import com.dream.drp.domain.DrpGoodsWarehouse;
import com.dream.drp.domain.DrpWarehouse;
import com.dream.drp.mapper.DrpGoodsWarehouseMapper;
import com.dream.drp.service.DrpGoodsService;
import com.dream.drp.mapper.DrpGoodsMapper;
import com.dream.drp.service.DrpGoodsWarehouseService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class DrpGoodsServiceImpl extends ServiceImpl<DrpGoodsMapper, DrpGoods>
    implements DrpGoodsService{


    @Autowired
    DrpGoodsMapper drpGoodsMapper;
    @Autowired
    DrpGoodsWarehouseService goodsWarehouseService;

    @Override
    public AjaxResult select(DrpGoods drpGoods, Integer current, Integer size) {

        Page<DrpGoods> page = page(
                new Page<DrpGoods>(current, size),
                new QueryWrapper<DrpGoods>()
                        .eq(
                                drpGoods.getGoodsName()!= null && !drpGoods.getGoodsName().isEmpty(),
                                "GoodsName","%"+drpGoods.getGoodsName()+"%"
                        )
                        .eq(
                                drpGoods.getGoodsId()!=null ,
                                "GoodsId",drpGoods.getGoodsId()
                        )
        );
        return AjaxResult.success(page);
    }

    @Override
    public List<DrpGoods> queryGoodsByWarehouseId(Integer warehouseId, DrpGoods drpGoods, PageDomain pageDomain) {

        return drpGoodsMapper.queryGoodsByWarehouseId(warehouseId,drpGoods,pageDomain);
    }

    @Override
    public AjaxResult getOne(Integer id) {
        DrpGoods byId = drpGoodsMapper.getOne(id);
        System.out.println(byId);
        return AjaxResult.success(byId);
    }

    @Override
    @Transactional // 开启事务
    public AjaxResult add(DrpGoods drpGoods) throws GoodsIsExist {
        boolean save = save(drpGoods);
        boolean insert = goodsWarehouseService.save(new DrpGoodsWarehouse(drpGoods.getGoodsId(), drpGoods.getWarehouseId()));
        return AjaxResult.success(save&&insert);
    }

    @Override
    public AjaxResult edit(DrpGoods drpGoods) {
        boolean save=false;
        DrpGoodsWarehouse one = goodsWarehouseService
                .getOne(
                        new QueryWrapper<DrpGoodsWarehouse>()
                                .eq("GoodsId", drpGoods.getGoodsId())
                                .eq("WarehouseId", drpGoods.getWarehouseId()));
        if(one!=null){
            return AjaxResult.success(updateById(drpGoods));
        }else{
            return AjaxResult.error("商品不存在");
        }


    }

    @Override
    @Transactional
    public AjaxResult remove(Long[] id) {

        boolean data = removeByIds(Collections.arrayToList(id));
        boolean goodsId = goodsWarehouseService.remove(new UpdateWrapper<DrpGoodsWarehouse>().in("GoodsId", id));
        return AjaxResult.success(data&&goodsId);
    }
}




