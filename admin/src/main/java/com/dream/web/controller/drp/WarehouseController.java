package com.dream.web.controller.drp;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.drp.domain.DrpWarehouse;
import com.dream.drp.service.DrpWarehouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test/drpWarehouse")
public class WarehouseController {

    @Resource
    private DrpWarehouseService drpWarehouseService;

    @GetMapping("/select")
    public AjaxResult select(DrpWarehouse drpWarehouse){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return drpWarehouseService.select(drpWarehouse,pageDomain.getPageNum(),pageDomain.getPageSize());
    }

    @GetMapping("/{id}")
    public AjaxResult getOne(Integer id){
        return drpWarehouseService.getOne(id);
    }

    @PostMapping
    public AjaxResult add(DrpWarehouse drpWarehouse){
        return drpWarehouseService.add(drpWarehouse);
    }

    @PutMapping
    public AjaxResult edit(DrpWarehouse drpWarehouse){
        return drpWarehouseService.edit(drpWarehouse);
    }

    @DeleteMapping("{/id}")
    public AjaxResult remove(@PathVariable  Integer[] id){
        return drpWarehouseService.remove(id);
    }
}
