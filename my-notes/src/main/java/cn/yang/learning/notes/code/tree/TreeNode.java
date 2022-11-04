package cn.yang.learning.notes.code.tree;

/**
 * @author shenhui.yang
 * @ClassName: TreeNode
 * @Description: 这里的树通常是指二叉树
 * @date 2017/8/29 0029 16:26
 */
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void println() {
        System.out.println(value);
    }

    @Override
    public String toString() {
        return "value=" + value;
    }

}
