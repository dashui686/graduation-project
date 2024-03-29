package com.dream.web.controller.drp;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.common.utils.SecurityUtils;
import com.dream.drp.domain.DrpRelation;
import com.dream.drp.service.DrpRelationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/test/relation/supplier")
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
        drpRelation.setCreateUsers(SecurityUtils.getUserId().intValue());
        drpRelation.setCreateDate(LocalDateTime.now());
        return relationService.add(drpRelation);
    }

    @PutMapping
    public AjaxResult edit(@RequestBody DrpRelation drpRelation){
        drpRelation.setUpdateUsers(SecurityUtils.getUserId().intValue());
        drpRelation.setUpdateDate(LocalDateTime.now());
        drpRelation.setLasttradingDate(LocalDateTime.now());
        return relationService.edit(drpRelation);
    }


    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable("id")  Long[] ids){
        return relationService.removeIds(ids);
    }
}
