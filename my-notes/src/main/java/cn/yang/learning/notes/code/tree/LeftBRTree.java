package cn.yang.learning.notes.code.tree;

/**
 * 红黑数
 * 性质1. 节点是红色或黑色。
 * 性质2. 根节点是黑色。
 * 性质3  每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 * 性质4. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
 * 以上是完全红黑树的定义
 * <p>
 * 左偏红黑树的定义：（左偏红黑树代码已经经过验证ok）
 * 1、红链接均为左链接（左偏红黑树）；
 * 2、没有任何一个结点同时和两条红链接相连；
 * 3、该树是完美黑色平衡的，即任意空链接到根节点的路径上的黑链接数量相同。
 */
public class LeftBRTree<T> {
    // 根节点
    private Node root;

    // 删除节点

    // 中序遍历
    public void middleList(Node node) {
        if (node == null) {
            return;
        }
        middleList(node.getLeft());
        System.out.print(node.toString() + " ");
        middleList(node.getRight());
    }

    // 添加节点
    public Node add(T value) {
        if (value == null) {
            return null;
        }
        Node node = new Node(value);

        // 根节点为空，红黑树根节点为黑色
        if (root == null) {
            root = node;
            root.setColor(Color.BLACK);
            return root;
        }

        Node parent = null;
        Node current = root;
        // 根据特性3，新添加节点=红色
        node.setColor(Color.RED);

        while (current != null) {
            parent = current;
            Comparable comparable = (Comparable) value;
            int comp = comparable.compareTo(current.getValue());
            if (comp >= 0) {
                current = current.getRight();
                if (current == null) {
                    parent.setRight(node);
                }
            } else {
                current = current.getLeft();
                if (current == null) {
                    parent.setLeft(node);
                }
            }
        }
        node.setParent(parent);
        // 平衡红黑树
        balance(node);
        return node;
    }

    private void balance(Node node) {
        Node current = node;
        while (current != null) {
            // 右节点=红色
            if (isRed(current.getRight())) {
                current = rotateLeft(current);
            }

            // 左子节点、左子子节点都为红色
            if (isRed(current.getLeft()) && isRed(current.getLeft().getLeft())) {
                current = rotateRight(current);
            }

            // 左右子节点都为红色
            if (isRed(current.getLeft()) && isRed(current.getRight())) {
                current = flipColor(current);
            }

            current = current.getParent();
        }
        root.setColor(Color.BLACK);
    }

    //左旋转
    private Node rotateLeft(Node node) {
        Node current = node.getRight();

        node.setRight(current.getLeft());
        if (current.getLeft() != null) {
            current.getLeft().setParent(node);
        }

        if (node == root) {
            root = current;
            current.setParent(null);
        } else {
            current.setParent(node.getParent());
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(current);
            } else {
                node.getParent().setRight(current);
            }
        }

        current.setLeft(node);
        node.setParent(current);

        current.setColor(node.getColor());
        node.setColor(Color.RED);

        return current;
    }

    //右旋转
    private Node rotateRight(Node node) {
        Node current = node.getLeft();

        node.setLeft(current.getRight());
        if (current.getRight() != null) {
            current.getRight().setParent(node);
        }

        if (node == root) {
            root = current;
            current.setParent(null);
        } else {
            current.setParent(node.getParent());
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(current);
            } else {
                node.getParent().setRight(current);
            }
        }

        current.setRight(node);
        node.setParent(current);

        current.setColor(node.getColor());
        node.setColor(Color.RED);

        return current;
    }

    /**
     * node节点的2个字节点都为红色，则子节点都变成黑色，node由黑变红
     *
     * @param node
     * @return
     */
    private Node flipColor(Node node) {
        node.getLeft().setColor(Color.BLACK);
        node.getRight().setColor(Color.BLACK);
        node.setColor(Color.RED);
        return node;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isRed(Node node) {
        if (node == null) return false;
        return node.getColor() == Color.RED;
    }

    public class Node {
        private T value;
        private Node left;
        private Node right;
        private Node parent;
        private Color color;

        public Node(T value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return (value + "-" + (color != null ? color.getDesc() : ""));
        }
    }

    public enum Color {
        RED("红"), BLACK("黑");

        private String desc;

        Color(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

}
