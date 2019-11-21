package com.server.Basic.IO.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * NIO C/S 通信 ——  客户端
 */
public class NIOClient {

    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",9999);

        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("尚未连接 ，可以干些其他的事情！");
            }
        }

        Scanner sin = new Scanner(System.in);
        String data = sin.nextLine();
        while (socketChannel.finishConnect()&&!data.equals("exit")){
            ByteBuffer byteBuffer = ByteBuffer.wrap(data.getBytes());
            System.out.println("客户端数据："+new String(byteBuffer.array()));
            //将 byteBuffer 数据写到  channel
            socketChannel.write(byteBuffer);
            data=sin.nextLine();
            byteBuffer.clear();
        }
    }

}
