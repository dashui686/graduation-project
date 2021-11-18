package com.dream.process.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.domain.TbFlow;
import com.dream.process.domain.TbGoodsapply;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.service.TbFlowService;
import com.dream.process.service.TbGoodsapplyService;
import com.dream.process.mapper.TbGoodsapplyMapper;
import com.dream.process.utils.ProcessUtils;
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
public class TbGoodsapplyServiceImpl extends ServiceImpl<TbGoodsapplyMapper, TbGoodsapply>
    implements TbGoodsapplyService{

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    ProcessUtils processUtils;

    @Autowired
    TaskService taskService;

    @Autowired
    TbFlowService tbFlowService;

    @Override
    @Transactional
    public AjaxResult add(TbGoodsapply tbGoodsapply) {
        LoginUser loginUser = SecurityUtils.getLoginUser();

        boolean save = save(tbGoodsapply);
        Map<String,Object> map = new HashMap<>();
        map.put("assignee",tbGoodsapply.getCreateUser());
        ProcessInstance myLeave = processUtils.createInstance("myGoodsApply", tbGoodsapply.getId().toString(), map);
        if(myLeave!=null){
            System.out.println(myLeave);
            Task task = taskService.createTaskQuery().processInstanceId(myLeave.getProcessInstanceId()).singleResult();
            System.out.println(task);
            ProcessApproveVo processApproveVo = new ProcessApproveVo(tbGoodsapply.getAssignee(), myLeave.getProcessInstanceId(), task.getId(), "", "1");

            TbFlow tbFlow = new TbFlow();
            tbFlow.setAssignee(tbGoodsapply.getAssignee());
            tbFlow.setBusinessKey(tbGoodsapply.getId().toString());
            tbFlow.setCreateDate(LocalDateTime.now());
            tbFlow.setCreateUserId(loginUser.getUserId().toString());
            tbFlow.setCreateUserName(loginUser.getUser().getNickName());
            tbFlow.setProcessInstanceDefinitionKey(myLeave.getProcessDefinitionKey());
            tbFlow.setProcessInstanceDefinitionName(myLeave.getProcessDefinitionName());
            tbFlow.setProcessInstanceId(myLeave.getProcessInstanceId());
            tbFlow.setTaskId(task.getId());
            tbFlow.setTaskNaem(task.getName());
            tbFlow.setProcessState(0);
            boolean insert = tbFlow.insert();
            AjaxResult ajaxResult = approvePleaseLeave(processApproveVo);

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
    public TbGoodsapply getBusiness(String id)  {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
        return getById(processInstance.getBusinessKey());
    }

    @Override
    public AjaxResult approvePleaseLeave(ProcessApproveVo processApproveVo) {
        TbFlow one = tbFlowService.getOne(new QueryWrapper<TbFlow>().eq("ProcessInstanceId", processApproveVo.getProcessInstanceId()).eq("TaskId", processApproveVo.getTaskId()));
        Task task1 = taskService.createTaskQuery().taskId(processApproveVo.getTaskId()).singleResult();
        Map<String,Object> map = new HashMap<>();
        map.put("state",processApproveVo.getState());
        map.put("assginee",processApproveVo.getAssignee());
        boolean b = processUtils.approveTask(processApproveVo.getTaskId(), processApproveVo.getApproveReason(), map);

        if(b){
            Task task = taskService.createTaskQuery().processInstanceId(task1.getProcessInstanceId()).singleResult();
            if(task==null){
                one.setAssignee("");
                one.setProcessState(1);
                one.setTaskId("0");
                one.setTaskNaem("完成");
                one.setReason(processApproveVo.getApproveReason());
            }else{
                one.setAssignee(processApproveVo.getAssignee());
                one.setTaskId(task.getId());
                one.setTaskNaem(task.getName());
                one.setReason(processApproveVo.getApproveReason());
            }
        }
        boolean b1 = one.updateById();
        return AjaxResult.toAjax(b&&b1,b&&b1?"":"任务已完成或任务不存在");
    }

    @Override
    public AjaxResult unApprovePleaseLeave(ProcessApproveVo processApproveVo) {
        TbFlow one = tbFlowService.getOne(new QueryWrapper<TbFlow>().eq("ProcessInstanceId", processApproveVo.getProcessInstanceId()).eq("TaskId", processApproveVo.getTaskId()));
        one.setProcessState(2);
        one.setReason(processApproveVo.getApproveReason());
        one.setTaskNaem("拒绝审批");
        boolean b = processUtils.unApproveTask(processApproveVo.getTaskId(), processApproveVo.getApproveReason());
        boolean b1 = one.updateById();
        return AjaxResult.toAjax(b&&b1,b&&b1?"":"任务已完成或任务不存在");
    }
}




