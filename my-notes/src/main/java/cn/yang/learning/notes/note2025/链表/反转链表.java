package cn.yang.learning.notes.note2025.链表;

public class 反转链表 {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode curr = head;
        ListNode next = curr.next;
        curr.next = null;

        while (next != null) {
            ListNode temp = next.next;
            next.next = curr;

            curr = next;
            next = temp;
        }

        return curr;
    }

}
