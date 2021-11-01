package com.dream.web.controller.drp;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.drp.domain.DrpRelation;
import com.dream.drp.service.DrpRelationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test/relation")
public class RelationController {

    @Resource
    private DrpRelationService relationService;

    @GetMapping("/select")
    public AjaxResult select(DrpRelation drpRelation){
        System.out.println(drpRelation);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return relationService.select(drpRelation,pageDomain.getPageNum(),pageDomain.getPageSize());
    }

    @GetMapping("/{id}")
    public AjaxResult getOne(@PathVariable  Integer id){
        return relationService.getOne(id);
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody DrpRelation drpRelation){
        return relationService.add(drpRelation);
    }

    @PutMapping
    public AjaxResult edit(@RequestBody DrpRelation drpRelation){
        return relationService.edit(drpRelation);
    }


    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable("id")  Long[] ids){
        System.out.println(ids);
        return relationService.removeIds(ids);
    }
}
