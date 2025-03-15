package cn.yang.learning.notes.note2025算法.链表;

public class 两两交换链表中的节点 {

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return head;
        }
        if (head.next == null) {
            return head;
        }

        ListNode head1 = head;
        ListNode head1Curr = head1;

        ListNode head2 = head.next;
        ListNode head2Curr = head2;

        while(true) {
            if(head2Curr.next == null) {
                head1Curr.next = null;
                break;
            } else {
                head1Curr.next = head2Curr.next;
                head1Curr = head1Curr.next;
            }

            if(head1Curr.next == null) {
                head2Curr.next = null;
                break;
            } else {
                head2Curr.next = head1Curr.next;
                head2Curr = head2Curr.next;
            }
        }

        ListNode headNew = new ListNode(-1);
        ListNode headNewCurr = headNew;

        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while (true) {
            if(temp1 == null && temp2 ==null) {
                break;
            } else if(temp1 != null && temp2!= null) {
                headNewCurr.next = new ListNode(temp2.val);
                headNewCurr.next.next = new ListNode(temp1.val);
                headNewCurr = headNewCurr.next.next;
            } else if(temp2== null && headNewCurr !=null) {
                headNewCurr.next = new ListNode(temp1.val);
                headNewCurr = headNewCurr.next;
            } else if(temp1 == null && headNewCurr !=null) {
                headNewCurr.next = new ListNode(temp2.val);
                headNewCurr = headNewCurr.next;
            }

            temp1 = temp1 != null ? temp1.next : null;
            temp2 = temp2 != null ? temp2.next : null;

        }


        return headNew.next;
    }

}
