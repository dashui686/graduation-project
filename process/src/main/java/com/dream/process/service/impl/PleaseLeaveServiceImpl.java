package com.dream.process.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.process.domain.PleaseLeave;
import com.dream.process.service.PleaseLeaveService;
import com.dream.process.mapper.PleaseLeaveMapper;
import org.activiti.api.process.model.builders.StartProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class PleaseLeaveServiceImpl extends ServiceImpl<PleaseLeaveMapper, PleaseLeave>
    implements PleaseLeaveService{

    /**
     * 仓库
     */
    @Autowired
    private RepositoryService repositoryService;

    /**
     * 运行中的任务
     */
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    ProcessRuntime runtime;

    /**
     * 任务
     */
    @Autowired
    private TaskService taskService;

    /**
     * 历史记录
     */
    @Autowired
    private HistoryService historyService;

    @Override
    @Transactional
    public boolean addLeave(PleaseLeave pleaseLeave) throws Exception {
        boolean save = save(pleaseLeave);
        if(save){
            ProcessInstance myLeave = runtimeService.startProcessInstanceByKey("myLeave", pleaseLeave.getId().toString());

            System.out.println(myLeave.getId());
        }else{
            save = false;
            throw new Exception("启动流程错误");
        }
        return save;
    }
}




