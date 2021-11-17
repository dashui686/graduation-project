package com.dream.web.controller.process;

import com.dream.common.core.domain.AjaxResult;
import com.dream.common.core.domain.model.LoginUser;
import com.dream.common.utils.SecurityUtils;
import com.dream.process.domain.FlowConferenceroom;
import com.dream.process.domain.TbConferenceroom;
import com.dream.process.service.FlowConferenceroomService;
import com.dream.process.service.TbConferenceroomService;
import org.activiti.engine.TaskService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/test/conference")
public class ConferenceRoomController {

    @Resource
    TbConferenceroomService tbConferenceroomService;
    @Resource
    TaskService taskService;
    @Resource
    FlowConferenceroomService flowConferenceroomService;

    /**
     * 查询会议室
     * @param tbConferenceroom
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(@Validated TbConferenceroom tbConferenceroom){
        System.out.println(tbConferenceroom);
        List<TbConferenceroom> tbConferencerooms = tbConferenceroomService.queryInfo(tbConferenceroom);
        return AjaxResult.toAjax(tbConferenceroom!=null,tbConferencerooms);
    }


    @PostMapping
    public AjaxResult addConference(@Validated @RequestBody FlowConferenceroom flowConferenceroom){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        flowConferenceroom.setCreateUser(loginUser.getUserId().intValue());
        flowConferenceroom.setCreateDate(LocalDateTime.now());
        return flowConferenceroomService.addLeave(flowConferenceroom);
    }



}
