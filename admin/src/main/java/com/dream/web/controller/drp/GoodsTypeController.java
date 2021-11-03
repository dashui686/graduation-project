package com.dream.web.controller.drp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.drp.domain.DrpGoodstype;
import com.dream.drp.domain.DrpWarehouse;
import com.dream.drp.service.DrpGoodstypeService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/treeselect")
    public AjaxResult treeselect(){
        List<Map<String, Object>> list = goodsTypeService.listMaps(new QueryWrapper<DrpGoodstype>().select("GoodsTypeId as id", "GoodsTypeName as label"));
        return AjaxResult.toAjax(list !=null,list);
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
