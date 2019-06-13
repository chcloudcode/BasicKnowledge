package com.server.basic.LeetCode.simple;

/**
 * 合并两个有序链表
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeSeqList {

    /**
     * 思路： 建立新的链表head，设立遍历指针p、q、r 分别遍历 l1、l2、head
     *            先找到头结点，然后遍历l1、l2相同长度部分。
     *            多余部分直接接到r后面
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;
        ListNode p = l1;
        ListNode q = l2;
        ListNode head ;
        if(p.val<q.val){
            head = p;
            p=p.next;
        }else{
            head=q;
            q=q.next;
        }
        ListNode r = head;
        while (p!=null&q!=null){
            if(p.val<q.val){
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
     * 打印链表
     * @param head
     */
    public static void display(ListNode head){
        while (null!=head){
            System.out.print(head.val+"   ");
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeSeqList m = new MergeSeqList();
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(7);
        ListNode n3 = new ListNode(9);
        n1.next=n2;n2.next=n3;n3.next=null;
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(8);
        n4.next=n5;n5.next=n6;n6.next=null;
        display(m.mergeTwoLists(n1,n4));
    }


}
