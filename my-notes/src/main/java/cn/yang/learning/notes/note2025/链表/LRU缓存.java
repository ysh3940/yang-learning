package cn.yang.learning.notes.note2025.链表;

import java.util.HashMap;
import java.util.Map;

public class LRU缓存 {
    Map<Integer, Node> map;
    int capacity;
    int size;
    Node head;
    Node tail;

    LRU缓存(int capacity){
        this.capacity =capacity;
        this.size=0;
        map = new HashMap<>(capacity);
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            moveHead(node);
            return node.val;
        }

        return -1;
    }

    void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.setVal(value);
            moveHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);

            putHead(node);
            ++size;

            if (size > capacity) {
                Node remNode = removeTail();
                map.remove(remNode.key);
                --size;
            }
        }
    }

    private Node removeTail() {
        Node tailPrev = tail.prev;

        Node tailPrevPrev = tailPrev.prev;

        tailPrevPrev.next = tail;
        tail.prev = tailPrevPrev;

        return tailPrev;
    }

    private void putHead(Node node) {
        Node headNext = head.next;
        head.next  = node;
        headNext.prev = node;

        node.prev = head;
        node.next = headNext;
    }

    private void moveHead(Node node) {
        Node nodePrev = node.prev;
        Node nodeNext = node.next;

        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;


        Node headNext = head.next;
        head.next  = node;
        headNext.prev = node;

        node.prev = head;
        node.next = headNext;
    }


}
