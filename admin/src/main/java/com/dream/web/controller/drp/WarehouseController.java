package com.dream.web.controller.drp;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.drp.domain.DrpWarehouse;
import com.dream.drp.service.DrpWarehouseService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
