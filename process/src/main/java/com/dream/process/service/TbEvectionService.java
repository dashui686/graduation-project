package com.dream.process.service;

import com.dream.common.core.domain.AjaxResult;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.domain.TbEvection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dream.process.entity.ProcessApproveVo;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public interface TbEvectionService extends IService<TbEvection> {

    @Transactional
    AjaxResult addLeave(TbEvection pleaseLeave);

    TbEvection getBusiness(String id);

    AjaxResult approvePleaseLeave(ProcessApproveVo processApproveVo);

    AjaxResult unApprovePleaseLeave(ProcessApproveVo processApproveVo);
}
