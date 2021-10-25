package com.dream.web.controller.business;

import com.dream.common.core.domain.AjaxResult;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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



    @GetMapping("/test/1")
    public AjaxResult test(){
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> list = deploymentQuery.list();
        System.out.println(list);

        return AjaxResult.success(list);
    }

    @GetMapping("/test/2")
    public String test2(){

        return "123";
    }

}
