package com.dream.process.service;

import com.dream.common.core.domain.AjaxResult;
import com.dream.process.domain.PleaseLeave;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dream.process.entity.ProcessApproveVo;
import com.dream.process.exception.TaskOfNullException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public interface PleaseLeaveService extends IService<PleaseLeave> {

    @Transactional
    AjaxResult addLeave(PleaseLeave pleaseLeave);

    PleaseLeave getBusiness(String id);

    AjaxResult approvePleaseLeave(ProcessApproveVo processApproveVo) ;

    AjaxResult unApprovePleaseLeave(ProcessApproveVo processApproveVo);
}
