package cn.yang.learning.notes.code.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {

    @org.junit.Test
    public void test() {
        Random random = new Random();

        BinaryTree tree = new BinaryTree();
        for (int i = 1; i <= 5; ++i) {
            tree.add(new TreeNode(random.nextInt(10)));
        }
        tree.add(new TreeNode(3));

        List<TreeNode> list = new ArrayList<TreeNode>();
        tree.sequentialTraversal(tree.getRoot(), list);
        System.out.println(list.toString());

        // 直接用list的数据会有问题，原list里面的节点有引用关系
        list.clear();
        list.add(new TreeNode(8));
        list.add(new TreeNode(3));
        list.add(new TreeNode(9));
        list.add(new TreeNode(1));
        list.add(new TreeNode(9));
        list.add(new TreeNode(0));

        BinaryTree newTree = new BinaryTree();
        newTree.arrayToTree(newTree, list, 0, list.size() - 1);
        newTree.sequentialTraversal(newTree.getRoot());
        System.out.println();

        list.clear();
        newTree.sequentialTraversal(newTree.getRoot(), list);
        System.out.println(list.toString());

        System.out.println(newTree.get(4));
    }

}
