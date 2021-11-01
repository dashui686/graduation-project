package com.dream.drp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.drp.domain.DrpRelation;
import com.dream.drp.domain.DrpWarehouse;
import com.dream.drp.service.DrpRelationService;
import com.dream.drp.mapper.DrpRelationMapper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DrpRelationServiceImpl extends ServiceImpl<DrpRelationMapper, DrpRelation>
    implements DrpRelationService{

    @Override
    public AjaxResult select(DrpRelation drpRelation, Integer current, Integer size) {
        Page<DrpRelation> page = page(
                new Page<DrpRelation>(current, size),
                new QueryWrapper<DrpRelation>()
                        .like(
                                drpRelation.getName()!= null && !drpRelation.getName().isEmpty(),
                                "name","%"+drpRelation.getName()+"%"
                        )
                        .eq(
                                drpRelation.getId()!=null ,
                                "id",drpRelation.getId()
                        )
        );
        return AjaxResult.success(page);
    }

    @Override
    public AjaxResult getOne(Integer id) {
        return AjaxResult.success(getById(id));
    }

    @Override
    public AjaxResult add(DrpRelation drpRelation) {
        return AjaxResult.success(save(drpRelation));
    }

    @Override
    public AjaxResult edit(DrpRelation drpRelation) {
        return AjaxResult.success(updateById(drpRelation));
    }

    @Override
    public AjaxResult removeIds(Long[] id) {
        return AjaxResult.success(removeByIds(Collections.arrayToList(id)));
        //Collections.arrayToList(id)
    }
}




