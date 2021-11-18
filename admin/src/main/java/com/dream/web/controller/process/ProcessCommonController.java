package com.dream.web.controller.process;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.entity.SysUser;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.core.page.PageDomain;
import com.dream.common.core.page.TableSupport;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.TbFlow;
import com.dream.process.service.TbFlowService;
import com.dream.process.utils.ProcessUtils;
import com.dream.system.service.ISysUserService;
import com.dream.system.service.impl.SysUserServiceImpl;
import com.dream.web.activiti.SecurityUtil;
import net.bytebuddy.asm.Advice;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test/process")
public class ProcessCommonController {

    @Autowired
    TaskService taskService;
    @Autowired
    HistoryService historyService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    ProcessUtils processUtils;
    @Autowired
    ISysUserService sysUserService;
    /**
     * 仓库
     */
    @Autowired
    private RepositoryService repositoryService;



    @Autowired
    private TbFlowService tbFlowService;

    @GetMapping("/myProcess")
    public AjaxResult getMyProcess(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        System.out.println(pageDomain);
        Page<TbFlow> page = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        QueryWrapper<TbFlow> createUserId1 = new QueryWrapper<TbFlow>().eq("Assignee", SecurityUtils.getUserId()).or().eq("CandidateGroups",SecurityUtils.getDeptId());
        Page<TbFlow> createUserId = tbFlowService.page(page, createUserId1);
        return AjaxResult.success(createUserId);
    }


//    public AjaxResult getMyProcess(){
//
//        /**
//         * 根据负责人id  查询任务
//         */
//        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(SecurityUtils.getLoginUser().getUserId().toString());
//        Map<String, String> map1 = processMap();
//        List<Task> list = taskQuery.orderByTaskCreateTime().desc().listPage(0,10);
//        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
//        for (Task task : list) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("taskid", task.getId());
//            map.put("taskname", task.getName());
//            map.put("description", task.getDescription());
//            map.put("priority", task.getPriority());
//            map.put("owner", task.getOwner());
//            map.put("assignee", task.getAssignee());
//            map.put("delegationState", task.getDelegationState());
//            map.put("processInstanceId", task.getProcessInstanceId());
//            map.put("executionId", task.getExecutionId());
//            map.put("processDefinitionId", task.getProcessDefinitionId());
//            map.put("createTime", task.getCreateTime());
//            map.put("taskDefinitionKey", task.getTaskDefinitionKey());
//            map.put("dueDate", task.getDueDate());
//            map.put("category", task.getCategory());
//            map.put("parentTaskId", task.getParentTaskId());
//            map.put("tenantId", task.getTenantId());
//            String value = task.getProcessDefinitionId().split(":")[0];
//            map.put("processKey", value);
//            map.put("processName",map1.get(value));
//
//            SysUser sysUser = sysUserService.selectUserById(Long.valueOf(task.getAssignee()));
//            map.put("assigneeUser", sysUser.getNickName());
//            listmap.add(map);
//        }
//
//        return AjaxResult.success(listmap);
//    }


    @GetMapping("/myProcessStart")
    public AjaxResult myProcess(){
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Page<TbFlow> page = new Page<>(pageDomain.getPageNum(), pageDomain.getPageSize());
        QueryWrapper<TbFlow> createUserId1 = new QueryWrapper<TbFlow>().eq("CreateUserId", SecurityUtils.getUserId());
        Page<TbFlow> createUserId = tbFlowService.page(page, createUserId1);
        return AjaxResult.success(createUserId);
    }

    private Map<String,String> processMap(){
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        Map<String,String> map = new HashMap<>();
        list.forEach(obj ->{
            map.put(obj.getKey(),obj.getName());
        });
        return map;
    }
}
