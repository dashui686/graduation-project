package com.dream.drp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.drp.domain.DrpGoodstype;
import com.dream.drp.domain.DrpRelation;
import com.dream.drp.service.DrpGoodstypeService;
import com.dream.drp.mapper.DrpGoodstypeMapper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.stereotype.Service;


/**
 *
 */
@Service
public class DrpGoodstypeServiceImpl extends ServiceImpl<DrpGoodstypeMapper, DrpGoodstype>
    implements DrpGoodstypeService{

    @Override
    public AjaxResult select(DrpGoodstype drpGoodstype, Integer current, Integer size) {
        Page<DrpGoodstype> page = page(
                new Page<DrpGoodstype>(current, size),
                new QueryWrapper<DrpGoodstype>()
                        .like(
                                drpGoodstype.getGoodsTypeName()!= null && !drpGoodstype.getGoodsTypeName().isEmpty(),
                                "goodsTypeName","%"+drpGoodstype.getGoodsTypeName()+"%"
                        )
                        .eq(
                                drpGoodstype.getGoodsTypeId()!=null ,
                                "goodsTypeId",drpGoodstype.getGoodsTypeId()
                        )
        );
        return AjaxResult.success(page);
    }

    @Override
    public AjaxResult getOne(Integer id) {
        return AjaxResult.success(getById(id));
    }

    @Override
    public AjaxResult add(DrpGoodstype drpGoodstype) {
        return AjaxResult.success(save(drpGoodstype));
    }

    @Override
    public AjaxResult edit(DrpGoodstype drpGoodstype) {
        return AjaxResult.success(updateById(drpGoodstype));
    }

    @Override
    public AjaxResult removeIds(Long[] id) {
        return AjaxResult.success(removeByIds(Collections.arrayToList(id)));
    }
}




