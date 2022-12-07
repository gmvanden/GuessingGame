/**
 * @Auhtors Grace, Casen, Kam, Jake
 * @Date 12/7/2022
 * This Class runs the main program where the GuessingGame takes place asking the questions and answer them
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
@SuppressWarnings("ALL")
public class GuessingGame implements Game{
    ArrayList<LinkedBinaryTreeNode> tree = new ArrayList<>();
    String fileName = null;
    @Override
    public BinaryTreeNode<String> loadTree(String filename) { //do this first
        //de-compile strings and use information within them and the order of them to organize them into an array of Nodes
        try{
            File file = new File(filename);
            Scanner fIn = new Scanner(file);
            while(fIn.hasNext()){
                String Line = fIn.nextLine();
                if(Line.charAt(0) == 'Q'){
                    //String nodeName = Line.substring(2,Line.length());
                    LinkedBinaryTreeNode<String> node = new Question<>(Line);
                    tree.add(node);
                }
                if(Line.charAt(0) == 'G'){
                    //String nodeName = Line.substring(2,Line.length());
                    LinkedBinaryTreeNode<String> node = new Guess<>(Line);
                    tree.add(node);
                }
            }


        }catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("file name incorrect or file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null; // return root node
    }

    @Override
    public void saveTree(String filename) { // do this last
        //compile tree into Array of Strings that can be input into a file
        //compilation of Strings will be done real time during the game

        //PREORDER
        //load those Strings in the proper order into the file
        try {
            FileWriter myWriter = new FileWriter(fileName);
            String fileContents = "";
            //traverse through tree and add each .data to a String
            for (LinkedBinaryTreeNode node:tree) {
                fileContents += node.getData();
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error writing the file occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public BinaryTreeNode<String> getRoot() {
        return tree.get(0);
    } //do this second

    @Override
    public void play() { //do this third
        System.out.println("Welcome to the Guessing Game!\nShall we play a game?(y/n)");
        Scanner in = new Scanner(System.in);
        Boolean playing = true;
        Boolean Questioning = true;
        if(in.nextLine().contains("n")){ //establishes if playing or not
            playing = false;
        }
        while(playing){
            //load tree
            loadTree(fileName);
            int iterativeI=0;
            while(Questioning) {
                //establish question using root and branches


                //output question
                System.out.println(tree.get(iterativeI).getData().toString()+"(y/n)");
                //input answer
                String response = in.nextLine();
                //evaluate answer

                //ask question, if useranswer == answer, computer wins
                // if useranswer != answer, ask the next question
                if(response.contains("y")){ //no to the left, yes to the right
                    //update iterativeI -- need to figure out how to up it accurately -- something with checking left and right children
                    //continue down the tree until reach leaf
                    if(tree.get(iterativeI).isLeaf()){
                        System.out.println("I win!");
                        Questioning=false;
                        break;
                    } //keep going
                } else if (response.contains("n")){//no to the left, yes to the right
                    //update iterativeI
                    //need to check if this node is a leaf
                    //if leaf, then say "it is a *guess*".
                    //else, then ask question
                    if(tree.get(iterativeI).isLeaf()){
                        System.out.println("You win!");
                        System.out.println("What are you thinking of? ");
                        String newGuess = in.nextLine();
                        System.out.println("Great! What question defines a "+ newGuess + "?");
                        String newQ = in.nextLine();
                        Question<String> newQuestionObj = new Question<>(newQ);
                        Guess<String> newGuessObj = new Guess<>(newGuess);
                        tree.add(newQuestionObj);
                        tree.add(newGuessObj);
                        Questioning=false;
                        break;
                    }
                }
                iterativeI++;
            }

            //save tree
            System.out.println("would you like to play again?(y/n)\n"); // make sure this is always the last thing to run
            if(in.nextLine().contains("n")){
                playing=false;
                break;
            }
        }
    }
    public GuessingGame( String filename ){
        this.fileName = filename;
    }

}
