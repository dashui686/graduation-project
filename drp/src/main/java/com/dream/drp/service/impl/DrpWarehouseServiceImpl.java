package com.dream.drp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.drp.domain.DrpGoods;
import com.dream.drp.domain.DrpWarehouse;
import com.dream.drp.service.DrpWarehouseService;
import com.dream.drp.mapper.DrpWarehouseMapper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 *
 */
@Service
public class DrpWarehouseServiceImpl extends ServiceImpl<DrpWarehouseMapper, DrpWarehouse>
    implements DrpWarehouseService{

    @Override
    public AjaxResult select(DrpWarehouse drpWarehouse, Integer current, Integer size) {
        Page<DrpWarehouse> page = page(
                new Page<DrpWarehouse>(current, size),
                new QueryWrapper<DrpWarehouse>()
                        .like(
                                drpWarehouse.getWarehouseName()!= null && !drpWarehouse.getWarehouseName().isEmpty(),
                                "WarehouseName","%"+drpWarehouse.getWarehouseName()+"%"
                        )
                        .eq(
                                drpWarehouse.getWarehouseId()!=null ,
                               "WarehouseId",drpWarehouse.getWarehouseId()
                       )
        );
        return AjaxResult.success(page);
    }

    @Override
    public AjaxResult getOne(Integer id) {
        return AjaxResult.success(getById(id));
    }

    @Override
    public AjaxResult add(DrpWarehouse drpWarehouse) {
        drpWarehouse.setCreateDate(LocalDateTime.now());
        return AjaxResult.success(save(drpWarehouse));
    }

    @Override
    public AjaxResult edit(DrpWarehouse drpWarehouse) {
        drpWarehouse.setUpdateDate(LocalDateTime.now());
        return AjaxResult.success(updateById(drpWarehouse));
    }

    @Override
    public AjaxResult removeIds(Long[] id) {
        return AjaxResult.success(removeByIds(Collections.arrayToList(id)));
    }
}




