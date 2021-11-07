package com.dream.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.exception.TaskOfNullException;
import com.dream.process.service.PleaseLeaveService;
import com.dream.process.mapper.PleaseLeaveMapper;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class PleaseLeaveServiceImpl extends ServiceImpl<PleaseLeaveMapper, PleaseLeave>
    implements PleaseLeaveService{

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



    @Override
    @Transactional
    public boolean addLeave(PleaseLeave pleaseLeave, Integer assignee) throws Exception {
        boolean save = save(pleaseLeave);
        if(save){
            Map<String,Object> map = new HashMap<>();
            map.put("assignee",pleaseLeave.getUserId());
//            map.put("assignee",assignee);
            ProcessInstance myLeave = runtimeService.startProcessInstanceByKey("myLeave", pleaseLeave.getId().toString(),map);
        }else{
            save = false;
            throw new Exception("启动流程错误");
        }
        return save;
    }

    @Override
    @Transactional
    public PleaseLeave getBusiness(String id)  {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
        PleaseLeave byId = getById(processInstance.getBusinessKey());
        if (byId == null){
            return null;
        }
        return byId;
    }

    @Override
    public void approvePleaseLeave(ProcessApproveVo processApproveVo) throws TaskOfNullException {
        try {
            Task task = taskService.createTaskQuery().taskId(processApproveVo.getTaskId()).singleResult();
            Authentication.setAuthenticatedUserId(SecurityUtils.getUserId().toString());
            taskService.addComment(processApproveVo.getTaskId(),task.getProcessInstanceId(),processApproveVo.getApproveReason());
            Map<String,Object> map = new HashMap<>();
            System.out.println(processApproveVo);
            map.put("state",processApproveVo.getState());
            map.put("assginee",processApproveVo.getAssignee());
            taskService.complete(task.getId(),map);
        } catch (Exception e) {
            throw new TaskOfNullException("任务已完成或任务不存在");
        }
    }

    @Override
    public void unApprovePleaseLeave(ProcessApproveVo processApproveVo) throws TaskOfNullException {
        try {
            Task task = taskService.createTaskQuery().taskId(processApproveVo.getTaskId()).singleResult();
            Authentication.setAuthenticatedUserId(SecurityUtils.getUserId().toString());
            taskService.addComment(processApproveVo.getTaskId(),task.getProcessInstanceId(),processApproveVo.getApproveReason());
            runtimeService.deleteProcessInstance(task.getProcessInstanceId(),processApproveVo.getApproveReason());
        } catch (Exception e) {
            throw new TaskOfNullException("任务已完成或任务不存在");
        }
    }
}




