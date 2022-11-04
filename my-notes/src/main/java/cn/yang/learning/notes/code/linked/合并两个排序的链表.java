package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

public class 合并两个排序的链表 extends Base {

    @Test
    public void test1() {
        // 题目要求：
        //输入两个递增排序的链表，要求合并后保持递增。

        // 解题思路：
        //这个题目是二路链表归并排序的一部分，或者说是最关键的归并函数部分。熟悉归并排序的话做这个题应该很容易。思路很简单，注意链表的next指针，初始条件，结束条件即可。

        NodeMy node1 = initNode();
        NodeMy node2 = initNode2();

        NodeMy nodeNew = deal(node1, node2);
        printNode(nodeNew);
    }

    private NodeMy deal(NodeMy node1, NodeMy node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        NodeMy headMerge;
        if (node1.getV() <= node2.getV()) {
            headMerge = node1;
            headMerge.setNext(deal(node1.getNext(), node2));
        } else {
            headMerge = node2;
            headMerge.setNext(deal(node1, node2.getNext()));
        }
        return headMerge;
    }

}
