package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

// 题目要求：
// 比如[1,2,2,3,3,3],删除之后为[1];
public class 删除排序链表中重复的节点 extends Base {

    protected NodeMy initNode() {
        NodeMy node1 = new NodeMy(1);
        NodeMy node2 = new NodeMy(2);
        NodeMy node3 = new NodeMy(2);
        NodeMy node4 = new NodeMy(3);
        NodeMy node5 = new NodeMy(3);

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);

        NodeMy cur = node1;
        while (cur != null) {
            log(cur.getV());
            cur = cur.getNext();
        }
        println();

        return node1;
    }

    @Test
    public void test1() {
        NodeMy node1 = initNode();

        // ----------------------------------------

        // 解题思路：
        //由于是已经排序好的链表，需要确定重复区域的长度，删除后还需要将被删去的前与后连接，所以需要三个节点pre,cur,post，cur-post为重复区域，删除后将pre与post.next连接即可。
        //此外，要注意被删结点区域处在链表头部的情况，因为需要修改head。

        NodeMy head = node1;

        NodeMy pre = null;
        NodeMy cur = head;
        NodeMy next;
        int count = 0;

        while (cur != null) {
            next = cur.getNext();
            if (next == null) {
                if (count >= 0) {
                    pre.setNext(next);
                    break;
                }
                break;
            }

            if (cur.getV() == next.getV()) {
                cur = next;
                ++count;
                continue;
            } else if (count > 0) {
                cur = next;

                while (count >= 0) {
                    pre.setNext(pre.getNext().getNext());
                    --count;
                }

                pre.setNext(cur);
                continue;
            }

            pre = cur;
            cur = next;
        }


        cur = head;
        while (cur != null) {
            log(cur.getV());
            cur = cur.getNext();
        }
        println();
    }

}
