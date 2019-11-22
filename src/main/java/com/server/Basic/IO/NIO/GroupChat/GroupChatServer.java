package com.server.Basic.IO.NIO.GroupChat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊——服务端
 * 1. 监听 客户端上下线
 * 2. 同客户端通信 并实现 消息转发
 */
public class GroupChatServer {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    private int GROUP_CHAT_PORT = 9998;

    //服务端初始化工作
    public GroupChatServer(){
        try {
            selector=Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(GROUP_CHAT_PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端启动成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //监听客户端上下线，及读写事件
    public void listen(){
        try{
            while(true){
                int clients = selector.select(1000);
                if(clients>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        //客户端连接事件
                        if(key.isAcceptable()){
                            SocketChannel channel = serverSocketChannel.accept();
                            channel.configureBlocking(false);
                            channel.register(selector,SelectionKey.OP_READ);
                            System.out.println(channel.getRemoteAddress() + " 上线 ");
                        }

                        //客户端 往 通道发送数据，通道有数据可读事件
                        if(key.isReadable()){
                           ReceiveClientData(key);
                        }
                        // 记住 防止重复 操作
                        iterator.remove();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    //接受客户端消息 并 转发
    private void ReceiveClientData(SelectionKey key) {
        SocketChannel channel = null;
        try{
            channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            if(count>0){
                String msg = new String(byteBuffer.array());
                System.out.println("来自客户端："+msg.trim());
                //因为是群聊，消息转发到其他客户端
                MessageForward(msg,channel);
            }
        }catch(Exception e){
            try {
                System.out.println(channel.getRemoteAddress()+" 下线了  ");
                //取消注册
                key.cancel ();
                //关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }

    private void MessageForward(String msg, SocketChannel self) throws Exception {
        System.out.println("消息转发中。。。。");

        Iterator<SelectionKey> iterator = selector.keys().iterator();
        while (iterator.hasNext()){
            Channel channel = iterator.next().channel();
            if(channel instanceof SocketChannel && channel!=self){
                SocketChannel dest = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes("utf-8"));
                dest.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {

        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();

    }


}
