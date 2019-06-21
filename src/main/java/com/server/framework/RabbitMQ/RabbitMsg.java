package com.server.framework.RabbitMQ;

import lombok.Data;

import java.io.Serializable;

@Data
public class RabbitMsg implements Serializable{

    private static final long seriaVersionUID = -1937376620038L;

    private String id;
    private String name;
    private String content;
    private String messageId;

    public RabbitMsg(){}

    public RabbitMsg(String id, String name, String content, String messageId) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.messageId = messageId;
    }
}
