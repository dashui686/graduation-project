package com.dream.process.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName tb_flow
 */
@TableName(value ="tb_flow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbFlow extends Model<TbFlow> implements Serializable {
    /**
     * id
     */
    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    /**
     * 流程实例ID
     */
    @TableField(value = "ProcessInstanceId")
    private String processInstanceId;

    /**
     * 流程Key
     */
    @TableField(value = "ProcessInstanceDefinitionKey")
    private String processInstanceDefinitionKey;

    /**
     * 流程Name
     */
    @TableField(value = "ProcessInstanceDefinitionName")
    private String processInstanceDefinitionName;

    /**
     * 当前任务ID
     */
    @TableField(value = "TaskId")
    private String taskId;

    /**
     * 当前任务名
     */
    @TableField(value = "TaskNaem")
    private String taskNaem;

    /**
     * 创建人
     */
    @TableField(value = "CreateUserId")
    private String createUserId;

    /**
     * 创建人姓名
     */
    @TableField(value = "CreateUserName")
    private String createUserName;

    /**
     * 处理人员
     */
    @TableField(value = "Assignee")
    private String assignee;

    /**
     * 处理
     */
    @TableField(value = "Roles")
    private String roles;

    /**
     * 业务ID
     */
    @TableField(value = "BusinessKey")
    private String businessKey;

    /**
     * 状态
     */
    @TableField(value = "ProcessState")
    private Integer processState;

    /**
     * 原因
     */
    @TableField(value = "Reason")
    private String reason;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CreateDate")
    private LocalDateTime createDate;

    /**
     * 用户
     */
    @TableField(value = "CandidateUsers")
    private String candidateUsers;

    /**
     * 组
     */
    @TableField(value = "CandidateGroups")
    private String candidateGroups;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceDefinitionKey() {
        return processInstanceDefinitionKey;
    }

    public void setProcessInstanceDefinitionKey(String processInstanceDefinitionKey) {
        this.processInstanceDefinitionKey = processInstanceDefinitionKey;
    }

    public String getProcessInstanceDefinitionName() {
        return processInstanceDefinitionName;
    }

    public void setProcessInstanceDefinitionName(String processInstanceDefinitionName) {
        this.processInstanceDefinitionName = processInstanceDefinitionName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskNaem() {
        return taskNaem;
    }

    public void setTaskNaem(String taskNaem) {
        this.taskNaem = taskNaem;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Integer getProcessState() {
        return processState;
    }

    public void setProcessState(Integer processState) {
        this.processState = processState;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCandidateUsers() {
        return candidateUsers;
    }

    public void setCandidateUsers(String candidateUsers) {
        this.candidateUsers = candidateUsers;
    }

    public String getCandidateGroups() {
        return candidateGroups;
    }

    public void setCandidateGroups(String candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        TbFlow other = (TbFlow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProcessInstanceId() == null ? other.getProcessInstanceId() == null : this.getProcessInstanceId().equals(other.getProcessInstanceId()))
            && (this.getProcessInstanceDefinitionKey() == null ? other.getProcessInstanceDefinitionKey() == null : this.getProcessInstanceDefinitionKey().equals(other.getProcessInstanceDefinitionKey()))
            && (this.getProcessInstanceDefinitionName() == null ? other.getProcessInstanceDefinitionName() == null : this.getProcessInstanceDefinitionName().equals(other.getProcessInstanceDefinitionName()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getTaskNaem() == null ? other.getTaskNaem() == null : this.getTaskNaem().equals(other.getTaskNaem()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateUserName() == null ? other.getCreateUserName() == null : this.getCreateUserName().equals(other.getCreateUserName()))
            && (this.getAssignee() == null ? other.getAssignee() == null : this.getAssignee().equals(other.getAssignee()))
            && (this.getRoles() == null ? other.getRoles() == null : this.getRoles().equals(other.getRoles()))
            && (this.getBusinessKey() == null ? other.getBusinessKey() == null : this.getBusinessKey().equals(other.getBusinessKey()))
            && (this.getProcessState() == null ? other.getProcessState() == null : this.getProcessState().equals(other.getProcessState()))
            && (this.getReason() == null ? other.getReason() == null : this.getReason().equals(other.getReason()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getCandidateUsers() == null ? other.getCandidateUsers() == null : this.getCandidateUsers().equals(other.getCandidateUsers()))
            && (this.getCandidateGroups() == null ? other.getCandidateGroups() == null : this.getCandidateGroups().equals(other.getCandidateGroups()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProcessInstanceId() == null) ? 0 : getProcessInstanceId().hashCode());
        result = prime * result + ((getProcessInstanceDefinitionKey() == null) ? 0 : getProcessInstanceDefinitionKey().hashCode());
        result = prime * result + ((getProcessInstanceDefinitionName() == null) ? 0 : getProcessInstanceDefinitionName().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getTaskNaem() == null) ? 0 : getTaskNaem().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateUserName() == null) ? 0 : getCreateUserName().hashCode());
        result = prime * result + ((getAssignee() == null) ? 0 : getAssignee().hashCode());
        result = prime * result + ((getRoles() == null) ? 0 : getRoles().hashCode());
        result = prime * result + ((getBusinessKey() == null) ? 0 : getBusinessKey().hashCode());
        result = prime * result + ((getProcessState() == null) ? 0 : getProcessState().hashCode());
        result = prime * result + ((getReason() == null) ? 0 : getReason().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getCandidateUsers() == null) ? 0 : getCandidateUsers().hashCode());
        result = prime * result + ((getCandidateGroups() == null) ? 0 : getCandidateGroups().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", processInstanceId=").append(processInstanceId);
        sb.append(", processInstanceDefinitionKey=").append(processInstanceDefinitionKey);
        sb.append(", processInstanceDefinitionName=").append(processInstanceDefinitionName);
        sb.append(", taskId=").append(taskId);
        sb.append(", taskNaem=").append(taskNaem);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createUserName=").append(createUserName);
        sb.append(", assignee=").append(assignee);
        sb.append(", roles=").append(roles);
        sb.append(", businessKey=").append(businessKey);
        sb.append(", processState=").append(processState);
        sb.append(", reason=").append(reason);
        sb.append(", createDate=").append(createDate);
        sb.append(", candidateUsers=").append(candidateUsers);
        sb.append(", candidateGroups=").append(candidateGroups);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}