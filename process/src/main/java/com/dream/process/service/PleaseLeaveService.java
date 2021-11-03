package com.dream.process.service;

import com.dream.process.domain.PleaseLeave;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface PleaseLeaveService extends IService<PleaseLeave> {

       public boolean addLeave(PleaseLeave pleaseLeave, Integer assignee) throws Exception;
}
