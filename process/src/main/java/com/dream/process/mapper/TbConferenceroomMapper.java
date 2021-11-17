package com.dream.process.mapper;

import com.dream.process.domain.TbConferenceroom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.dream.process.domain.TbConferenceroom
 */
@Mapper
public interface TbConferenceroomMapper extends BaseMapper<TbConferenceroom> {
    /**
     * 查询会议室信息
     * @param tbConferenceroom
     * @return
     */
    public List<TbConferenceroom> queryInfo(@Param("conference") TbConferenceroom tbConferenceroom);


}




