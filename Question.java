public class Question<E> extends LinkedBinaryTreeNode {
    LinkedBinaryTreeNode<String> node = new LinkedBinaryTreeNode<>();
    public Question(String Q, LinkedBinaryTreeNode y, LinkedBinaryTreeNode n){
        node.setData(Q);
        node.setLeft(n); //if the question answer is no then it moves to the left
        node.setRight(y); //if the question answer is yes then it moves to the right
    }
    public Question(String Q, LinkedBinaryTreeNode y, LinkedBinaryTreeNode n,LinkedBinaryTreeNode prevQuestion){
        node.setParent(prevQuestion); // sets the parent to the previous question if there is one
        node.setData(Q);
        node.setLeft(n); //if the question answer is no then it moves to the left
        node.setRight(y); //if the question answer is yes then it moves to the right
    }


}
