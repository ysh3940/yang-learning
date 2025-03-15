package cn.yang.learning.notes.note2025算法.链表;

import java.util.ArrayList;
import java.util.List;

public class 排序链表 {

    public ListNode sortList2(ListNode head) {
        if (head==null){return head;}

        List<Integer> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }

        list.sort(Integer::compareTo);

        ListNode newHead = new ListNode(-1);
        ListNode currNew = newHead;
        for (Integer val : list) {
            currNew.next = new ListNode(val);
            currNew = currNew.next;
        }

        return newHead.next;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddle(head);
        ListNode rightHead = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        return mergeTwoLists(left, right);
    }


    private ListNode findMiddle(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println("slow="+slow.val);

        return slow;
    }

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
