package com.dream.web.controller.drp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.common.utils.ServletUtils;
import com.dream.drp.Exception.GoodsIsExist;
import com.dream.drp.domain.DrpGoods;
import com.dream.drp.domain.DrpGoodsWarehouse;
import com.dream.drp.service.DrpGoodsService;
import com.dream.drp.service.DrpGoodsWarehouseService;
import com.dream.drp.service.DrpGoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/test/drpGoods")
public class GoodsController {
    @Resource
    private DrpGoodsService drpGoodsService;
    @Autowired
    private DrpGoodstypeService goodstypeService;
    @Autowired
    private DrpGoodsWarehouseService drpGoodsWarehouseService;
    @GetMapping("/select")
    public AjaxResult select(DrpGoods drpGoods){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer warehouseId = ServletUtils.getParameterToInt("warehouseId");
        System.out.println(warehouseId);
        List<DrpGoods> drpGoods1 = drpGoodsService.queryGoodsByWarehouseId(warehouseId,drpGoods,pageDomain);
        //AjaxResult select = drpGoodsService.select(drpGoods, pageDomain.getPageNum(), pageDomain.getPageNum());
        return AjaxResult.success(drpGoods1);
    }
    @GetMapping("/treeSelectByWarehouseId/{id}")
    public AjaxResult treeSelect(@PathVariable Integer id){
        List<DrpGoods> goodsId = null;
        try {
            List<Object> objects = drpGoodsWarehouseService.listObjs(new QueryWrapper<DrpGoodsWarehouse>().eq("WarehouseId", id).select("GoodsId"));
            if(objects.size() <=0){
                throw new Exception("仓库物品不足");
            }
            goodsId = drpGoodsService.getListByIds(objects);



        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(goodsId);
    }
    @GetMapping("/{id}")
    public AjaxResult getOne(@PathVariable Integer id){

        return drpGoodsService.getOne(id);
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody DrpGoods drpGoods){
        drpGoods.setCreateDate(LocalDateTime.now());
        System.out.println(drpGoods);
        AjaxResult add = null;
        try {
            add = drpGoodsService.add(drpGoods);
        } catch (GoodsIsExist goodsIsExist) {
            goodsIsExist.printStackTrace();
        }
        return AjaxResult.toAjax(add!=null);
    }

    @PutMapping
    public AjaxResult edit(@Validated @RequestBody DrpGoods drpGoods){
        drpGoods.setUpdateDate(LocalDateTime.now());
        return drpGoodsService.edit(drpGoods);
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable Long[] id){
        System.out.println(id);
        return drpGoodsService.remove(id);
    }
}
