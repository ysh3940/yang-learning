package cn.yang.learning.notes.code.linked;

import cn.yang.learning.notes.code.Base;
import cn.yang.learning.notes.code.NodeMy;
import org.junit.Test;

// 思路是：首先申请两个指针，快指针一次前进两步，慢指针一次前进一步，初始化都再链表头部。
// 然后开始移动，如果他们指向了同一个节点，则证明有环，否则没环。当他们指向了同一个节点时，慢指针再次初始化，指向头结点。
// 快慢指针前进步数都改为1，当他们再次指向同一个节点，这个节点就是环的入口节点。
public class 链表中环的入口 extends Base {

    @Test
    public void test1() {
        NodeMy node1 = new NodeMy(1);
        NodeMy node2 = new NodeMy(2);
        NodeMy node3 = new NodeMy(3);
        NodeMy node4 = new NodeMy(4);
        NodeMy node5 = new NodeMy(5);

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

        node5.setNext(node3);
        // -------------------------------------------
        NodeMy n1 = node1;// 每次走一步
        NodeMy n2 = node1;// 每次走二步

        Integer temp;
        while (true) {
            n1 = n1.getNext();
            n2 = n2.getNext().getNext();
            if (n1.getV() == n2.getV()) {
                temp = n1.getV();
                break;
            }
        }
        logLN("temp = " + temp.intValue());

        NodeMy fast = node1;
        NodeMy slow = node1;
        while (true) {
            n1 = n1.getNext();
            fast = fast.getNext();
            if (n1.getV() == temp) {
                break;
            }
        }

        while (true) {
            if (fast.getV() == slow.getV()) {
                logLN(fast.getV());
                break;
            }
            fast = fast.getNext();
            slow = slow.getNext();
        }
    }


}
