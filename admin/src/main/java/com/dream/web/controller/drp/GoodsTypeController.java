package com.dream.web.controller.drp;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.drp.domain.DrpGoodstype;
import com.dream.drp.service.DrpGoodstypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test/goodsType")
public class GoodsTypeController {

    @Resource
    private DrpGoodstypeService goodsTypeService;

    @GetMapping("/select")
    public AjaxResult select(DrpGoodstype drpGoodstype){
        System.out.println(drpGoodstype);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return goodsTypeService.select(drpGoodstype,pageDomain.getPageNum(),pageDomain.getPageSize());
    }

    @GetMapping("/{id}")
    public AjaxResult getOne(@PathVariable Integer id){
        return goodsTypeService.getOne(id);
    }


    @PostMapping
    public AjaxResult add(@Validated @RequestBody DrpGoodstype drpGoodstype){
        return goodsTypeService.add(drpGoodstype);
    }

    @PutMapping
    public AjaxResult edit(@RequestBody DrpGoodstype drpGoodstype){
        return goodsTypeService.edit(drpGoodstype);
    }

    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable("id") Long[] ids){
        return goodsTypeService.removeIds(ids);
    }
}
