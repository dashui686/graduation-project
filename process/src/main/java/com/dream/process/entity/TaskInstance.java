package com.dream.process.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.activiti.bpmn.model.ActivitiListener;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.SuspensionState;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.task.DelegationState;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TaskInstance {
    private String assignee;
    private String id;
    private String parentTaskId;
    private String name;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime; // The time when the task has been created
    private String processInstanceId;
    private String definitionKey;
    private String processName;
    public String getDefinitionKey() {
        return definitionKey;
    }

    public void setDefinitionKey(String definitionKey) {
        String[] split = definitionKey.split(":");
        this.definitionKey = split[0];
    }
}
