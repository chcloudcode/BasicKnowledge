package com.server.Basic.IO.BIO.BIODemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {

    private final static int BIO_SERVER_PORT =  9999;

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try{
            serverSocket = new ServerSocket(BIO_SERVER_PORT);
            System.out.println("服务器启动了");
            while(true){
                System.out.println("等待连接。。。");
                socket = serverSocket.accept();
                System.out.println("来了一个新连接");
                threadPool.execute(new BIOServerHandler(socket));
            }

        }catch (Exception e){
            System.out.println(e);
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
