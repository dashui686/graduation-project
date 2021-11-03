package com.dream.drp.mapper;

import com.dream.common.core.page.PageDomain;
import com.dream.drp.domain.DrpGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Entity com.dream.drp.domain.DrpGoods
 */
public interface DrpGoodsMapper extends BaseMapper<DrpGoods> {

    List<DrpGoods> queryGoodsByWarehouseId(@Param("warehouseId") Integer warehouseId,@Param("drpGoods") DrpGoods drpGoods,@Param("pageDomain") PageDomain pageDomain);
    DrpGoods getOne(@Param("id") Integer id);
}




