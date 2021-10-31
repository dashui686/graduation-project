package com.dream.drp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.drp.domain.DrpGoods;
import com.dream.drp.service.DrpGoodsService;
import com.dream.drp.mapper.DrpGoodsMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DrpGoodsServiceImpl extends ServiceImpl<DrpGoodsMapper, DrpGoods>
    implements DrpGoodsService{


    @Override
    public AjaxResult select(DrpGoods drpGoods, Integer current, Integer size) {
        Page<DrpGoods> page = page(
                new Page<DrpGoods>(current, size)
//                ,
//                new QueryWrapper<DrpGoods>()
//                        .eq(
//                                processDeploy.getProcessName()!= null && !processDeploy.getProcessName().isEmpty(),
//                                "ProcessName",processDeploy.getProcessName()
//                        )
//                        .eq(
//                                processDeploy.getProcessState()!=null ,
//                                "ProcessState",processDeploy.getProcessState()
//                        )
        );
        return AjaxResult.success(page);
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
    public AjaxResult remove(Integer[] id) {
        return AjaxResult.success(removeById(id));
    }
}




