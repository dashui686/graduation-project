package com.dream.web.controller.process;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.domain.TbEvection;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.service.PleaseLeaveService;
import com.dream.process.service.TbEvectionService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/evection")
public class MyEvectionController {
    @Autowired
    TbEvectionService evectionService;
    @Autowired
    TaskService taskService;
    @PostMapping
    public AjaxResult evection(@Validated @RequestBody TbEvection evection){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        evection.setUserId(loginUser.getUserId().intValue());
        evection.setDeptId(loginUser.getDeptId().intValue());
        return evectionService.addLeave(evection);
    }

    @GetMapping("/{id}")
    public AjaxResult getById(@PathVariable String id){
        TbEvection byId = evectionService.getById(id);
        return AjaxResult.toAjax(byId!=null,byId);
    }

    @GetMapping("/getBusiness/{id}")
    public AjaxResult getBusiness(@PathVariable String id){
        TbEvection business1 = evectionService.getBusiness(id);
        return AjaxResult.toAjax(business1!=null,business1);
    }

    @PostMapping("/approveProcess")
    public AjaxResult approveProcess(@Validated @RequestBody ProcessApproveVo processApproveVo){
        return evectionService.approvePleaseLeave(processApproveVo);
    }
}
