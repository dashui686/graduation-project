package com.dream.process.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessApproveVo {
    private String assignee;
    private String processInstanceId;
    private String taskId;
    private String approveReason;
    private String state;

}
