package com.dream.web.controller.deploy;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/test/processDeploy")
public class ProcessDeployController {

    @Autowired
    ProcessDeployService processDeployService;
    /**
     * 仓库
     */
    @Autowired
    private RepositoryService repositoryService;

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
    @PostMapping
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
        return AjaxResult.toAjax(processDeploy.updateById());
    }

    /**
     * 删除部署任务
     */
    @DeleteMapping("/{postIds}")
    public AjaxResult remove(@PathVariable Long[] postIds)
    {
        return AjaxResult.toAjax(processDeployService.removeByIds(Collections.arrayToList(postIds)));
    }


    /**
     * 流程部署
     */
    @PostMapping("/deploy")
    public AjaxResult processDeploy(@Validated @RequestBody ProcessDeploy processDeploy)
    {
        ProcessDeploy processDeploy1 = processDeploy.selectById();
        // 定义zip输入流
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream(
                        "processes/"+processDeploy1.getProcessZip()+".zip");
        if(inputStream == null){
            return  AjaxResult.error("部署文件不存在");
        }
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);

        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
        boolean condition = !(deploy == null);
        boolean update = processDeployService.update(new UpdateWrapper<ProcessDeploy>().set(condition, "ProcessState", 0).eq("ProcessId", processDeploy1.getProcessId()));
        return AjaxResult.toAjax(condition && update);
    }

    /**
     * 流程删除
     */
    @PostMapping("/unDeploy")
    public AjaxResult unProcessDeploy(@Validated @RequestBody ProcessDeploy processDeploy)
    {
        ProcessDeploy processDeploy1 = processDeploy.selectById();
        Deployment deployment = repositoryService.createDeploymentQuery().processDefinitionKey(processDeploy1.getProcessZip()).singleResult();
        boolean a = false;
        try {
            repositoryService.deleteDeployment(deployment.getId());
            a = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean update = processDeployService.update(new UpdateWrapper<ProcessDeploy>().set("ProcessState", 1).eq(a,"ProcessId", processDeploy1.getProcessId()));
        return AjaxResult.toAjax(update&&a);
    }

}
