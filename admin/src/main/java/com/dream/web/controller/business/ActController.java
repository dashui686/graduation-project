package com.dream.web.controller.business;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.ProcessDeploy;
import com.dream.process.service.ProcessDeployService;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private ProcessRuntime processRuntime;

    /**
     * 任务
     */
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRuntime taskRuntime;

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
     * 查询所有流程中的业务
     * @return
     */
    @GetMapping("/test/3")
    public AjaxResult test3(){
        List<ProcessInstance> myLeave =
                runtimeService.createProcessInstanceQuery().processDefinitionKey("myLeave").list();
        Stream<Map<String,Object>> objectStream = myLeave.stream().map(obj ->{
            Map<String, Object> stringObjectMap = null;
            try {
                stringObjectMap = objectToMap(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return stringObjectMap;
        });

        return AjaxResult.success(objectStream.collect(Collectors.toList()));
    }

    //java对象转map
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
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




    @GetMapping("/test/myLeave")
    public LoginUser myLeave(){
        return SecurityUtils.getLoginUser();
    }
}
