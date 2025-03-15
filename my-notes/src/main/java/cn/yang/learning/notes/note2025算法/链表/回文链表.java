package cn.yang.learning.notes.note2025算法.链表;

public class 回文链表 {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode headNew = new ListNode(head.val);
        ListNode currNew = headNew;

        ListNode next = head.next;
        while (next != null) {
            ListNode temp = new ListNode(next.val);
            next = next.next;

            currNew.next = temp;
            currNew = temp;
        }

        ListNode head2 = reverseList(headNew);

        ListNode currA = head;
        ListNode currB = head2;
        while (currA != null) {
            if (currA.val != currB.val) {
                return false;
            }

            currA = currA.next;
            currB = currB.next;
        }

        return true;
    }

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
