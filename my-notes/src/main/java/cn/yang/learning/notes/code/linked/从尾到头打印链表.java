package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 从尾到头打印链表 extends Base {

    @Test
    public void test1() {
        NodeMy node1 = initNode();

        // ------------------反转单链表----------------------------
        // 包括：从尾到头打印链表、反转链表

        // 解法1：反转单链表
        // 解法2：递归

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

        while (cur != null) {
            log(cur.getV());
            cur = cur.getNext();
        }
        println();

    }

    // 从尾到头打印链表
    @Test
    public void test2() {
        NodeMy node1 = initNode();
        printNode(node1);

        List<String> list = new ArrayList<>();
        do2(node1, list);
        println();
        for (String s : list) {
            logLN(s);
        }
    }

    private void do2(NodeMy node, List<String> list) {
        if (node == null) {
            return;
        }
        do2(node.getNext(), list);
        list.add(node.getV() + "");
    }


    @Test
    public void test3() {
        NodeMy node1 = initNode();

        NodeMy tail;
        NodeMy next = node1.getNext();
        while (next != null) {
            if (next.getNext() == null) {
                break;
            }
            next = next.getNext();
        }
        tail = next;

        do3(node1);
        node1.setNext(null);

        printNode(tail);
    }

    private NodeMy do3(NodeMy node) {
        if (node == null) {
            return null;
        }
        if (node.getNext() == null) {
            return node;
        }
        NodeMy temp = do3(node.getNext());
        if (temp != null) {
            temp.setNext(node);
        }

        return node;
    }


}

