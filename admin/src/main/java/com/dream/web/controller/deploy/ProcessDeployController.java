package com.dream.web.controller.deploy;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.common.annotation.Log;
import com.dream.common.constant.UserConstants;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.common.enums.BusinessType;
import com.dream.deploy.domain.ProcessDeploy;
import com.dream.deploy.service.ProcessDeployService;
import com.dream.system.domain.SysPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProcessDeployController {
    @Autowired
    ProcessDeployService processDeployService;

    @GetMapping("/test/processDeploy/select")
    public AjaxResult select(ProcessDeploy processDeploy){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        //查询全部
        Page<ProcessDeploy> page = processDeployService
                .page(
                        new Page<ProcessDeploy>(pageDomain.getPageNum(), pageDomain.getPageSize()),
                        new QueryWrapper<ProcessDeploy>()
                                .eq(
                                        processDeploy.getProcessName()!= null && !processDeploy.getProcessName().isEmpty(),
                                        "ProcessName",processDeploy.getProcessName()
                                )
                                .eq(
                                        processDeploy.getProcessState()!=null ,
                                        "ProcessState",processDeploy.getProcessState()
                                )
                );
        return AjaxResult.success(page);
    }

    @GetMapping("/test/processDeploy/{id}")
    public AjaxResult getOne(@PathVariable("id") Integer id){
        ProcessDeploy byId = processDeployService.getById(id);
        return AjaxResult.success(byId);
    }

    /**
     * 新增岗位
     */
//    @PreAuthorize("@ss.hasPermi('system:post:add')")
//    @Log(title = "管理", businessType = BusinessType.INSERT)
    @PostMapping("/")
    public AjaxResult add(@Validated @RequestBody ProcessDeploy processDeploy)
    {
        System.out.println(processDeploy);
        return AjaxResult.toAjax(true,"123");
    }

    /**
     * 修改岗位
     */
//    @Log(title = "岗位管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysPost post)
    {
        return AjaxResult.toAjax(true,"!23");
    }

    /**
     * 删除岗位
     */
    @PreAuthorize("@ss.hasPermi('system:post:remove')")
    @Log(title = "岗位管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return AjaxResult.toAjax(true,"!23");
    }

}
