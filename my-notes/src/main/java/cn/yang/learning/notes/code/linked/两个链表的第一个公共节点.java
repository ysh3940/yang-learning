package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

public class 两个链表的第一个公共节点 extends Base {

    @Test
    public void test1() {
        // 题目要求：
        // 输入两个单链表，找出它们的第一个公共节点。以下图为例，对一个公共节点为6所在的节点。
        // 1 -> 2 -> 3 -> 6 -> 7
        //      4 -> 5 ↗

        // 解法1：反转2个单链表
        // 解法2：长链表先行|n-m|步，转化为等长链表

        NodeMy node1 = new NodeMy(1);
        NodeMy node2 = new NodeMy(2);
        NodeMy node3 = new NodeMy(3);
        NodeMy node81 = new NodeMy(8);
        NodeMy node91 = new NodeMy(9);

        NodeMy node4 = new NodeMy(4);
        NodeMy node5 = new NodeMy(5);
        NodeMy node6 = new NodeMy(6);
        NodeMy node7 = new NodeMy(7);
        NodeMy node8 = new NodeMy(8);
        NodeMy node9 = new NodeMy(9);


        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node81);
        node81.setNext(node91);

        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node7);
        node7.setNext(node8);
        node8.setNext(node9);

        printNode(node1);
        printNode(node4);

        NodeMy node1R = reverse(node1);
        NodeMy node4R = reverse(node4);
        printNode(node1R);
        printNode(node4R);

        NodeMy pre = null;
        while (node1R != null && node4R != null) {
            if (node1R.getV() != node4R.getV()) {
                break;
            }
            pre = node1R;

            node1R = node1R.getNext();
            node4R = node4R.getNext();
        }
        logLN(pre.getV());
    }

    public NodeMy reverse(NodeMy node) {
        NodeMy node1 = node;

        // ------------------反转单链表----------------------------
        // 包括：从尾到头打印链表、反转链表
        NodeMy cur = node1;
        NodeMy next = cur.getNext();
        cur.setNext(null);
        NodeMy temp;
        while (next != null) {
            temp = next.getNext();
            next.setNext(cur);

            cur = next;
            next = temp;
        }

        return cur;
    }

}
