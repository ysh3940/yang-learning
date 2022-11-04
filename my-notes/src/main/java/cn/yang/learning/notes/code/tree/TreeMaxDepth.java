package cn.yang.learning.notes.code.tree;

public class TreeMaxDepth {

    /**
     * 计算树的深度
     *
     * @param node
     * @return
     */
    public static int depth(BRTree.Node node) {
        if (node == null) {
            return 0;
        } else {
            int d1 = depth(node.getLeft());
            int d2 = depth(node.getRight());
            return 1 + Math.max(d1, d2);
        }
    }

}
