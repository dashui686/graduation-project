package com.dream.web.controller.process;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.domain.TbGoodsapply;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.service.PleaseLeaveService;
import com.dream.process.service.TbGoodsapplyService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/test/goodsApply")
public class GoodsApplyController {
    @Autowired
    TbGoodsapplyService tbGoodsapplyService;
    @Autowired
    TaskService taskService;

    @PostMapping
    public AjaxResult myLeave(@Validated @RequestBody TbGoodsapply tbGoodsapply){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        tbGoodsapply.setCreateUser(loginUser.getUserId().intValue());
        tbGoodsapply.setCreateDate(LocalDateTime.now());
        return tbGoodsapplyService.add(tbGoodsapply);
    }

    @GetMapping("/{id}")
    public AjaxResult getById(@PathVariable String id){
        TbGoodsapply byId = tbGoodsapplyService.getById(id);
        return AjaxResult.toAjax(byId!=null,byId);
    }

    @GetMapping("/getBusiness/{id}")
    public AjaxResult getBusiness(@PathVariable String id){
        TbGoodsapply business1 = tbGoodsapplyService.getBusiness(id);
        return AjaxResult.toAjax(business1!=null,business1);
    }

    @PostMapping("/approveProcess")
    public AjaxResult approveProcess(@Validated @RequestBody ProcessApproveVo processApproveVo){
        System.out.println(processApproveVo);
        return tbGoodsapplyService.approvePleaseLeave(processApproveVo);
    }
}
