package com.server.DataStructure;

/**
 * 链表实现队列
 */
public class ListQueue {

    public  Node head = null;

    public Node tail = head;

    public int count = 0;

    /**
     * 入队
     * @param data
     * @return
     */
    public Boolean enqueue(int data){
        Node newNode = new Node(data);
        if(tail==null){
            head=tail=newNode;
        }else{
            tail.next=newNode;
            tail = tail.next;
        }
        count++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public int dequeue(){
        if(head==tail && head == null) {
            System.out.println("queue is empty!");
            return 0;
        }
        int result = head.data;
        head=head.next;
        count--;
        return result;
    }

    public void display(){
        Node p = head;
        while (p!=null){
            System.out.print(p.data+" ");
            p=p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListQueue l = new ListQueue();
        l.enqueue(1);
        l.enqueue(2);
        l.enqueue(3);
        l.enqueue(4);
        l.display();
        l.dequeue();
        l.dequeue();
        l.display();
    }
}

