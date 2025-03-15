package cn.yang.learning.notes.note2025算法.链表;

import lombok.Data;

@Data
public class Node {
    int key;
    int val;
    Node next;
    Node random;
    Node prev;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
        this.prev = null;
    }

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.random = null;
        this.prev = null;
    }


}
