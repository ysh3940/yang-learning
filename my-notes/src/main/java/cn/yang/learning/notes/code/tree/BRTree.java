package cn.yang.learning.notes.code.tree;

/**
 * 红黑数（已经经过测试）
 * 性质1. 节点是红色或黑色。
 * 性质2. 根节点是黑色。
 * 性质3  每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 * 性质4. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
 */
public class BRTree<T> {
    // 根节点
    private Node root;

    // 删除节点（普通二叉树的删除，删除后未做balance）
    public Node delete(Object value) {
        if (value == null) {
            return null;
        }
        // 查询value是否在树中
        Node node = get(value);
        if (node == null) {
            return null;
        }

        // 考虑一下root的删除
        Node oldNode = new Node(node.getValue());
        if (node.getLeft() == null && node.getRight() == null) {
            // 没有左右子节点，可以直接删除
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(null);
            } else if (node == node.getParent().getRight()) {
                node.getParent().setRight(null);
            }
            node.setParent(null);
        } else if (node.getLeft() != null && node.getRight() != null) {
            // 同时存在左右子节点，不能简单的删除，但是可以通过和后继节点交换后转换为前两种情况
            // 找到第一个比node节点大的节点
            Node successor = successor(node);
            delete(successor.getValue());
            node.setValue(successor.getValue());
        } else {
            // 只存在一个节点
            // 存在左节点或者右节点，删除后需要对子节点移动
            Node child = node.getLeft() == null ? node.getRight() : node.getLeft();
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(child);
                child.setParent(node.getParent());
            } else {
                node.getParent().setRight(child);
                child.setParent(node.getParent());
            }

            node.setParent(null);
            node.setLeft(null);
            node.setRight(null);
        }

        return oldNode;
    }

    // 找到第一个比node节点大的节点
    public Node successor(Node node) {
        if (node == null) {
            return null;
        }

        Node parent;
        // node节点的右节点肯定node大
        Node current = node.getRight();
        while (current != null) {
            parent = current;
            // 只要一直找左节点就可以，最后一个左节点就是第一个比node节点大的节点
            current = current.getLeft();
            if (current == null) {
                return parent;
            }
        }

        return null;
    }

    // 中序遍历
    public void middleList(Node node) {
        if (node == null) {
            return;
        }
        middleList(node.getLeft());
        System.out.print(node.toString() + " ");
        middleList(node.getRight());
    }

    // 查询节点
    public Node get(Object value) {
        if (value == null) {
            return null;
        }
        // 从根节点开始搜索
        Node current = root;
        while (current != null) {
            if (value == current.getValue()) {
                return current;
            }
            int comp = ((Comparable) value).compareTo(current.getValue());
            if (comp >= 0) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }

        return null;
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
        // 根据特性4，新添加节点=红色
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
        while (current != null && isRed(parentOf(current))) {
            if (parentOf(current) == parentOf(parentOf(current)).getLeft()) {
                if (isRed(parentOf(parentOf(current)).getRight())) {
                    flipColor(parentOf(parentOf(current)));
                    current = parentOf(parentOf(current));
                } else {
                    if (current == parentOf(current).getRight()) {
                        current = rotateLeft(parentOf(current)).getLeft();
                    }
                    rotateRight(parentOf(parentOf(current)));
                }
            } else {
                if (isRed(parentOf(parentOf(current)).getLeft())) {
                    flipColor(parentOf(parentOf(current)));
                    current = parentOf(parentOf(current));
                } else {
                    if (current == parentOf(current).getLeft()) {
                        current = rotateRight(parentOf(current)).getRight();
                    }
                    rotateLeft(parentOf(parentOf(current)));
                }
            }
        }
        root.setColor(Color.BLACK);
    }

    private Node parentOf(Node node) {
        return (node == null ? null : node.getParent());
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

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
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
