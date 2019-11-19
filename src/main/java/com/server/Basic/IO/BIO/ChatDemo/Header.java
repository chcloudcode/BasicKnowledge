package com.server.Basic.IO.BIO.ChatDemo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Header implements Serializable {
    //消息来源地
    private String host;
    //消息来源地的端口
    private int port;
    //发送消息用户的昵称
    private String nickName;

    public Header(String host, int port, String nickName) {
        this.host = host;
        this.port = port;
        this.nickName = nickName;
    }
}
