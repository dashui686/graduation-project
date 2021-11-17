package com.dream.web.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dream.common.core.domain.AjaxResult;
import com.dream.process.domain.ProcessDeploy;
import com.dream.process.service.ProcessDeployService;
import com.dream.process.utils.ProcessUtils;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController("testProcessController")
@RequestMapping("/test")
public class TestController {


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
    ProcessUtils processUtils;

    @Autowired
    ProcessDeployService processDeployService;

    @GetMapping("/1")
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
    @GetMapping("/un/{name}")
    public AjaxResult test3(@PathVariable String name){
        List<ProcessInstance> myLeave =
                runtimeService.createProcessInstanceQuery().processDefinitionKey(name).list();
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
    @GetMapping("/2")
    public String test2(){
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> list = deploymentQuery.list();
        for (Deployment deployment : list) {
            repositoryService.deleteDeployment(deployment.getId(),true);
        }
        processDeployService.update(new UpdateWrapper<ProcessDeploy>().set("ProcessState",1));
        return "123";
    }


    /**
     * 完成任务
     * @return
     */
    @GetMapping("/4")
    public String myLeave(){
        Task task = taskService.createTaskQuery().taskAssignee("1").singleResult();

        return "123";
    }


    @GetMapping("/myProcess")
    public AjaxResult myProcess(){
        /**
         * 根据负责人id  查询任务
         */
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee("1");
        List<Task> list = taskQuery.orderByTaskCreateTime().desc().listPage(0,10);
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        for (Task task : list) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("taskid", task.getId());
            map.put("taskname", task.getName());
            map.put("description", task.getDescription());
            map.put("priority", task.getPriority());
            map.put("owner", task.getOwner());
            map.put("assignee", task.getAssignee());
            map.put("delegationState", task.getDelegationState());
            map.put("processInstanceId", task.getProcessInstanceId());
            map.put("executionId", task.getExecutionId());
            map.put("processDefinitionId", task.getProcessDefinitionId());
            map.put("createTime", task.getCreateTime());
            map.put("taskDefinitionKey", task.getTaskDefinitionKey());
            map.put("dueDate", task.getDueDate());
            map.put("category", task.getCategory());
            map.put("parentTaskId", task.getParentTaskId());
            map.put("tenantId", task.getTenantId());
            map.put("processKey", task.getProcessDefinitionId().split(":")[0]);

//            User userInfo = userService.findOneUserById(Long.valueOf(task.getAssignee()));
//            map.put("assigneeUser", userInfo.getUsername());
            listmap.add(map);
        }

        return AjaxResult.success(listmap);
    }
}
