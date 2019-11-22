package com.server.Basic.IO.NIO.GroupChat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 群聊系统——客户端
 * 1. 同服务器通信
 * 2. 接受服务器消息的转发
 */
public class GroupChatClient {

    private SocketChannel socketChannel;

    private final InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",9998);

    private Selector selector;

    private String username;

    //客户端初始化
    public GroupChatClient(){
        try{
            selector = Selector.open();
            socketChannel = SocketChannel.open(serverAddress);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println("客户端："+ username+"  启动成功！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //发送数据到服务端
    public void sendDataToServer(String msg) {
        String data = username + "： "+ msg;
        ByteBuffer byteBuffer = null;
        try {
            byteBuffer = ByteBuffer.wrap(data.getBytes("utf-8"));
            socketChannel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取服务器返回的 消息
    public void ReceiveResponseFromServer(){
        try {
            int count = selector.select();
            if(count>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if(key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        System.out.println(new String(byteBuffer.array()).trim());
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        GroupChatClient groupChatClient = new GroupChatClient();

        new Thread(){
            @Override
            public void run() {
                while(true){
                    groupChatClient.ReceiveResponseFromServer();
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            groupChatClient.sendDataToServer(s);
        }
    }

}
