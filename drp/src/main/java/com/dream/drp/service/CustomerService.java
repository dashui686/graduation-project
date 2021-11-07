package com.dream.drp.service;

import com.dream.common.core.domain.AjaxResult;
import com.dream.drp.domain.DrpRelation;

public interface CustomerService {
    /**
     *      查询全部
     * @param drpRelation  条件参数
     * @param current       条件参数
     * @param size          条件参数
     * @return
     */
    public AjaxResult select(DrpRelation drpRelation, Integer current, Integer size);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public AjaxResult getOne(Integer id);

    /**
     * 添加
     * @return
     */
    public AjaxResult add(DrpRelation drpRelation);

    /**
     * 修改
     * @param drpRelation
     * @return
     */
    public AjaxResult edit(DrpRelation drpRelation);

    /**
     *  删除
     * @param id
     * @return
     */
    public AjaxResult removeIds(Long[] id);
}
