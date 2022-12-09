public class Question<E> extends LinkedBinaryTreeNode<E> {
    @Override
    public String toString() {
        return "Q:"+getData();
    }
    public Question(E question) {
        super.setData(question);
    }
}
