package com.server.framework.RabbitMQ;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMsgReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "queue01",durable = "true"),
            exchange = @Exchange(name = "exchange01",durable = "true",type = "topic"),
            key = "msg.#"
    ))
    @RabbitHandler
    public void onRabbitMsg(@Payload String msg, @Headers Map<String,Object> headers , Channel channel)throws Exception{
        System.out.println("---------------收到消息，开始消费----------------");
        System.out.println("---------------消息ID："+msg+"--------------");
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        //手动ack
        channel.basicAck(deliveryTag,false);
    }
}
