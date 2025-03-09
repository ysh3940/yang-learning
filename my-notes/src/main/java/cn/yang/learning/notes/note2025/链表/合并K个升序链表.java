package cn.yang.learning.notes.note2025.链表;

public class 合并K个升序链表 {

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge2(lists, 0, lists.length - 1);
    }

    private ListNode merge2(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = merge2(lists, left, mid);
        ListNode l2 = merge2(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length==1) {
            return lists[0];
        }

        ListNode mergeTemp = mergeTwoLists(lists[0], null);

        for (int i=1; i<lists.length; ++i) {
            ListNode one = lists[i];
            mergeTemp = mergeTwoLists(one, mergeTemp);
        }

        return mergeTemp;
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
