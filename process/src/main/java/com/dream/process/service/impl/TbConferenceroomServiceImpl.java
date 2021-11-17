package com.dream.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.process.domain.TbConferenceroom;
import com.dream.process.service.TbConferenceroomService;
import com.dream.process.mapper.TbConferenceroomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class TbConferenceroomServiceImpl extends ServiceImpl<TbConferenceroomMapper, TbConferenceroom>
    implements TbConferenceroomService{

    @Resource
    TbConferenceroomMapper tbConferenceroomMapper;

    @Override
    public List<TbConferenceroom> queryInfo(TbConferenceroom tbConferenceroom) {
        System.out.println(tbConferenceroom);
        return tbConferenceroomMapper.queryInfo(tbConferenceroom);
    }
}




