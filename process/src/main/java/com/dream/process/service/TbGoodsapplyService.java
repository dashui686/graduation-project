package com.dream.process.service;

import com.dream.common.core.domain.AjaxResult;
import com.dream.process.domain.TbGoodsapply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dream.process.entity.ProcessApproveVo;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public interface TbGoodsapplyService extends IService<TbGoodsapply> {

    @Transactional
    AjaxResult add(TbGoodsapply tbGoodsapply);

    TbGoodsapply getBusiness(String id);

    AjaxResult approvePleaseLeave(ProcessApproveVo processApproveVo);

    AjaxResult unApprovePleaseLeave(ProcessApproveVo processApproveVo);
}
