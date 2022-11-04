package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

// 解题思路：
// 直接删除单链表某一节点，无法在o(1)时间得到该节点的前一个节点，因此无法完成题目要求。
// 可以将欲删节点的后一个节点的值拷贝到欲删节点之上，删除欲删节点的后一个节点，从而可以在o(1)时间内完成删除。（
// 对于尾节点，删除仍然需要o(n),其他点为o(1)，因此平均时间复杂度为o(1)，满足要求）
public class 在o1时间内删除单链表的节点 extends Base {

    @Test
    public void test1() {
        NodeMy node1 = initNode();
        NodeMy node3 = node1.getNext().getNext();

        // 已知道要删除节点的指针 = node3
        // 如果是尾结点则无法o（1)时间删除
        node3.setV(node3.getNext().getV());
        node3.setNext(node3.getNext().getNext());

        NodeMy cur = node1;
        while (cur != null) {
            log(cur.getV());
            cur = cur.getNext();
        }
        println();
    }

}
