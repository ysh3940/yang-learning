package cn.yang.learning.notes.note2025.链表;

import java.util.HashSet;
import java.util.Set;

public class 相交链表 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode currA = headA;
        while (currA != null) {
            set.add(currA);
            currA = currA.next;
        }

        ListNode currB = headB;
        while (currB != null) {
            if (set.contains(currB)) {
                return currB;
            }
            currB = currB.next;
        }


        return null;
    }


    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode currA = headA;
        ListNode currB = headB;

        while (currA != currB) {
            currA = currA != null ? currA.next : headB;
            currB = currB != null ? currB.next : headA;
        }

        return currA;
    }


    }
