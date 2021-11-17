package com.dream.process.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 会议室表
 * @TableName tb_conferenceroom
 */
@TableName(value ="tb_conferenceroom")
@Data
public class TbConferenceroom implements Serializable {
    /**
     * 会议室Id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会议室名称
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 会议室容纳人数
     */
    @TableField(value = "Capacity")
    private Integer capacity;

    /**
     * 会议室位置
     */
    @TableField(value = "Position")
    private String position;

    /**
     * 使用状态
     */
    @TableField(value = "State")
    private Object state;

    /**
     * 创建人
     */
    @TableField(value = "CreateUserId")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "CreateDate")
    private LocalDateTime createDate;

    /**
     * 修改人
     */
    @TableField(value = "UpdateUserId")
    private Integer updateUserId;

    /**
     * 修改时间
     */
    @TableField(value = "UpdateDate")
    private LocalDateTime updateDate;

    /**
     * 会议室图片
     */
    @TableField(value = "Image")
    private String image;

    /**
     * 附加描述
     */
    @TableField(value = "Additional")
    private String additional;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        TbConferenceroom other = (TbConferenceroom) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCapacity() == null ? other.getCapacity() == null : this.getCapacity().equals(other.getCapacity()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getAdditional() == null ? other.getAdditional() == null : this.getAdditional().equals(other.getAdditional()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCapacity() == null) ? 0 : getCapacity().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getAdditional() == null) ? 0 : getAdditional().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", capacity=").append(capacity);
        sb.append(", position=").append(position);
        sb.append(", state=").append(state);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", image=").append(image);
        sb.append(", additional=").append(additional);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}