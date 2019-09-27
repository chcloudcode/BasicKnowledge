package com.server.DesignPattern.StrategyPattern.example02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    AsyncTaskHandlerFactory asyncTaskHandlerFactory;

    @GetMapping("/executeTask")
    public void executeTask(@RequestParam String taskTypeCode){
        BaseHandler baseHandler = asyncTaskHandlerFactory.getTaskByCode(taskTypeCode);
        baseHandler.execute();
    }
}
