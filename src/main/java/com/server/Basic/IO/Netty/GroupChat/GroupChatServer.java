package com.server.Basic.IO.Netty.GroupChat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Netty 群聊服务端
 */
public class GroupChatServer {

    private int prot;

    public GroupChatServer(int port) {
        this.prot=port;
    }

    public void run() throws InterruptedException {

        NioEventLoopGroup boosGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            //向pipline中添加解码器
                            channel.pipeline().addLast( new StringDecoder());
                            //向pipline中添加编码器
                            channel.pipeline().addLast(new StringEncoder());
                            //向pipline中添加 业务处理handler
                            channel.pipeline().addLast(new GroupChatServerHandler());
                        }
                    });
            System.out.println("Netty Group Chat launched successfully！");

            //Netty 异步模型 监听端口
            ChannelFuture channelFuture = serverBootstrap.bind(prot).sync();

            //监听关闭
            channelFuture.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatServer(8888).run();
    }
}
