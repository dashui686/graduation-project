package com.dream.drp.service;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.drp.Exception.GoodsIsExist;
import com.dream.drp.domain.DrpGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 *
 */
public interface DrpGoodsService extends IService<DrpGoods> {
    /**
     * 查询全部
     * @param drpGoods   条件参数
     * @return 数据集合
     */
    public AjaxResult select(DrpGoods drpGoods,Integer current,Integer size);

    List<DrpGoods> queryGoodsByWarehouseId(Integer warehouseId,DrpGoods drpGoods, PageDomain pageDomain);


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
    public AjaxResult add(DrpGoods drpGoods) throws GoodsIsExist;

    /**
     * 修改
     * @param drpGoods
     * @return
     */
    public AjaxResult edit(DrpGoods drpGoods);

    /**
     *  删除
     * @param id
     * @return
     */
    public AjaxResult remove(Long[] id);



}
