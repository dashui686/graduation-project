package com.dream.web.controller.drp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.drp.domain.DrpGoods;
import com.dream.drp.service.DrpGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test/drpGoods")
public class GoodsController {
    @Resource
    private DrpGoodsService drpGoodsService;

    @GetMapping("/select")
    public AjaxResult select(DrpGoods drpGoods){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return drpGoodsService.select(drpGoods,pageDomain.getPageNum(),pageDomain.getPageSize());
    }
    
    @GetMapping("/{id}")
    public AjaxResult getOne(Integer id){
        return drpGoodsService.getOne(id);
    }

    @PostMapping
    public AjaxResult add(DrpGoods drpGoods){
        return drpGoodsService.add(drpGoods);
    }

    @PutMapping
    public AjaxResult edit(DrpGoods drpGoods){
        return drpGoodsService.edit(drpGoods);
    }

    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable  Integer[] id){
        return drpGoodsService.remove(id);
    }
}
