import java.util.ArrayList;

public class LinkedBinaryTreeNode implements BinaryTreeNode{
    BinaryTreeNode rootNode = null;
    BinaryTreeNode parentNode = null;
    BinaryTreeNode leftNode = null;
    BinaryTreeNode rightNode = null;
    @Override
    public Object getData() {
        return null;
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public BinaryTreeNode getRoot() {
        return rootNode;
    }

    @Override
    public BinaryTreeNode getParent() {
        return parentNode;
    }

    @Override
    public void setParent(BinaryTreeNode parent) { parentNode = parent;}

    @Override
    public BinaryTreeNode getLeft() {
        return leftNode;
    }

    @Override
    public void setLeft(BinaryTreeNode child) { leftNode = child; }

    @Override
    public BinaryTreeNode getRight() {
        return rightNode;
    }

    @Override
    public void setRight(BinaryTreeNode child) { rightNode = child; }

    @Override
    public boolean isParent() {
        if ((rootNode.hasLeftChild() || rootNode.hasRightChild())) {
            return true;
        }
        return false;
    }
    @Override
    public boolean isLeaf() {
        if (!(rootNode.hasLeftChild() || rootNode.hasRightChild())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasLeftChild() {
        if(rootNode.getLeft() != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasRightChild() {
        if(rootNode.getRight() != null){
            return true;
        }
        return false;
    }

    @Override
    public int getDepth() {
        if (rootNode == null) return -1;
        else return 1;
    }

    @Override
    public int getHeight() {
        return -1;
    }

    @Override
    public int size() {
        if(rootNode==null){
            return 0;
        }
        return 1 + size(rootNode.getLeft()) + size(rootNode.getRight());
    }
    public int size(BinaryTreeNode node) {
        if(node==null){
            return 0;
        }
        return 1 + size(node.getLeft()) + size(node.getRight());
    }
    @Override
    public void removeFromParent() {

    }

    @Override
    public ArrayList<BinaryTreeNode> pathTo(BinaryTreeNode descendant) {
        ArrayList<BinaryTreeNode> descendantNodes = new ArrayList<>();

        return descendantNodes;
    }

    @Override
    public ArrayList<BinaryTreeNode> pathFrom(BinaryTreeNode ancestor) {
        return null;
    }

    @Override
    public void traversePreorder(Visitor visitor) {

    }

    @Override
    public void traversePostorder(Visitor visitor) {

    }

    @Override
    public void traverseInorder(Visitor visitor) {

    }
}
