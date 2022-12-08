public class Testing {

    public static void main(String[] args) {
/*      Guess cow = new Guess("cow");
        Guess rock = new Guess("ROCK");
        Guess gnat = new Guess("Gnat");
        Guess shoestring = new Guess("shoestring");
        Question isAnimal = new Question<>("is it an animal?");

        cow.setParent(isAnimal);
        rock.setParent(isAnimal);


        System.out.println(cow.getDepth());
        System.out.println(isAnimal.getHeight());
        System.out.println(cow.getParent().getData());
        System.out.println(cow.getData());
        System.out.println(isAnimal.getData());*/

        GuessingGame game = new GuessingGame("tree4.data");
        BinaryTreeNode<String> root = game.loadTree("tree4.data");
        printPreorder(root);
        //game.play();
    }
    public static void printPreorder(BinaryTreeNode node)
    {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.getData().toString() + "\n");

        /* then recur on left subtree */
        printPreorder(node.getLeft());

        /* now recur on right subtree */
        printPreorder(node.getRight());
    }

    // Wrappers over above recursive functions
}
