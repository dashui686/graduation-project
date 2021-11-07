package com.dream.web.controller.drp;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.common.utils.SecurityUtils;
import com.dream.drp.domain.DrpRelation;
import com.dream.drp.service.CustomerService;
import com.dream.drp.service.DrpRelationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/test/relation/customer")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @GetMapping("/select")
    public AjaxResult select(DrpRelation drpRelation){
        System.out.println(drpRelation);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        return customerService.select(drpRelation,pageDomain.getPageNum(),pageDomain.getPageSize());
    }

    @GetMapping("/{id}")
    public AjaxResult getOne(@PathVariable Integer id){
        return customerService.getOne(id);
    }

    @PostMapping
    public AjaxResult add(@Validated @RequestBody DrpRelation drpRelation){
        drpRelation.setCreateUsers(SecurityUtils.getUserId().intValue());
        drpRelation.setCreateDate(LocalDateTime.now());
        return customerService.add(drpRelation);
    }

    @PutMapping
    public AjaxResult edit(@RequestBody DrpRelation drpRelation){
        drpRelation.setUpdateUsers(SecurityUtils.getUserId().intValue());
        drpRelation.setUpdateDate(LocalDateTime.now());
        drpRelation.setLasttradingDate(LocalDateTime.now());
        return customerService.edit(drpRelation);
    }


    @DeleteMapping("/delete/{id}")
    public AjaxResult remove(@PathVariable("id")  Long[] ids){
        return customerService.removeIds(ids);
    }
}
