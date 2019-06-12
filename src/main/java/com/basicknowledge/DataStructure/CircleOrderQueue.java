package com.basicknowledge.DataStructure;

/**
 * 基于数组的循环队列
 */

public class CircleOrderQueue {

    public String[] arr;

    public int head;

    public int tail;

    public CircleOrderQueue(int len){
        arr = new String[len];
        head=0;
        tail=head;
    }

    /**
     * 入队
     * @param data
     * @return
     */
    public Boolean enqueue(String data){
        if((tail+1)%arr.length==head){
            System.out.println("queue is full!");
            return false;
        }
        arr[tail]=data;
        tail=(tail+1)%arr.length;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String dequeue(){
        if(head==tail) {
            System.out.println("queue is empty ");
            return "";
        }
        String result = arr[head];
        head=(head+1)%arr.length;
        return result;
    }

    public void display(){
        int q = head;
        while(q!=tail){
            System.out.print(arr[q]+" ");
            q=(q+1)%arr.length;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircleOrderQueue c = new CircleOrderQueue(8);
        c.enqueue("a");c.enqueue("b"); c.enqueue("c");
        c.enqueue("d");c.enqueue("e");c.enqueue("f");
        c.enqueue("g");c.enqueue("h");
        c.display();
        c.dequeue();c.dequeue();c.dequeue();c.dequeue();
        c.display();
        c.enqueue("i");
        c.display();
        c.dequeue();c.dequeue();c.dequeue();c.dequeue();
        c.dequeue();
    }
}
