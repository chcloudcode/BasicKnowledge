package com.server.Basic.IO.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO C/S 通信 ——  服务端
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {

        // 多路复用 Selector 实例
        Selector selector = Selector.open();

        // 服务端通信 通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);//非阻塞
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));//绑定端口

        //注册 到 selector 并监听 它的  客户端连接事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        while(true){
            
            int select  = selector.select(1000);
            if(select==0){
//                System.out.println("轮询一秒，暂时没有监听到事件");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("监听到一个客户端连接事件，客户端Channel的hashCode"+socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(key.isReadable()){
                    System.out.println("监听到一个读事件");
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    System.out.println("旧position 位置 "+byteBuffer.position());
                    int start = byteBuffer.position();
                    //将channel数据 写到byteBuffer
                    socketChannel.read(byteBuffer);
                    System.out.println("新position 位置 "+byteBuffer.position());
                    int end = byteBuffer.position();
                    String result = new String(byteBuffer.array());
                    System.out.println("byteBuffer中的内容："+new String(byteBuffer.array()));
                    System.out.println("本次通信的内容："+ result.trim().substring(start,end));

                }
                iterator.remove();
            }
        }
    }

}
