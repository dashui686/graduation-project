package com.dream.drp.service;

import com.dream.common.core.domain.AjaxResult;
import com.dream.drp.domain.DrpGoods;
import com.dream.drp.domain.DrpWarehouse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface DrpWarehouseService extends IService<DrpWarehouse> {

    /**
     *      查询全部
     * @param drpWarehouse  条件参数
     * @param current       条件参数
     * @param size          条件参数
     * @return
     */
    public AjaxResult select(DrpWarehouse drpWarehouse, Integer current, Integer size);

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
    public AjaxResult add(DrpWarehouse drpWarehouse);

    /**
     * 修改
     * @param drpWarehouse
     * @return
     */
    public AjaxResult edit(DrpWarehouse drpWarehouse);

    /**
     *  删除
     * @param id
     * @return
     */
    public AjaxResult remove(Integer[] id);
}
