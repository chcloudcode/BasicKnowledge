package com.server.basic.DataStructure;


public class OrderStack {

    public String[] element ;
    public int count = 0;
    public int size = 0;

    public OrderStack(int n ){
        element = new String[n];
        this.size=n;
        this.count=0;
    }

    public Boolean push(String data){
        if(count==size) {
            System.out.println("stack is overflow!");
            return false;
        }
        element[count]=data;
        count++;
        return true;
    }

    public String pop(){
        if(count == 0 ) {
            System.out.println("stack is empty!");
            return "";
        }
        String result = element[count-1];
        count--;
        return result;
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public String getTop(){
        if(0==count){
            System.out.println("stack is empty!");
            return "";
        }
        return element[count-1];
    }

}
