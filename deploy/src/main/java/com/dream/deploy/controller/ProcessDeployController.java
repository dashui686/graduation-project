package com.dream.deploy.controller;

import com.dream.deploy.domain.ProcessDeploy;
import com.dream.deploy.service.ProcessDeployService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProcessDeployController {
    @Autowired
    ProcessDeployService processDeployService;
//
//    @GetMapping("test/1")
//    public List<ProcessDeploy> stud(){
//        //查询全部
//        List<ProcessDeploy> list = processDeployService.list();
//        return list;
//
//
//    }
//
//    @GetMapping("test/2")
//    public boolean app(){
//        //添加
//        ProcessDeploy entity = new ProcessDeploy();
//        entity.setSsex("女");
//        entity.setSage("2000");
//        entity.setSname("bbh");
////        entity.setSID("09");
////        boolean insert =entity.insert();
//        boolean save = processDeployService.save(entity);
//        return save;
//    }
//
//    @GetMapping("test/3")
//    public boolean update(){
//        //修改
//        ProcessDeploy entity = new ProcessDeploy();
//        entity.setSsex("女");
//        entity.setSage("2000");
//        entity.setSname("yyy");
////        entity.setSID("09");
////        boolean insert =entity.insert();
//        boolean save = processDeployService.save(entity);
//        System.out.println(entity);
//        //修改
//        boolean b1 = entity.updateById();
//        return save;
//    }
//
//    @GetMapping("test/4")
//    public boolean delete(){
//        //删除
//        boolean b = processDeployService.removeById(10);
//        return b;
//    }

}
