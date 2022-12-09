public class Guess<E> extends LinkedBinaryTreeNode<E> {
    @Override
    public String toString() {
        return "G:"+getData();
    }
    public Guess(E guess) {
        super.setData(guess);
    }
}
