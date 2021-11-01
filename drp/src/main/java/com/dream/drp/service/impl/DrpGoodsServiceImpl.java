package com.dream.drp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.drp.domain.DrpGoods;
import com.dream.drp.service.DrpGoodsService;
import com.dream.drp.mapper.DrpGoodsMapper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class DrpGoodsServiceImpl extends ServiceImpl<DrpGoodsMapper, DrpGoods>
    implements DrpGoodsService{


    @Autowired
    DrpGoodsMapper drpGoodsMapper;

    @Override
    public AjaxResult select(DrpGoods drpGoods, Integer current, Integer size) {

        return AjaxResult.success();
    }

    @Override
    public List<DrpGoods> queryGoodsByWarehouseId(Integer warehouseId, DrpGoods drpGoods, PageDomain pageDomain) {

        return drpGoodsMapper.queryGoodsByWarehouseId(warehouseId,drpGoods,pageDomain);
    }

    @Override
    public AjaxResult getOne(Integer id) {
        return AjaxResult.success(getById(id));
    }

    @Override
    public AjaxResult add(DrpGoods drpGoods) {
        return AjaxResult.success(add(drpGoods));
    }

    @Override
    public AjaxResult edit(DrpGoods drpGoods) {
        return AjaxResult.success(updateById(drpGoods));
    }

    @Override
    public AjaxResult remove(Long[] id) {
        return AjaxResult.success(removeByIds(Collections.arrayToList(id)));
    }
}




