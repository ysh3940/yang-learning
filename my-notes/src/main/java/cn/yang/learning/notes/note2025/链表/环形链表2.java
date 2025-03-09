package cn.yang.learning.notes.note2025.链表;

import java.util.HashSet;
import java.util.Set;

public class 环形链表2 {

    // 找出环形链表的起点
    public ListNode detectCycle1(ListNode head) {
        if (head == null) {
            return null;
        }

        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;

        while (curr != null) {
            if (set.contains(curr)) {
                return curr;
            }
            set.add(curr);
            curr = curr.next;
        }

        return null;
    }


}
