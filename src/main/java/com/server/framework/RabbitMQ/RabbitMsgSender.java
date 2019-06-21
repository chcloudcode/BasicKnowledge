package com.server.framework.RabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMsgSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(RabbitMsg msg) throws Exception{
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(msg.getMessageId());
        rabbitTemplate.convertAndSend("exchange01","msg.key",msg.getContent(),correlationData);
    }


}
