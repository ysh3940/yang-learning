package cn.yang.learning.notes.code.tree;

import org.junit.Test;

public class SinglyLinkedListReversal {

    @Test
    public void test1() {
        Node root = new Node("0");
        Node current = root;
        for (int i = 1; i < 5; ++i) {
            Node node = new Node("" + i);
            current.setNext(node);
            current = node;
        }

        print(root);

        // 反转单链表
        Node first = root;
        Node curr2 = root.getNext();
        while (curr2 != null) {
            first.setNext(curr2.getNext());
            curr2.setNext(root);
            root = curr2;

            curr2 = first.getNext();
        }

        System.out.println();
        print(root);
    }

    private void print(Node node) {
        Node curr = node;
        while (curr != null) {
            System.out.print(curr.getValue() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }

}

class Node {
    private String value;
    private Node next;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

}
