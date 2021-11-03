package com.dream.process.utils;

import com.dream.process.entity.TaskInstance;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessUtils {
    @Autowired
    private RepositoryService repositoryService;


    public Map<String,String> getProcessName(){
        Map<String,String> m  = new HashMap<>();
        repositoryService.createProcessDefinitionQuery().list().forEach(obj->{
            m.put(obj.getKey(),obj.getName());
        });
        return m;
    }

    public List<TaskInstance> getTaskList(List<Task> list){
        List<TaskInstance> list1 = new ArrayList<>();
        Map<String, String> processName = getProcessName();
        for (Task task : list) {
            TaskInstance taskInstance = new TaskInstance();
            taskInstance.setId(task.getId());
            taskInstance.setName(task.getName());
            taskInstance.setDefinitionKey(task.getProcessDefinitionId());
            taskInstance.setProcessName(processName.get(taskInstance.getDefinitionKey()));
            taskInstance.setCreateTime(task.getCreateTime());
            taskInstance.setAssignee(task.getAssignee());
            list1.add(taskInstance);
        }
        return list1;
    }
}
