import java.util.ArrayList;

public class LinkedBinaryTreeNode implements BinaryTreeNode{
    @Override
    public Object getData() {
        return null;
    }

    @Override
    public void setData(Object data) {

    }

    @Override
    public BinaryTreeNode getRoot() {
        return null;
    }

    @Override
    public BinaryTreeNode getParent() {
        return null;
    }

    @Override
    public void setParent(BinaryTreeNode parent) {

    }

    @Override
    public BinaryTreeNode getLeft() {
        return null;
    }

    @Override
    public void setLeft(BinaryTreeNode child) {

    }

    @Override
    public BinaryTreeNode getRight() {
        return null;
    }

    @Override
    public void setRight(BinaryTreeNode child) {

    }

    @Override
    public boolean isParent() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public boolean hasLeftChild() {
        return false;
    }

    @Override
    public boolean hasRightChild() {
        return false;
    }

    @Override
    public int getDepth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void removeFromParent() {

    }

    @Override
    public ArrayList<BinaryTreeNode> pathTo(BinaryTreeNode descendant) {
        return null;
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
