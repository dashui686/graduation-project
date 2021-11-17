package com.dream.process.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 会议室预定表
 * @TableName flow_conferenceroom
 */
@TableName(value ="flow_conferenceroom")
@Data
public class FlowConferenceroom implements Serializable {
    /**
     * 预定Id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会议室Id
     */
    @TableField(value = "ConferenceRoom")
    private Integer conferenceRoom;

    /**
     * 开始时间
     */
    @TableField(value = "BeginDate")
    private LocalDateTime beginDate;

    /**
     * 结束时间
     */
    @TableField(value = "EndDate")
    private LocalDateTime endDate;

    /**
     * 申请理由
     */
    @TableField(value = "Reason")
    private String reason;

    /**
     * 申请人
     */
    @TableField(value = "CreateUser")
    private Integer createUser;

    /**
     * 创建时间
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String assignee;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FlowConferenceroom other = (FlowConferenceroom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getConferenceRoom() == null ? other.getConferenceRoom() == null : this.getConferenceRoom().equals(other.getConferenceRoom()))
            && (this.getBeginDate() == null ? other.getBeginDate() == null : this.getBeginDate().equals(other.getBeginDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getConferenceRoom() == null) ? 0 : getConferenceRoom().hashCode());
        result = prime * result + ((getBeginDate() == null) ? 0 : getBeginDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", conferenceRoom=").append(conferenceRoom);
        sb.append(", beginDate=").append(beginDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", reason=").append(reason);
        sb.append(", createUser=").append(createUser);
        sb.append(", createDate=").append(createDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}