package com.server.Basic.IO.Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        // 创建两个线程组  boosGroup workerGroup
        // boosGroup 只处理客户端连接请求
        // workerGroup  处理与客户端的读写业务请求
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建服务器端的启动对象  ServerBootstrap ，配置参数
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup) //设置两个线程组
                .channel(NioServerSocketChannel.class)  //使用NioServerSocketChannel 作为服务器端的通道实现
                .option(ChannelOption.SO_BACKLOG, 1024) //设置线程队列等待连接个数
                .childOption(ChannelOption.SO_KEEPALIVE, true) //设置保持活动连接状态
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new NettyServerHandler());
                    }
                });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }

}
