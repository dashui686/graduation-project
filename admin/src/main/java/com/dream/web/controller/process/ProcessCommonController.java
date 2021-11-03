package com.dream.web.controller.process;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.entity.TaskInstance;
import com.dream.process.utils.ProcessUtils;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntityImpl;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/test/process")
public class ProcessCommonController {

    @Autowired
    TaskService taskService;
    @Autowired
    ProcessUtils processUtils;

    @GetMapping("/myProcess")
    public AjaxResult getMyProcess(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        List<Task> list = taskService.createTaskQuery().taskAssignee(loginUser.getUserId().toString()).list();
        List<TaskInstance> taskList = processUtils.getTaskList(list);
        return AjaxResult.success(taskList);
    }
}
