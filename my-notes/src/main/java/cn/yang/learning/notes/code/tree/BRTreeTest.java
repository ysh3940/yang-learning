package cn.yang.learning.notes.code.tree;

import org.junit.Test;

public class BRTreeTest {

    @Test
    public void test() {
        // Integer[] array = {1, 2, 3, 4, 5, 6, 7};
        String[] array = {"A", "C", "E", "H", "L", "M", "P", "R", "S", "S"};

        BRTree tree = new BRTree();

        for (int i = 0; i < array.length; ++i) {
            tree.add(array[i]);

            tree.middleList(tree.getRoot());
            System.out.println();
        }
        log("root = " + tree.getRoot().toString());
        log(tree.get("S").getValue());

    }

    private void log(Object s) {
        System.out.println(Thread.currentThread().getName() + " - " + s);
    }

}
