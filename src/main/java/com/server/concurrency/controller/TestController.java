package com.server.concurrency.controller;

public class TestController extends Thread{

    @Override
    public void run() {
        while(true) {
            System.out.println(this.currentThread().getName());
        }
    }


    public static void main(String[] args) {
        TestController th =  new TestController();
        th.start();
    }
}
