package com.dream.process.entity;

import lombok.Data;

@Data
public class ProcessApproveVo {
    private String assignee;
    private String processInstanceId;
    private String taskId;
    private String approveReason;
    private String state;

}
