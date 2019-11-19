package com.server.Basic.IO.BIO.BIODemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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
            while(true){
                System.out.println("thread id : "+ Thread.currentThread().getId()+"  read ........");
                String result = in.readLine();
                if(result.equals("")|| result.equals("exit")) {
                    System.out.println("thread id : "+ Thread.currentThread().getId()+" 结束 ");
                    break;
                }
                System.out.println("thead id:  "+Thread.currentThread().getId()+ "  客户端数据："+ result);
                out.println("来自服务器的 消息："+System.currentTimeMillis());
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
