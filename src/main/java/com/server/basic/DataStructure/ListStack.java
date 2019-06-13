package com.server.basic.DataStructure;

public class ListStack{
    public Node top = null;
    public int count = 0;

    public Boolean push(int data){
        Node newNode = new Node(data);
        if(top!=null){
            newNode.next=top;
        }
        top=newNode;
        count++;
        return true;
    }

    public int pop(){
        if(count<=0) {
            System.out.println("stack is emtpy!");
            return -1;
        }
        int result = top.data;
        top=top.next;
        return result;
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public int getTop(){
        if(null == top) {
            System.out.println("stack is empty!");
            return 0;
        }
        return top.data;
    }


    public void display(){
        Node p = top;
        while(p!=null){
            System.out.print(p.data+" ");
            p=p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListStack listStack = new ListStack();
        listStack.push(1);
        listStack.push(2);
        listStack.push(3);
        listStack.push(4);
        listStack.display();

        listStack.pop();
        listStack.pop();
        listStack.pop();
        listStack.display();
    }

}
