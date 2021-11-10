package com.dream.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.exception.TaskOfNullException;
import com.dream.process.service.PleaseLeaveService;
import com.dream.process.mapper.PleaseLeaveMapper;
import com.dream.process.utils.ProcessUtils;
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

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProcessUtils processUtils;

    @Autowired
    TaskService taskService;

    @Override
    @Transactional
    public AjaxResult addLeave(PleaseLeave pleaseLeave) {
        boolean save = save(pleaseLeave);
        Map<String,Object> map = new HashMap<>();
        map.put("assignee",pleaseLeave.getUserId());
        ProcessInstance myLeave = processUtils.createInstance("myLeave", pleaseLeave.getId().toString(), map);
        if(myLeave!=null){
            System.out.println(myLeave);
            Task task = taskService.createTaskQuery().processInstanceId(myLeave.getProcessInstanceId()).singleResult();
            System.out.println(task);
            ProcessApproveVo processApproveVo = new ProcessApproveVo(pleaseLeave.getAssignee(), myLeave.getProcessInstanceId(), task.getId(), "", "1");
            return approvePleaseLeave(processApproveVo);
        }else{
            return AjaxResult.error("流程启动失败");
        }
    }

    @Override
    public PleaseLeave getBusiness(String id)  {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
        return getById(processInstance.getBusinessKey());
    }

    @Override
    public AjaxResult approvePleaseLeave(ProcessApproveVo processApproveVo) {
            Map<String,Object> map = new HashMap<>();
            map.put("state",processApproveVo.getState());
            map.put("assginee",processApproveVo.getAssignee());
            boolean b = processUtils.approveTask(processApproveVo.getTaskId(), processApproveVo.getApproveReason(), map);
            return AjaxResult.toAjax(b,b?"":"任务已完成或任务不存在");
    }

    @Override
    public AjaxResult unApprovePleaseLeave(ProcessApproveVo processApproveVo) {
            boolean b = processUtils.unApproveTask(processApproveVo.getTaskId(), processApproveVo.getApproveReason());
            return AjaxResult.toAjax(b,b?"":"任务已完成或任务不存在");
    }

}




