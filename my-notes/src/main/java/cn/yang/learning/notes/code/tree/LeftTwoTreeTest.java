package cn.yang.learning.notes.code.tree;

import org.junit.Test;

public class LeftTwoTreeTest {

    @Test
    public void test() {
        String[] array = {"A", "C", "E", "H", "L", "M", "P", "R", "S", "S"};
        // String[] array = {"S","E","A","R","C","H","X","M","P","L"};

        LeftBRTree tree = new LeftBRTree();

        for (int i = 0; i < array.length; ++i) {
            tree.add(array[i]);

            tree.middleList(tree.getRoot());
            System.out.println();
        }

        log("root = " + tree.getRoot().toString());
    }

    private void log(String s) {
        System.out.println(Thread.currentThread().getName() + " - " + s);
    }

}
