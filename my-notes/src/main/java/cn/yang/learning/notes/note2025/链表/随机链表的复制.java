package cn.yang.learning.notes.note2025.链表;

import java.util.HashMap;
import java.util.Map;

public class 随机链表的复制 {

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> map = new HashMap<>();
        Node curr = head;
        while (curr != null) {
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }

        Node newHead = new Node(-1);
        Node currNew = newHead;

        curr = head;
        while (curr != null) {
            Node temp = map.get(curr);
            temp.next = map.get(curr.next);
            temp.random = map.get(curr.random);

            currNew.next = temp;

            curr = curr.next;
            currNew = currNew.next;
        }


        return newHead.next;
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            Node temp = curr.next;
            curr.next = copy;
            copy.next = temp;

            curr = copy.next;
        }

        curr = head;
        while (curr != null) {
            Node copy = curr.next;
            if (curr.random != null) {
                copy.random = curr.random.next;
            }

            curr = copy.next;
        }

        // 拆分链表
        Node newHead = head.next;
        curr = head;
        Node copyCurrent = newHead;
        while (curr != null) {
            curr.next = copyCurrent.next;
            curr = curr.next;
            if (curr != null) {
                copyCurrent.next = curr.next;
                copyCurrent = copyCurrent.next;
            }
        }
        return newHead;

    }



}
