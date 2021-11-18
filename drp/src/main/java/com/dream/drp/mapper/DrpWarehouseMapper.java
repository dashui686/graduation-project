package com.dream.drp.mapper;

import com.dream.drp.domain.DrpWarehouse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.dream.drp.domain.DrpWarehouse
 */
public interface DrpWarehouseMapper extends BaseMapper<DrpWarehouse> {

    /**
     * 查询全部仓库
     * @return
     */
    List<DrpWarehouse> queryAll();
}




