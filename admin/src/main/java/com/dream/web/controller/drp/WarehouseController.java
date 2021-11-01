package com.dream.web.controller.drp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.drp.domain.DrpWarehouse;
import com.dream.drp.service.DrpWarehouseService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test/drpWarehouse")
public class WarehouseController {

    @Resource
    private DrpWarehouseService drpWarehouseService;


    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable("id") Long[] ids)
    {
        return drpWarehouseService.removeIds(ids);
    }

    @GetMapping("/select")
    public AjaxResult select(DrpWarehouse drpWarehouse){
        System.out.println(drpWarehouse);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return drpWarehouseService.select(drpWarehouse,pageDomain.getPageNum(),pageDomain.getPageSize());
    }

    @GetMapping("/treeselect")
    public AjaxResult treeselect(){
        List<Map<String, Object>> list = drpWarehouseService.listMaps(new QueryWrapper<DrpWarehouse>().select("WarehouseId as id", "WarehouseName as label"));
        return AjaxResult.toAjax(list !=null,list);
    }
    @GetMapping("/{id}")
    public AjaxResult getOne(@PathVariable Integer id){

        return drpWarehouseService.getOne(id);
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody DrpWarehouse drpWarehouse){
        return drpWarehouseService.add(drpWarehouse);
    }

    @PutMapping
    public AjaxResult edit(@RequestBody DrpWarehouse drpWarehouse){
        System.out.println(drpWarehouse);
        return drpWarehouseService.edit(drpWarehouse);
    }


}
