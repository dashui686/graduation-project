package com.dream.deploy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 
 * @TableName process_deploy
 */
@TableName(value ="process_deploy")
@Data
public class ProcessDeploy extends Model<ProcessDeploy> implements Serializable {
    /**
     * ID自增列
     */
    @TableId(value = "ProcessId", type = IdType.AUTO)
    private Integer processId;

    /**
     * 流程实例名
     */
    @TableField(value = "ProcessName")
    private String processName;

    /**
     * 流程实例ZIP路径
     */
    @TableField(value = "ProcessZip")
    private String processZip;

    /**
     * 流程部署状态 
     */
    @TableField(value = "ProcessState")
    private String processState;

    /**
     * 流程部署时间
     */
    @TableField(value = "ProcessTime")
    private LocalDateTime processTime;

    /**
     * 流程部署人
     */
    @TableField(value = "ProcessDeployer")
    private String processDeployer;

    /**
     * 创建时间
     */
    @TableField(value = "CreateTime")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "UpdateTime")
    private LocalDateTime updateTime;

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
        ProcessDeploy other = (ProcessDeploy) that;
        return (this.getProcessId() == null ? other.getProcessId() == null : this.getProcessId().equals(other.getProcessId()))
            && (this.getProcessName() == null ? other.getProcessName() == null : this.getProcessName().equals(other.getProcessName()))
            && (this.getProcessZip() == null ? other.getProcessZip() == null : this.getProcessZip().equals(other.getProcessZip()))
            && (this.getProcessState() == null ? other.getProcessState() == null : this.getProcessState().equals(other.getProcessState()))
            && (this.getProcessTime() == null ? other.getProcessTime() == null : this.getProcessTime().equals(other.getProcessTime()))
            && (this.getProcessDeployer() == null ? other.getProcessDeployer() == null : this.getProcessDeployer().equals(other.getProcessDeployer()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProcessId() == null) ? 0 : getProcessId().hashCode());
        result = prime * result + ((getProcessName() == null) ? 0 : getProcessName().hashCode());
        result = prime * result + ((getProcessZip() == null) ? 0 : getProcessZip().hashCode());
        result = prime * result + ((getProcessState() == null) ? 0 : getProcessState().hashCode());
        result = prime * result + ((getProcessTime() == null) ? 0 : getProcessTime().hashCode());
        result = prime * result + ((getProcessDeployer() == null) ? 0 : getProcessDeployer().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", processId=").append(processId);
        sb.append(", processName=").append(processName);
        sb.append(", processZip=").append(processZip);
        sb.append(", processState=").append(processState);
        sb.append(", processTime=").append(processTime);
        sb.append(", processDeployer=").append(processDeployer);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}