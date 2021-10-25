package com.dream.web.controller.deploy;


import com.dream.common.core.domain.AjaxResult;
import com.dream.deploy.domain.ProcessDeploy;
import com.dream.deploy.service.ProcessDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProcessDeployController {
    @Autowired
    ProcessDeployService processDeployService;

    @GetMapping("/test/select")
    public AjaxResult stud(){
        //查询全部
        List<ProcessDeploy> list = processDeployService.list();
        return AjaxResult.success(list);
    }

    @GetMapping("/test/add")
    public boolean app(){
        //添加
        ProcessDeploy entity = new ProcessDeploy();
        entity.setUpdateTime(LocalDateTime.now());
        entity.setCreateTime(LocalDateTime.now());
        entity.setProcessDeployer("52");
        entity.setProcessTime(LocalDateTime.now());
        entity.setProcessState("1100");
        entity.setProcessZip("1100");
        entity.setProcessName("101");
        entity.setProcessId(2);
//        boolean insert =entity.insert();
        boolean save = processDeployService.save(entity);
        return save;
    }

    @GetMapping("/test/0")
    public boolean update(){
        //修改
        ProcessDeploy entity = new ProcessDeploy();
        entity.setUpdateTime(LocalDateTime.now());
        entity.setCreateTime(LocalDateTime.now());
        entity.setProcessDeployer("bbh");
        entity.setProcessTime(LocalDateTime.now());
        entity.setProcessState("552225");
        entity.setProcessZip("552225");
        entity.setProcessName("552225");
        entity.setProcessId(5);
//        boolean insert =entity.insert();
        boolean save = processDeployService.updateById(entity);
        System.out.println(entity);
        //修改
//        boolean b1 = entity.updateById();
        return save;
    }

    @GetMapping("/test/delete")
    public boolean delete(){
        //删除
        boolean b = processDeployService.removeById(4);
        return b;
    }

}
