import java.util.ArrayList;

public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E> {
    private BinaryTreeNode<E> parent = null;
    private BinaryTreeNode<E> right = null;
    private BinaryTreeNode<E> left = null;
    private E data = null;
    public LinkedBinaryTreeNode() {
    }
    public LinkedBinaryTreeNode( E data ) {
        this.data = data;
    }
    @Override
    public E getData() {
        return data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public BinaryTreeNode<E> getRoot() {
        BinaryTreeNode<E> node = this;
        while ( node.getParent() != null) {
            node = node.getParent();
        }
        return node;
    }

    @Override
    public BinaryTreeNode<E> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTreeNode<E> parent) {
        if (this.parent == null) {this.parent = parent;}
            BinaryTreeNode<E> left = this.parent.getLeft();
            BinaryTreeNode<E> right = this.parent.getRight();
            parent.setLeft(left);
            parent.setRight(right);
            this.parent = parent;

    }

    @Override
    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    @Override
    public void setLeft(BinaryTreeNode<E> child) {
        left = child;
    }

    @Override
    public BinaryTreeNode<E> getRight() {
        return right;
    }

    @Override
    public void setRight(BinaryTreeNode<E> child) {
        right = child;
    }

    @Override
    public boolean isParent() {
        return left != null || right != null;
    }

    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public boolean hasLeftChild() {
        return left != null;
    }

    @Override
    public boolean hasRightChild() {
        return right != null;
    }

    @Override
    public int getDepth() {
        BinaryTreeNode<E> currentNode = this;
        int depthCount = 0;
        while ( currentNode.getParent() != null) {
            currentNode = currentNode.getParent();
            depthCount++;
        }
        return depthCount;
    }

    @Override
    public int getHeight() {
        return getHeightUtil(this);
    }
    public int getHeightUtil(BinaryTreeNode<E> currentNode) {
        if (currentNode == null) {return -1;}
        int leftHeight = getHeightUtil(currentNode.getLeft());
        int rightHeight = getHeightUtil(currentNode.getRight());
        return Math.max(leftHeight, rightHeight)+1;
    }

    @Override
    public int size() {
        return sizeUtil(this);
    }
    public int sizeUtil(BinaryTreeNode<E> root) {
        if (!root.isLeaf()) {
            return 1+sizeUtil(root.getLeft())+sizeUtil(root.getRight());
        }
        else {
            return 1;
        }
    }
    @Override
    public void removeFromParent() {
        BinaryTreeNode<E> currentNode = this;
        BinaryTreeNode<E> parentNode = currentNode.getParent();
        parentNode.setLeft(null);
        parentNode.setRight(null);
    }

    @Override
    public ArrayList<BinaryTreeNode<E>> pathTo(BinaryTreeNode<E> descendant) {
        ArrayList<BinaryTreeNode<E>> list = new ArrayList<>();
        LinkedBinaryTreeNode<E> current = this;
        while (descendant != current.getParent()) {
            list.add(descendant);
            descendant = descendant.getParent();
        }
        return list;
    }

    @Override
    public ArrayList<BinaryTreeNode<E>> pathFrom(BinaryTreeNode<E> ancestor) {
        ArrayList<BinaryTreeNode<E>> list = new ArrayList<>();
        BinaryTreeNode<E> node = this;
        while (node != ancestor.getParent()) {
            list.add(node);
            node = node.getParent();
        }
        return list;
    }

    @Override
    public void traversePreorder(Visitor visitor) {
        BinaryTreeNode<E> root = getRoot();
        preorderUtil(visitor,root);
    }
    public void preorderUtil(Visitor visitor, BinaryTreeNode<E> root) {
        if (root != null) {
            visitor.visit(root);
            preorderUtil(visitor,root.getLeft());
            preorderUtil(visitor, root.getRight());
        }

    }
    @Override
    public void traversePostorder(Visitor visitor) {
        BinaryTreeNode<E> root = getRoot();
        postorderUtil(visitor, root);
    }
    public void postorderUtil(Visitor visitor, BinaryTreeNode<E> root) {
        if (root != null) {
            postorderUtil(visitor, root.getLeft());
            postorderUtil(visitor,root.getRight());
            visitor.visit(root);
        }

    }
    @Override
    public void traverseInorder(Visitor visitor) {
        BinaryTreeNode<E> root = getRoot();
        inorderUtil (visitor,root);
    }
    public void inorderUtil(Visitor visitor, BinaryTreeNode<E> root) {
        if (root != null) {
            inorderUtil(visitor, root.getLeft());
            visitor.visit(root);
            inorderUtil(visitor, root.getRight());
        }
    }
}
