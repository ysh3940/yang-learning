package cn.yang.learning.notes.note2025.链表;

import lombok.Data;

@Data
public class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

}
