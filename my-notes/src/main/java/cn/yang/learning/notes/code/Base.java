package cn.yang.learning.notes.code;

public class Base {

    protected void logLN(Object o) {
        System.out.println(o.toString());
    }

    protected void logArray(Object[] array) {
        for (Object o : array) {
            System.out.print(o.toString() + "\t");
        }
        System.out.println();
    }

    protected void logArray(char[] array) {
        for (Object o : array) {
            System.out.print(o.toString() + "\t");
        }
        System.out.println();
    }

    protected void log(Object o) {
        System.out.print(o.toString());
        System.out.print("\t");
    }

    protected void println() {
        System.out.println();
    }

    protected void printNode(NodeMy node) {
        NodeMy cur = node;
        while (cur != null) {
            String s = cur.getV() + "";
            if (cur.getSibling() != null) {
                s = s + "-" + cur.getSibling().getV();
            }
            log(s);
            cur = cur.getNext();
        }
        println();
    }

    protected NodeMy initNode() {
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

        return node1;
    }

    protected NodeMy initNode2() {
        NodeMy node1 = new NodeMy(2);
        NodeMy node2 = new NodeMy(4);
        NodeMy node3 = new NodeMy(6);
        NodeMy node4 = new NodeMy(8);
        NodeMy node5 = new NodeMy(10);

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

}
