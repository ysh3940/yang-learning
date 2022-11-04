package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

public class 复杂链表的复制 extends Base {

    // 面试题35：复杂链表的复制
    //题目要求：在复杂链表中，每个节点除了有一个next指针指向下一个节点，还有一个random指针指向链表中的任意节点或null，请完成一个能够复制复杂链表的函数。
    //
    //解题思路：
    //此题定义了一种新的数据结构，复杂链表。与传统链表的区别是多了一个random指针。本题的关键点也就在如何高效地完成random指针的复制。


    @Test
    public void test1() {
        NodeMy node1 = new NodeMy(1);
        NodeMy node2 = new NodeMy(2);
        NodeMy node3 = new NodeMy(3);
        NodeMy node4 = new NodeMy(4);
        NodeMy node5 = new NodeMy(5);

        node1.setNext(node2);
        node1.setSibling(node5);

        node2.setNext(node3);
        node2.setSibling(node4);

        node3.setNext(node4);
        node3.setSibling(node3);

        node4.setNext(node5);
        node4.setSibling(node2);

        node5.setSibling(node1);

        printNode(node1);
        // -----------------------------------------

        NodeMy cur = node1;
        while (cur != null) {
            NodeMy copyNode = new NodeMy(cur.getV());
            copyNode.setNext(cur.getNext());
            cur.setNext(copyNode);

            cur = copyNode.getNext();
        }

        cur = node1;
        while (cur != null) {
            cur.getNext().setSibling(cur.getSibling().getNext());

            cur = cur.getNext().getNext();
        }

        cur = node1;
        NodeMy copyNode = cur.getNext();
        NodeMy temp = cur.getNext();
        cur = cur.getNext().getNext();
        while (cur != null) {
            copyNode.setNext(cur.getNext());
            copyNode = copyNode.getNext();

            cur = cur.getNext().getNext();
        }

        printNode(temp);


    }

}
