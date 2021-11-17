package com.dream.process.service;

import com.dream.process.domain.TbConferenceroom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface TbConferenceroomService extends IService<TbConferenceroom> {

    /**
     * 查询会议室信息
     * @param tbConferenceroom
     * @return
     */
    public List<TbConferenceroom> queryInfo(TbConferenceroom tbConferenceroom);

}
