package cn.yang.learning.notes.note2025算法.链表;

public class 删除链表的倒数第N个结点 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode fast = head;
        for (int i=1; i<n; ++i) {
            if (fast != null) {
                fast = fast.next;
            }
        }
        System.out.println("fast="+fast.val);

        if (fast == null) {
            return head;
        }
        if (fast.next == null) {
            return head.next;
        }

        System.out.println("--------------------");

        ListNode slow = head;
        while (fast != null) {
            System.out.println("fast="+fast.val);
            System.out.println("slow="+slow.val);

            fast = fast.next;
            if (fast == null || fast.next == null) {
                break;
            }
            if (slow.next != null) {
                slow = slow.next;
            }

        }
        ListNode temp = slow.next;
        slow.next = temp.next;


        return head;
    }

}
