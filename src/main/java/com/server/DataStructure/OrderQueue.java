package com.server.DataStructure;

/**
 * 数组实现队列
 */
public class OrderQueue {

    public String[] str ;

    public int head;

    public int tail;

    public OrderQueue(int n ){
        str = new String[n];
        head=0;
        tail = head;
    }

    /**
     * 入队
     * @param data
     * @return
     */
    public Boolean enqueue(String data){
        if(tail==str.length&&head==0) {
            System.out.println("queue is full！");
            //可以动态扩容
            return false;
        }
        //数据搬迁
        if(tail==str.length&&head>0){
            int len = tail-head;
            for(int i = 0;i<len;i++){
                str[i]= str[head];
                head++;
            }
            tail = len;
            head=0;
        }
        str[tail]=data;
        tail++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        if(head==tail) {
            System.out.println("queue is empty!");
            tail=head=0;
            return "";
        }
        String result = str[head];
        head++;
        return result;
    }

    /**
     * 打印队列
     */
    public void display(){
        System.out.println("head: " +head+"  tail : "+tail);
        for(int i = head;i<tail;i++){
            System.out.print(str[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        OrderQueue o = new OrderQueue(5);
        o.dequeue();
        o.display();
        o.enqueue("a");
        o.enqueue("b");
        o.enqueue("c");
        o.enqueue("d");
        o.enqueue("e");
        o.enqueue("f");
        o.display();
        o.dequeue();
        o.dequeue();
        o.display();
        o.enqueue("g");
        o.display();
    }
}
