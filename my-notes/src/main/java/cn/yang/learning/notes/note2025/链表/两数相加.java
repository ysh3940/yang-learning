package cn.yang.learning.notes.note2025.链表;

public class 两数相加 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode currA = l1;
        ListNode currB = l2;

        ListNode newHead = new ListNode(-1);
        ListNode curr = newHead;

        int addOne = 0;

        while (currA != null || currB != null) {
            if (currA == null && currB == null) {
                break;
            }

            if (currA != null && currB != null) {
                curr.next = new ListNode((currA.val+currB.val+addOne)%10);
                addOne = (currA.val+currB.val+addOne)/10;
                currA = currA.next;
                currB = currB.next;
            } else if (currA != null && currB == null) {
                curr.next = new ListNode((currA.val+addOne)%10);
                addOne = (currA.val+addOne)/10;
                currA = currA.next;
            } else if (currA == null && currB != null) {
                curr.next = new ListNode((currB.val+addOne)%10);
                addOne = (currB.val+addOne)/10;
                currB = currB.next;
            }

            curr = curr.next;
        }

        if (addOne != 0) {
            curr.next = new ListNode(addOne);
        }

        return newHead.next;
    }


}
