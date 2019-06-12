package com.basicknowledge.DataStructure;

/**
 * 用链表实现LRU 缓存淘汰算法
 * LRU 最近最少使用策略（其余缓存淘汰策略包括：FIFO 先进先出  LFU 最少使用策略）
 * 思路：
 * 1. 维护一个有序单链表（根据缓存时间排序），链表尾部为最早之前访问，当有新的数据访问时，从链表头部开始访问
 * 2. 如果此数据已经被缓存在链表中，则遍历得到就数据然后删除，将新数据插入到链表头部。
 * 3. 如果此数据没有被缓存到链表中:
 *      1) 缓存未满 ，插入到链表头部
 *      2) 缓存已满，删掉尾节点，将新节点插入到头部。
 *
 */
public class LRUList {

    //带头结点单链表
    public static Node head = new Node();

    /**
     * 缓存算法
     * @param data
     */
    public static void cache(int data){
        //遍历链表，看是否已经缓存
        Node currentNode = null;
        Node preNode = head;
        Node lastNode =null;
        int length = 0;
        while(null!=preNode.next){
            currentNode=preNode.next;
            if(currentNode.data==data){
                //已经缓存过，删掉该节点
                preNode.next=currentNode.next;
            }else{
                preNode=preNode.next;
                length++;
            }
        }
        if(length>=10){
            System.out.println("cache is full，remove the data at the end! ");
            currentNode=head;
            while(currentNode.next!=preNode){
                currentNode=currentNode.next;
            }
            //删除第一次遍历的最后一个节点preNode
            currentNode.next=null;
        }
        addBefore(data);
        printList(head);
    }

    /**
     * 插入链表头部
     * @param data
     */
    public static void addBefore(int data){
        Node currentNode = new Node(data);
        if(null!=head.next){
            currentNode.next=head.next;
        }
        head.next=currentNode;
    }

    /**
     * 打印缓存链表
     * @param head
     */
    public static void printList(Node head){
        while (null!=head.next){
            head=head.next;
            System.out.print(head.data+"    ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        //缓存已满
        cache(1);
        cache(2);
        cache(6);
        cache(7);
        cache(8);
        cache(9);
        cache(4);
        cache(5);
        cache(11);
        cache(3);
        cache(12);

        //插入已经缓存过的数据
//        cache(1);
//        cache(2);
//        cache(6);
//        cache(7);
//        cache(8);
//        cache(9);
//        cache(4);
//        cache(1);

    }

}

