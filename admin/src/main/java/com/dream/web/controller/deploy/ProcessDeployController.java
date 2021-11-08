package com.dream.web.controller.deploy;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.ProcessDeploy;
import com.dream.process.service.ProcessDeployService;
import com.sun.jmx.snmp.tasks.TaskServer;
import io.jsonwebtoken.lang.Collections;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
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
    @Autowired
    TaskRuntime taskRuntime;
    @Autowired
    TaskService taskService;

    @GetMapping("/select")
    public AjaxResult select(ProcessDeploy processDeploy){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        //查询全部
        Page<ProcessDeploy> page = processDeployService
                .page(
                        new Page<ProcessDeploy>(pageDomain.getPageNum(), pageDomain.getPageSize()),
                        new QueryWrapper<ProcessDeploy>()
                                .like(
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
//        Long id = SecurityUtils.getLoginUser().getUserId();
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
                        "process/"+processDeploy1.getProcessZip()+".zip");
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
