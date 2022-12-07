/**
 * @Auhtors Grace, Casen, Kam, Jake
 * @Date 12/7/2022
 * This class builds the dataset of the Guessing Game linkedBinaryTree
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("ALL")
public class LinkedBinaryTreeNode<E> implements BinaryTreeNode<E>{
    BinaryTreeNode rootNode = null;
    BinaryTreeNode parentNode = null;
    BinaryTreeNode leftNode = null;
    BinaryTreeNode rightNode = null;
    E data = null;
    @Override
    public E getData() {return data;}
    @Override
    public void setData(E data) {this.data = data;}
    @Override
    public BinaryTreeNode getRoot() {return rootNode;}
    @Override
    public BinaryTreeNode getParent() {return parentNode;}
    @Override
    public void setParent(BinaryTreeNode parent) { parentNode = parent;}
    @Override
    public BinaryTreeNode getLeft() {return leftNode;}
    @Override
    public void setLeft(BinaryTreeNode child) { leftNode = child; }
    @Override
    public BinaryTreeNode getRight() {return rightNode;}
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
    public int getDepth() {return getDepth(0,rootNode);}
    public int getDepth(int i, BinaryTreeNode node){
        if (node == null)
            return i;
        else {
            int lDepth = getDepth(i+1,node.getLeft());
            int rDepth = getDepth(i+1,node.getRight());
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
    @Override
    public int getHeight() {
        return getHeight(rootNode);
    }
    public int getHeight(BinaryTreeNode root){
        int depth = 0;

        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            BinaryTreeNode temp = q.peek();
            q.remove();
            if (temp == null) {
                depth++;
            }
            if (temp != null) {
                if (temp.getLeft() != null) {
                    q.add(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    q.add(temp.getRight());
                }
            }
            else if (!q.isEmpty()) {
                q.add(null);
            }
        }
        return depth;
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
        setChildrenNull(rootNode);
        rootNode=null;
    }
    public void setChildrenNull(BinaryTreeNode node){
        if(node.hasLeftChild())
            node.getLeft();
        if(node.hasRightChild())
            setChildrenNull(node.getRight());
        node = null;
    }
    @Override
    public ArrayList<BinaryTreeNode> pathTo(BinaryTreeNode descendant) {
        //give the search method a start (rootnode) and an end (descendant)
        return search(descendant);
    }
    @Override
    public ArrayList<BinaryTreeNode> pathFrom(BinaryTreeNode ancestor) {
        //give the search method a start (ancestor) and an end (rootnode)
        BinaryTreeNode tempNode1 = rootNode;
        rootNode = ancestor;
        ArrayList<BinaryTreeNode> pathToReturn = search(tempNode1);
        rootNode = tempNode1;
        return pathToReturn;
    }
    @Override
    public void traversePreorder(Visitor visitor) {
        visitor.visit(rootNode);
        if (rootNode.getRight() != null){
            traversePreorder(visitor,rootNode.getRight());
        }
        if (rootNode.getLeft() != null){
            traversePreorder(visitor, rootNode.getLeft());
        }

    }
    public void traversePreorder(Visitor visitor, BinaryTreeNode curNode) {
        if (rootNode.getRight() != null){
            traversePreorder(visitor,curNode.getRight());
        }
        if (rootNode.getLeft() != null){
            traversePreorder(visitor, curNode.getLeft());
        }
    }
    @Override
    public void traversePostorder(Visitor visitor) {
        if (rootNode.getLeft() != null){
            traversePostorder(visitor, rootNode.getLeft());
        }
        if (rootNode.getRight() != null){
            traversePostorder(visitor,rootNode.getRight());
        }
        visitor.visit(rootNode);
    }
    public void traversePostorder(Visitor visitor, BinaryTreeNode curNode) {
        //if (curNode == null) return; //need this?
        if (curNode.getLeft() != null){
            traversePostorder(visitor, curNode.getLeft());
        }
        if (curNode.getRight() != null){
            traversePostorder(visitor,curNode.getRight());
        }
    }
    @Override
    public void traverseInorder(Visitor visitor) {
        if (rootNode.getLeft() != null){
            traversePostorder(visitor, rootNode.getLeft());
        }
        visitor.visit(rootNode);
        if (rootNode.getRight() != null){
            traversePostorder(visitor,rootNode.getRight());
        }
    }
    public void traverseInorder(Visitor visitor, BinaryTreeNode curNode) {
        if (curNode.getLeft() != null){
            traverseInorder(visitor, curNode.getLeft());
        }
        if (curNode.getRight() != null){
            traverseInorder(visitor,curNode.getRight());
        }
    }
}
