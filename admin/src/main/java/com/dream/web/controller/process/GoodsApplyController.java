package com.dream.web.controller.process;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.service.PleaseLeaveService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test/goodsApply")
public class GoodsApplyController {
    @Autowired
    PleaseLeaveService pleaseLeaveService;
    @Autowired
    TaskService taskService;
    @PostMapping
    public AjaxResult myLeave(@Validated @RequestBody PleaseLeave pleaseLeave){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        pleaseLeave.setUserId(loginUser.getUserId().intValue());
        pleaseLeave.setDeptId(loginUser.getDeptId().intValue());
        return pleaseLeaveService.addLeave(pleaseLeave);
    }

    @GetMapping("/{id}")
    public AjaxResult getById(@PathVariable String id){
        PleaseLeave business = pleaseLeaveService.getById(id);
        return AjaxResult.toAjax(business!=null,business);
    }

    @GetMapping("/getBusiness/{id}")
    public AjaxResult getBusiness(@PathVariable String id){
        PleaseLeave business = pleaseLeaveService.getBusiness(id);
        return AjaxResult.toAjax(business!=null,business);
    }

    @PostMapping("/approveProcess")
    public AjaxResult approveProcess(@Validated @RequestBody ProcessApproveVo processApproveVo){
        return pleaseLeaveService.approvePleaseLeave(processApproveVo);
    }
}
