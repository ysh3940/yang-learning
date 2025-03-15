package cn.yang.learning.notes.note2025算法.链表;

import java.util.ArrayList;
import java.util.List;

public class K个一组翻转链表 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        List<ListNode> nodeList = new ArrayList<>();
        ListNode headPart = head;
        ListNode curr = head;
        int count = 1;

        while (true) {
            if (curr == null) {
                break;
            }
            if (curr.next == null) {
                nodeList.add(headPart);
                break;
            }

            curr = curr.next;
            ++count;

            if (count == k) {
                count = 1;

                ListNode temp = curr.next;
                curr.next = null;
                curr = temp;


                nodeList.add(headPart);
                headPart = curr;
            }

        }


        ListNode headNew = new ListNode(-1);
        ListNode last = headNew;

        for (int i=0; i<nodeList.size(); ++i) {
            ListNode part = nodeList.get(i);

            ListNode currTemp = part;
            int size = 0;
            while (currTemp != null) {
                ++size;
                currTemp = currTemp.next;
            }

            if (size == k) {
                ListNode partReverse = reverseList(part);
                last.next = partReverse;

                last = part;
            } else {
                last.next = part;
            }

        }



        return headNew.next;
    }

    private  ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


}
