package com.server.DataStructure;

/**
 *
 * 单链表 常用操作
 *  1. 单链表的反转
 *  2. 链表中环的检测
 *  3. 合并有序链表
 *  4. 查询链表中间值
 *  5. 删除链表的倒数第n个节点
 *
 */

public class  ListUtils{

    /**
     * 单链表的反转 : 头插法
     */
    public static Node reverse(Node head){
        //新增头结点 ， 每次头插法 插入到头结点后面。
        Node headNode = new Node(0);
        Node r = head;
        while (r!=null){
            //记录链表下一个节点
            Node next = r.next;
            r.next=headNode.next;
            headNode.next=r;
            r=next;
        }
        return headNode.next;
    }

    /**
     * 链表中环的检测 快慢指针
     * @return
     */
    public static Boolean checkCircle(Node head){
        if(head==null) return false;
        Node fast = head;
        Node slow = head;
        while (slow != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 两个有序链表的合并
     * @param l1
     * @param l2
     * @return
     */
    public static Node mergeOrderList(Node l1,Node l2){
        if(l1==null) return l2;
        if(l2==null) return l1;
        Node p = l1;
        Node q = l2;
        Node head ;
        //寻找新链表的第一个节点
        if(p.data<q.data){
            head = p;
            p=p.next;
        }else{
            head=q;
            q=q.next;
        }
        Node r = head;
        while (p!=null&q!=null){
            if(p.data<q.data){
                r.next=p;
                p=p.next;
            }else{
                r.next=q;
                q=q.next;
            }
            r=r.next;
        }
        if(p!=null){
            r.next=p;
        }
        if(q!=null){
            r.next=q;
        }
        return head;
    }

    /**
     * 查询链表的中间节点：快慢指针，慢指针走一步，快指针走两步，快指针走完，慢指针到达中点。
     * @param head
     * @return
     */
    public static Node findListMiddata(Node head){
        if(head==null) return null;
        Node slow = head;
        Node fast = head;
        while(fast!=null&&fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /**
     * 删除链表倒数第n个节点
     * @return
     */
    public static Node deleteLastKthNode(Node list,int n){
        Node fast = list;
        int i = 1;
        while (fast != null && i < n) {
            fast = fast.next;
            ++i;
        }

        if (fast == null) return list;

        Node slow = list;
        Node prev = null;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            list = list.next;
        } else {
            prev.next = prev.next.next;
        }
        return list;
    }

    /**
     * 删除链表中重复的元素
     * @param head
     * @return
     */
    public static Node deleteSameNode(Node head){
        if(head == null || head.next == null) return head;
        Node p = head,q=p.next;
        while(q!=null&&p.next!=null){
            if(p.data!=q.data) {
                q=q.next;
                p=p.next;
            }else{
                p.next=q.next;
                q=q.next;
            }
        }
        return head;
    }

    /**
     * 打印链表
     * @param head
     */
    public static void display(Node head){
        while (null!=head){
            System.out.print(head.data+"   ");
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        Node l1 = new Node(1);
//        Node l2 = new Node(3);
//        Node l3 = new Node(5);
//        Node l4 = new Node(7);
//        Node l5 = new Node(9);
//        l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;l5.next=null;
//        display(ListUtils.reverse(l1));//关系反转了
//        l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;l5.next=null;
//
//        Node r1 = new Node(2);
//        Node r2 = new Node(4);
//        Node r3 = new Node(6);
//        Node r4 = new Node(8);
//        Node r5 = new Node(10);
//        r1.next=r2;r2.next=r3;r3.next=r4;r4.next=r5;r5.next=null;
//        display(r1);
//        display(ListUtils.mergeOrderList(l1,r1));
//
//        System.out.println(ListUtils.findListMiddata(l1).data);
//        System.out.println(ListUtils.findListMiddata(r1).data);

        Node n1 = new Node(1);
        Node n2 = new Node(1);
        Node n3 = new Node(2);
        Node n4 = new Node(3);
        Node n5 = new Node(3);
        Node n6 = new Node(4);
        Node n7 = new Node(4);
        Node n8 = new Node(4);
        Node n9 = new Node(5);
        Node n10 = new Node(6);
        Node n11= new Node(6);
        n1.next=n2;n2.next=n3;n3.next=n4;n4.next=n5;n5.next=n6;n6.next=n7;n7.next=n8;n8.next=n9;n9.next=n10;n10.next=n11;

        display(ListUtils.deleteSameNode(n1));


        
    }

}