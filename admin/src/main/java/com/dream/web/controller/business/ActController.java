package com.dream.web.controller.business;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dream.common.core.domain.AjaxResult;
import com.dream.deploy.domain.ProcessDeploy;
import com.dream.deploy.service.ProcessDeployService;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActController {



    /**
     * 仓库
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 运行中的任务
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     * 任务
     */
    @Autowired
    private TaskService taskService;

    /**
     * 历史记录
     */
    @Autowired
    private HistoryService historyService;


    @Autowired
    ProcessDeployService processDeployService;

    @GetMapping("/test/1")
    public AjaxResult test(){
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> list = deploymentQuery.list();
        System.out.println(list);

        return AjaxResult.success(list);
    }


    /**
     * 删除所有流程
     * @return
     */
    @GetMapping("/test/2")
    public String test2(){
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> list = deploymentQuery.list();
        for (Deployment deployment : list) {
            repositoryService.deleteDeployment(deployment.getId());
        }
        processDeployService.update(new UpdateWrapper<ProcessDeploy>().set("ProcessState",1));
        return "123";
    }




    @PostMapping("/test/myLeave")
    public String myLeave(){
        return "1";
    }
}
