package cn.yang.learning.notes.note2025;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    Map<Integer, DLinkedNode> map;
    int size;
    int capacity;
    DLinkedNode head;
    DLinkedNode tail;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        int val = -1;
        if (map.containsKey(key)) {
            DLinkedNode node = map.get(key);
            val = node.value;
            moveHead(node);
        }

        return val;
    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node != null) {
            node.value = value;
            moveHead(node);
        } else {
            node = new DLinkedNode(key, value);
            map.put(key, node);
            ++size;
            moveHeadNotRemove(node);

            if (size > capacity) {
                DLinkedNode removeNode = removeTail();
                map.remove(removeNode.key);
                --size;
            }
        }
    }

    private DLinkedNode removeTail() {
        DLinkedNode tailPrev  = tail.prev;

        DLinkedNode nodeNext = tailPrev.next;
        DLinkedNode nodePrev = tailPrev.prev;

        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;


        return tailPrev;
    }


    private void removeNode(DLinkedNode node) {
        DLinkedNode nodeNext = node.next;
        DLinkedNode nodePrev = node.prev;

        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;
    }

    private void moveHead(DLinkedNode node) {
        removeNode(node);

        DLinkedNode headNext = head.next;

        head.next = node;
        node.prev = head;

        headNext.prev = node;
        node.next = headNext;
    }

    private void moveHeadNotRemove(DLinkedNode node) {
        DLinkedNode headNext = head.next;

        head.next = node;
        node.prev = head;

        headNext.prev = node;
        node.next = headNext;
    }



    // 定义双向链表节点类
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


}
