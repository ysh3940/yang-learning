package cn.yang.learning.notes.code;

import lombok.Data;

@Data
public class NodeMy {
    int v;
    NodeMy next;
    NodeMy sibling;// 指向链表中的任意一个节点

    public NodeMy(int v) {
        this.v = v;
    }

    public NodeMy(int v, NodeMy next) {
        this.v = v;
        this.next = next;
    }

}
