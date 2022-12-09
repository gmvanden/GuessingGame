import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class GuessingGame implements Game {
    private TreeDisplay display;
    private BinaryTreeNode<String> root;
    public GuessingGame(String filename) {
        loadTree(filename);
    }

    public void setRoot(LinkedBinaryTreeNode<String> root) {
        this.root = root;
    }

    @Override
    public BinaryTreeNode<String> loadTree(String filename) {
        File treeData = new File(filename);

        try (Scanner scan = new Scanner(treeData)) {
            String line = scan.nextLine();
            LinkedBinaryTreeNode<String> root;

            if (line.startsWith("Q:")) {
                root = new Question<>(line.substring(2));
                loadTreeUtil(root, loadTreeList(scan));
                this.root = root;
            } else if (line.startsWith("G:")) {
                root = new Guess<>(line.substring(2));
                loadTreeUtil(root, loadTreeList(scan));
                this.root = root;
            }
            if (display==null) {
                display = new TreeDisplay(this);
            } else {
                display.repaint();
            }
            return this.root;
        } catch (IOException e) {
            return null;
        }

    }
    public LinkedList<LinkedBinaryTreeNode<String>> loadTreeList(Scanner scan) {
        LinkedList<LinkedBinaryTreeNode<String>> list = new LinkedList<>();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            LinkedBinaryTreeNode<String> node;
            if (line.startsWith("Q:")) {
                node = new Question<>(line.substring(2));
                list.add(node);
            } else if (line.startsWith("G:")) {
                node = new Guess<>(line.substring(2));
                list.add(node);
            }
        }
        return list;
    }
    public void loadTreeUtil(LinkedBinaryTreeNode<String> root, LinkedList<LinkedBinaryTreeNode<String>> data) {
        if (!(root instanceof Guess)) {
            LinkedBinaryTreeNode<String> node = data.removeFirst();
            node.setParent(root);
            root.setLeft(node);
            loadTreeUtil(node,data);
            node = data.removeFirst();
            root.setRight(node);
            node.setParent(root);
            loadTreeUtil(node,data);
        }
    }
    @Override
    public void saveTree(String filename) {
        try {
            PrintStream og = System.out;
            PrintStream out = new PrintStream(new FileOutputStream(filename));
            System.setOut(out);
            root.traversePreorder(System.out::println);
            System.setOut(og);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BinaryTreeNode<String> getRoot() {
        return root;
    }

    @Override
    public void play() {
        Scanner scan = new Scanner(System.in);
        BinaryTreeNode<String> root = getRoot();
        System.out.println("Shall we play a game? (y/n)");
        String bool = scan.next();
        while (bool.equalsIgnoreCase("y")) {
            traverseTree(root,true);
            System.out.println("Shall we play a game? (y/n)");
            bool = scan.next();
            display.repaint();
        }
        System.out.println("Thanks for playing!");
        saveTree("output.data");
        System.exit(0);
    }
    public void traverseTree(BinaryTreeNode<String> root, Boolean goLeft) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        if (root.isLeaf()) {
            System.out.println("Are you thinking of a " + root.toString().substring(2) + "? (y/n)");
            if (scan.next().equalsIgnoreCase("n")) {
                System.out.println("You win!\nWhat are you thinking of?");
                Guess<String> guess = new Guess<>(scan.next());
                System.out.printf("What question separates a %s from a %s?\n",root.toString().substring(2), guess.toString().substring(2));
                Question<String> question = new Question<>(scan.next());
                System.out.printf("Is %s correct when the answer to %s is yes? (y/n)%n",guess.toString().substring(2),question.toString().substring(2));
                BinaryTreeNode<String> parent = root.getParent();
                if (parent.getRight() == root ) {
                    parent.setRight(question);
                } else {
                    parent.setLeft(question);
                }
                question.setParent(parent);
                guess.setParent(question);
                root.setParent(question);
                if (scan.next().equalsIgnoreCase("y")) {
                    question.setRight(guess);
                    question.setLeft(root);

                } else {
                    question.setRight(root);
                    question.setLeft(guess);
                }
            } else {
                System.out.println("I win!");
            }
        } else {
            System.out.println(root.toString().substring(2) + " (y/n)");
            if (scan.next().equalsIgnoreCase("n")) {
                goLeft = true;
            } else {
                goLeft = false;
            }
            if (goLeft) {
                traverseTree(root.getLeft(),false);
            } else {
                traverseTree(root.getRight(),false);
            }
        }
    }
    public static void main(String[] args) {
        GuessingGame game = new GuessingGame(args[0]);
        game.play();
    }
}
