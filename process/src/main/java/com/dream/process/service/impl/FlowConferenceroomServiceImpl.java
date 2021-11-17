package com.dream.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.FlowConferenceroom;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.domain.TbFlow;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.service.FlowConferenceroomService;
import com.dream.process.mapper.FlowConferenceroomMapper;
import com.dream.process.utils.ProcessUtils;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
public class FlowConferenceroomServiceImpl extends ServiceImpl<FlowConferenceroomMapper, FlowConferenceroom>
    implements FlowConferenceroomService{


    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProcessUtils processUtils;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    TaskService taskService;

    @Override
    @Transactional
    public AjaxResult addLeave(FlowConferenceroom flowConferenceroom) {
        LoginUser loginUser = SecurityUtils.getLoginUser();

        boolean save = save(flowConferenceroom);
        Map<String,Object> map = new HashMap<>();
        map.put("assignee",flowConferenceroom.getCreateUser());
        ProcessInstance conference = processUtils.createInstance("conference", flowConferenceroom.getId().toString(), map);
        if(conference!=null){
            System.out.println(conference);
            Task task = taskService.createTaskQuery().processInstanceId(conference.getProcessInstanceId()).singleResult();
            System.out.println(task);
            ProcessApproveVo processApproveVo = new ProcessApproveVo(flowConferenceroom.getAssignee(), conference.getProcessInstanceId(), task.getId(), "", "1");
            AjaxResult ajaxResult = approvePleaseLeave(processApproveVo);
            Task taskOnlie = taskService.createTaskQuery().processInstanceId(conference.getProcessInstanceId()).singleResult();

            TbFlow tbFlow = new TbFlow();
            tbFlow.setCandidateGroups("202");
            tbFlow.setBusinessKey(flowConferenceroom.getId().toString());
            tbFlow.setCreateDate(LocalDateTime.now());
            tbFlow.setCreateUserId(loginUser.getUserId().toString());
            tbFlow.setCreateUserName(loginUser.getUser().getNickName());
            tbFlow.setProcessInstanceDefinitionKey(conference.getProcessDefinitionKey());
            tbFlow.setProcessInstanceDefinitionName(conference.getProcessDefinitionName());
            tbFlow.setProcessInstanceId(conference.getProcessInstanceId());
            tbFlow.setTaskId(taskOnlie.getId());
            tbFlow.setTaskNaem(taskOnlie.getName());
            tbFlow.setProcessState(1);
            boolean insert = tbFlow.insert();
            if(insert){
                return ajaxResult;
            }else{
                return AjaxResult.error("流程启动失败");
            }
        }else{
            return AjaxResult.error("流程启动失败");
        }
    }

    @Override
    public PleaseLeave getBusiness(String id) {
        return null;
    }

    @Override
    public AjaxResult approvePleaseLeave(ProcessApproveVo processApproveVo) {
        return null;
    }

    @Override
    public AjaxResult unApprovePleaseLeave(ProcessApproveVo processApproveVo) {
        return null;
    }
}




