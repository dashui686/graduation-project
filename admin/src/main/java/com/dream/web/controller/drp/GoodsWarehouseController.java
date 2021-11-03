package com.dream.web.controller.drp;

import com.dream.common.core.domain.AjaxResult;

import com.dream.drp.domain.DrpGoodsWarehouse;
import com.dream.drp.service.DrpGoodsWarehouseService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test/goodsWarehouse")
public class GoodsWarehouseController {

    @Resource
    private DrpGoodsWarehouseService drpGoodsWarehouseService;


    @PostMapping
    public AjaxResult add(@Validated @RequestBody DrpGoodsWarehouse drpGoodsWarehouse){
        return AjaxResult.success();
//        return drpGoodsWarehouseService.add(drpGoodsWarehouse);
    }
}
