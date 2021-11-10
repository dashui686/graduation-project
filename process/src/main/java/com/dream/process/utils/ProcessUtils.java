package com.dream.process.utils;

import com.dream.common.utils.SecurityUtils;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessUtils {
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
     * 运行任务
     */
    @Autowired
    ProcessRuntime runtime;

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

    /**
     * 创建流程实例
     * @param definitionKey 流程key
     * @param businessKey 业务key
     * @param data 参数
     * @return 流程实例
     */
    public ProcessInstance createInstance(String definitionKey,String businessKey,Map<String,Object> data){
        Authentication.setAuthenticatedUserId(SecurityUtils.getUserId().toString());
        return runtimeService.startProcessInstanceByKey("myLeave",businessKey ,data);
    }

    /**
     * 同意审批
     * @param taskId 任务ID
     * @param reason 同意理由
     * @param data 参数
     * @return 是否成功
     */
    public boolean approveTask(String taskId,String reason,Map<String,Object> data){
        try {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            Authentication.setAuthenticatedUserId(SecurityUtils.getUserId().toString());
            taskService.addComment(taskId,task.getProcessInstanceId(),reason);
            taskService.complete(task.getId(),data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 拒绝审批
     * @param taskId 任务ID
     * @param reason 同意理由
     * @param data 参数
     * @return 是否成功
     */
    public boolean unApproveTask(String taskId,String reason){
        try {
            Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
            Authentication.setAuthenticatedUserId(SecurityUtils.getUserId().toString());
            taskService.addComment(taskId,task.getProcessInstanceId(),reason);
            runtimeService.deleteProcessInstance(task.getProcessInstanceId(),reason);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
