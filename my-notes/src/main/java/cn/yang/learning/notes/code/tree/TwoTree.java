package cn.yang.learning.notes.code.tree;

public class TwoTree {
    private Node root;

    // 删除节点未做平衡
    public Node delete(Integer value) {
        if (value == null) {
            return null;
        }
        Node node = get(value);
        if (node == null) {
            return null;
        }

        Node oldNode = new Node(node.getValue());
        if (node.getLeft() == null && node.getRight() == null) {
            // 没有左右子节点，可以直接删除
            if (node == node.getParent().getLeft())
                node.getParent().setLeft(null);
            if (node == node.getParent().getRight())
                node.getParent().setRight(null);
            node.setParent(null);
        } else if (node.getLeft() == null || node.getRight() == null) {
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
        } else {
            // 同时存在左右子节点，不能简单的删除，但是可以通过和后继节点交换后转换为前两种情况
            Node successor = successor(node);
            delete(successor.getValue());
            node.setValue(successor.getValue());
        }

        return oldNode;
    }

    // 找到第一个比node节点大的节点
    public Node successor(Node node) {
        if (node == null) {
            return null;
        }

        Node parent;
        Node current = node.getRight();
        while (current != null) {
            parent = current;
            current = current.getLeft();
            if (current == null) {
                return parent;
            }
        }

        return null;
    }

    public void middleList(Node node) {
        if (node == null) {
            return;
        }
        middleList(node.getLeft());
        System.out.print(node.toString() + " ");
        middleList(node.getRight());
    }

    public Node get(Integer value) {
        if (value == null) {
            return null;
        }
        Node current = root;
        while (current != null) {
            if (value == current.getValue()) {
                return current;
            }
            int comp = value > current.getValue() ? 1 : -1;
            if (comp >= 0) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }

        return null;
    }

    public Node add(Integer value) {
        if (value == null) {
            return null;
        }
        if (root == null) {
            root = new Node(value);
            root.setColor(Color.BLACK);
            return root;
        }

        Node parent = null;
        Node current = root;
        Node node = new Node(value);
        node.setColor(Color.RED);

        while (current != null) {
            parent = current;
            int comp = value >= current.getValue() ? 1 : -1;
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
        balance(node);
        return node;
    }

    private void balance(Node node) {
        Node parent = node;
        while (parent != null) {
            if (isBlack(parent.getLeft()) && isRed(parent.getRight())) {
                parent = rotateLeft(parent);
            }

            if (isRed(parent.getLeft()) && isRed(parent.getLeft().getLeft())) {
                parent = rotateRight(parent);
            }

            if (isRed(parent.getLeft()) && isRed(parent.getRight())) {
                parent = flipColor(parent);
            }

            parent = parent.getParent();
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
            current.setLeft(node);
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
            current.setRight(node);
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

    private Node flipColor(Node node) {
        node.getLeft().setColor(Color.BLACK);
        node.getRight().setColor(Color.BLACK);
        node.setColor(Color.RED);
        return node;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isRed(Node node) {
        if (node == null) return false;
        return node.getColor() == Color.RED;
    }

    public boolean isBlack(Node node) {
        return !isRed(node);
    }

    public class Node {
        private Integer value;
        private Node left;
        private Node right;
        private Node parent;
        private Color color;

        public Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
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
            return String.valueOf(value + "-" + (color != null ? color.name() : ""));
        }
    }

    public enum Color {
        RED, BLACK
    }

}
