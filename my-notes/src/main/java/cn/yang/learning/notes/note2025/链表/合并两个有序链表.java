package cn.yang.learning.notes.note2025.链表;

public class 合并两个有序链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }


        ListNode newNode = new ListNode(-1);
        ListNode curr = newNode;
        ListNode currA = l1;
        ListNode currB = l2;

        while (currA != null && currB != null) {
            if (currA.val < currB.val) {
                curr.next = new ListNode(currA.val);
                currA = currA.next;
            } else {
                curr.next = new ListNode(currB.val);
                currB = currB.next;
            }


            curr = curr.next;
        }

        if (currA != null) {
            curr.next = currA;
        }
        if (currB != null) {
            curr.next = currB;
        }


        return newNode.next;
    }

}
