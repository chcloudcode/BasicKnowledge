package com.server.framework.RabbitMQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private RabbitMsgSender rabbitMsgSender;

    @RequestMapping("/send")
    public void TestRabbitSend(@RequestParam String msg) throws Exception {
        RabbitMsg rabbitMsg = new RabbitMsg("20190621001","msg001",msg,System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        rabbitMsgSender.send(rabbitMsg);
    }
}
