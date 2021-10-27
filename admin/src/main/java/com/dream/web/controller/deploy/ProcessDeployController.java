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
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/test/processDeploy")
public class ProcessDeployController {

    @Autowired
    ProcessDeployService processDeployService;

    @GetMapping("/select")
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

    @GetMapping("/{id}")
    public AjaxResult getOne(@PathVariable("id") Integer id){
        ProcessDeploy byId = processDeployService.getById(id);
        return AjaxResult.success(byId);
    }

    /**
     * 新增部署任务
     */
    @PostMapping("/")
    public AjaxResult add(@Validated @RequestBody ProcessDeploy processDeploy, Principal principal)
    {
        processDeploy.setCreateTime(LocalDateTime.now());
        processDeploy.setProcessDeployer(principal.getName());
        processDeploy.setProcessState("1");
        processDeploy.setUpdateTime(LocalDateTime.now());
        boolean insert = processDeploy.insert();

        return AjaxResult.toAjax(insert,processDeploy);
    }

    /**
     * 修改部署任务
     */
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ProcessDeploy processDeploy)
    {
        processDeploy.setUpdateTime(LocalDateTime.now());
        boolean b = processDeploy.updateById();

        return AjaxResult.toAjax(b);
    }

    /**
     * 删除部署任务
     */
    @DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        boolean b = processDeployService.removeByIds(Collections.arrayToList(postIds));
        return AjaxResult.toAjax(b);
    }

}
