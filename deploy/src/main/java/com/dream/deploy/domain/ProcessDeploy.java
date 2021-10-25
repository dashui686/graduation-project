package com.dream.deploy.domain;


import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName process_deploy
 */
public class ProcessDeploy extends Model<ProcessDeploy> implements Serializable {
    /**
     * ID自增列
     */
    private Integer processId;

    /**
     * 流程实例名
     */
    private String processName;

    /**
     * 流程实例ZIP路径
     */
    private String processZip;

    /**
     * 流程部署状态 
     */
    private String processState;

    /**
     * 流程部署时间
     */
    private Date processTime;

    /**
     * 流程部署人
     */
    private String processDeployer;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * ID自增列
     */
    public Integer getProcessId() {
        return processId;
    }

    /**
     * ID自增列
     */
    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    /**
     * 流程实例名
     */
    public String getProcessName() {
        return processName;
    }

    /**
     * 流程实例名
     */
    public void setProcessName(String processName) {
        this.processName = processName;
    }

    /**
     * 流程实例ZIP路径
     */
    public String getProcessZip() {
        return processZip;
    }

    /**
     * 流程实例ZIP路径
     */
    public void setProcessZip(String processZip) {
        this.processZip = processZip;
    }

    /**
     * 流程部署状态 
     */
    public String getProcessState() {
        return processState;
    }

    /**
     * 流程部署状态 
     */
    public void setProcessState(String processState) {
        this.processState = processState;
    }

    /**
     * 流程部署时间
     */
    public Date getProcessTime() {
        return processTime;
    }

    /**
     * 流程部署时间
     */
    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    /**
     * 流程部署人
     */
    public String getProcessDeployer() {
        return processDeployer;
    }

    /**
     * 流程部署人
     */
    public void setProcessDeployer(String processDeployer) {
        this.processDeployer = processDeployer;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

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