package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

public class 链表中倒数第k个节点 extends Base {

    @Test
    public void test1() {
        NodeMy head = initNode();

        NodeMy temp = head;

        int k = 2;
        NodeMy nodeK = head;
        for (int i = 0; i < k; ++i) {
            head = head.getNext();
        }

        while (head != null) {
            head = head.getNext();
            if (head != null) {
                nodeK = nodeK.getNext();
            }
        }

        logLN(nodeK.getNext().getV());
        nodeK.setNext(nodeK.getNext().getNext());

        printNode(temp);

    }

}
