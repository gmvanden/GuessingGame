/**
 * @Auhtors Grace, Casen, Kam, Jake
 * @Date 12/7/2022
 * This Class runs the main program where the GuessingGame takes place asking the questions and answer them
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Index {

    int index = 0;
}
@SuppressWarnings("ALL")
public class GuessingGame implements Game{
    ArrayList<String> tree = new ArrayList<>();
    Index index = new Index();
    ArrayList<LinkedBinaryTreeNode> secondaryTree = new ArrayList<>();
    String fileName = null;
    @Override
    public BinaryTreeNode<String> loadTree(String filename) { //do this first
        //de-compile strings and use information within them and the order of them to organize them into an array of Nodes
        try{
            File file = new File(filename);
            Scanner fIn = new Scanner(file);
            while(fIn.hasNext()){
                String Line = fIn.nextLine();
                tree.add(Line);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("file name incorrect or file not found");
        }

        return constructTree(tree, tree.size());
    }
    LinkedBinaryTreeNode constructTreeUtil(List<String> pre, Index preIndex, int low, int high, int size)
    {
        if (preIndex.index >= size || low > high) {
            return null;
        }
        LinkedBinaryTreeNode root = new Question(pre.get(preIndex.index));
        preIndex.index = preIndex.index + 1;

        if (low == high) {
            return root;
        }

        int i;
        for (i = low; i <= high; ++i) {
            if (pre.get(i).compareTo((String) root.data) > 0) {
                break;
            }
        }
        root.setLeft(constructTreeUtil(pre, preIndex, preIndex.index, i - 1, size));
        root.setRight(constructTreeUtil(pre, preIndex, i, high, size));
        return root;
    }
    LinkedBinaryTreeNode constructTree(List<String> pre, int size)
    {
        return constructTreeUtil(pre, index, 0, size - 1,
                size);
    }
    @Override
    public void saveTree(String filename) { // do this last
        //PREORDER
        //load those Strings in the proper order into the file
        try {
            FileWriter myWriter = new FileWriter(fileName);
            String fileContents = "";
            myWriter.flush();

            //start at rootof tree(0), go recursively right, then left
            //preorderArrayFill(tree.get(0));
            //tree=secondaryTree;

            //traverse through tree and add each .data to a String
            //for (LinkedBinaryTreeNode node:tree) {
             //   fileContents = fileContents+ node.getData().toString()+"\n";
            //}

            myWriter.write(fileContents);
            myWriter.close();
            tree.clear();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error writing the file occurred.");
            e.printStackTrace();
        }
    }
    public void preorderArrayFill(LinkedBinaryTreeNode root){
        // return if the current node is empty
        if (root == null) {
            return;
        }

        // Display the data part of the root (or current node)
        secondaryTree.add(root);

        // Traverse the left subtree
        preorderArrayFill((LinkedBinaryTreeNode) root.getRight());

        // Traverse the right subtree
        preorderArrayFill((LinkedBinaryTreeNode) root.getLeft());
    }

    @Override
    public BinaryTreeNode<String> getRoot() {
        return null;
    } //do this second

    @Override
    public void play() { //do this third
        System.out.println("Welcome to the Guessing Game!\nShall we play a game?(y/n)");
        Scanner in = new Scanner(System.in);
        Boolean playing = true;
        Boolean questioning = true;
        if(in.nextLine().contains("n")){ //establishes if playing or not
            playing = false;
        }
        while(playing){
            //load tree
            loadTree(fileName);
            LinkedBinaryTreeNode currNode= null;
            while(questioning) {
                //output question
                if(currNode.isLeaf()){
                    System.out.println("Are you thinking of a "+currNode.getData().toString().substring(2).toLowerCase()+"?(y/n)");
                } else {
                    System.out.println(currNode.getData().toString().substring(2)+"(y/n)");
                }
                //input answer
                String response = in.nextLine();
                //evaluate answer

                //ask question, if useranswer == answer, computer wins
                // if useranswer != answer, ask the next question
                if(response.contains("y")){ // yes to the right
                    //continue down the tree until reach leaf
                    if(currNode.isLeaf()){
                        System.out.println("I win!");
                        questioning=false;
                    } //keep going
                    currNode= (LinkedBinaryTreeNode) currNode.getLeft();

                } else if (response.contains("n")){//no to the left
                    //need to check if this node is a leaf
                    //if leaf, then say "it is a *guess*".
                    //else, then ask question
                    if(currNode.isLeaf()){
                        System.out.println("You win!");
                        System.out.println("What are you thinking of? ");
                        String newGuess = "G:"+in.nextLine();
                        System.out.println("Great! What question defines a "+ newGuess.substring(2) + "?");
                        String newQ = "Q:"+in.nextLine();
                        Question<String> newQuestionObj = new Question<>(newQ);
                        Guess<String> newGuessObj = new Guess<>(newGuess);

                        int indexToReplace = tree.indexOf(currNode);

                        //not sure if i'll need these rn or not
                        LinkedBinaryTreeNode tempNode = (LinkedBinaryTreeNode) currNode;
                        currNode=newQuestionObj;
                        currNode.setLeft(newGuessObj);
                        currNode.setRight(tempNode);

                        //tree.set(indexToReplace, (LinkedBinaryTreeNode) currNode);

                        saveTree(fileName);
                        questioning=false;
                    }
                    currNode= (LinkedBinaryTreeNode) currNode.getRight();
                }
            }

            System.out.println("Would you like to play again?(y/n)\n"); // make sure this is always the last thing to run
            String playAgain = in.nextLine();
            if(playAgain.contains("n")){
                playing=false;
                questioning=false;
                break;
            } else if (playAgain.contains("y")) {
                playing=true;
                questioning=true;
                currNode=null;
            }
        }
    }
    public GuessingGame( String filename ){
        this.fileName = filename;
    }

}
