import java.util.ArrayList;

public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E>{
    BinaryTreeNode rootNode = null;
    BinaryTreeNode parentNode = null;
    BinaryTreeNode leftNode = null;
    BinaryTreeNode rightNode = null;

    E data = null;
    @Override
    public E getData() {
        return data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
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
        BinaryTreeNode curNode = rootNode;
        int depth = 0;
        while(curNode.getParent() != null){
            curNode = curNode.getParent();
            depth++;
        }
        return depth;
    }

    @Override
    public int getHeight() {
        return getHeight(0,rootNode);
    }
    public int getHeight(int i, BinaryTreeNode curNode){
        if(curNode.hasLeftChild()){
            return getHeight(i+1, curNode.getLeft());
        }
        if(curNode.hasRightChild()){
            return getHeight(i+1, curNode.getRight());
        }
        if(!curNode.hasLeftChild() && !curNode.hasRightChild()){
            return i;
        }
        return -1;
    }

    public ArrayList<BinaryTreeNode> search(BinaryTreeNode searchNode){
        ArrayList<BinaryTreeNode> path = new ArrayList<>();
        BinaryTreeNode curNode = rootNode;
        if(curNode.getLeft() == searchNode || curNode.getRight() == searchNode){
            path.add(curNode);
            return path;
        }
        if(curNode.getLeft() == null){
            search(curNode.getRight());
        }if(curNode.getRight() == null){
            search(curNode.getLeft());
        }if(curNode.getLeft() == null && curNode.getRight() == null){
            return null;
        }
        search(curNode.getLeft());
        search(curNode.getRight());
        return null;
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
        if (rootNode.isParent()){
            return;
        }

    }

    @Override
    public ArrayList<BinaryTreeNode> pathTo(BinaryTreeNode descendant) {
        //give the search method a start (rootnode) and an end (descendant)
        ArrayList<BinaryTreeNode> descendantNodes = new ArrayList<>();

        return descendantNodes;
    }

    @Override
    public ArrayList<BinaryTreeNode> pathFrom(BinaryTreeNode ancestor) {
        //give the search method a start (ancestor) and an end (rootnode)
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
        //if(rootNode == null) return null;
        //traverseInorder(rootNode.getLeft());
        // just commented out because errors cringe fix it later

    }



}
