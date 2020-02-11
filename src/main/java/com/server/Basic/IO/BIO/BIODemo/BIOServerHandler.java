package com.server.Basic.IO.BIO.BIODemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BIOServerHandler implements Runnable {

    private Socket socket;

    public BIOServerHandler(Socket socket) {
     this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            Scanner sin = new Scanner(System.in);
            while(true){
                System.out.println("Thread ID : "+ Thread.currentThread().getId()+"  准备读。。。。");
                //准备读取客户端发送的消息
                String result = in.readLine();
                if(result.equals("")|| result.equals("exit")) {
                    System.out.println("Thread ID : "+ Thread.currentThread().getId()+" ，服务器端收到该客户端结束通信指令！ ");
                    break;
                }
                System.out.println("thead id:  "+Thread.currentThread().getId()+ "  客户端数据："+ result);
                String response = sin.nextLine();
                //响应给客户端的消息
                out.println("服务器响应消息："+response);
                out.flush();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
