package com.dream.process.service;

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
    boolean addLeave(PleaseLeave pleaseLeave, Integer assignee) throws Exception;

    @Transactional
    PleaseLeave getBusiness(String id);

    void approvePleaseLeave(ProcessApproveVo processApproveVo) throws TaskOfNullException;

    void unApprovePleaseLeave(ProcessApproveVo processApproveVo) throws TaskOfNullException;
}
