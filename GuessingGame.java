import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
                    String nodeName = Line.substring(2,Line.length());
                    LinkedBinaryTreeNode<String> node = new Question<>(nodeName);
                    //set left and right nodes if they are not null
                    tree.add(node);
                }
                if(Line.charAt(0) == 'G'){
                    String nodeName = Line.substring(2,Line.length());
                    LinkedBinaryTreeNode<String> node = new Guess<>(nodeName);
                    //set left and right nodes if they are not null
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


        //load those Strings in the proper order into the file
    }

    @Override
    public BinaryTreeNode<String> getRoot() {
        return tree.get(0);
    } //do this second

    @Override
    public void play() { //do this third
        System.out.println("Welcome to the guessing game!\nShall we play a game?(y/n)");
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
                //set left side
                if(tree.size() > iterativeI+2){
                    if ((tree.get(iterativeI + 1) == null)) {
                        tree.get(iterativeI).setLeft(null);
                    } else {
                        tree.get(iterativeI).setLeft(tree.get(iterativeI + 1));
                    }
                    //set right side
                    if ((tree.get(iterativeI + 2) == null)) {
                        tree.get(iterativeI).setRight(null);
                    } else {
                        tree.get(iterativeI).setRight(tree.get(iterativeI + 2));
                    }
                }
                //output question
                System.out.println(tree.get(iterativeI).getData().toString());
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
                        break;
                    }
                } else if (response.contains("n")){//no to the left, yes to the right
                    //update iterativeI
                    //need to check if this node is a leaf
                    //if leaf, then say "it is a *guess*".
                    //else, then ask question

                    if(tree.get(iterativeI).isLeaf()){
                        System.out.println("Are you thinking of a "+tree.get(iterativeI).getData().toString().toLowerCase()+"? (y/n)");
                    } else {

                    }


                    System.out.println("Question: "+tree.get(iterativeI).getData()+"(y/n)");
                }

                iterativeI++;

                //determine continuation, victory or loss

                // if loss or victory is determined, break loop
                //if(lost || won){Questioning = false; break;}
                Questioning = false;
            }

            //save tree
            System.out.println("would you like to play again?(y/n)\n"); // make sure this is always the last thing to run
            if(in.nextLine().contains("n")){
                saveTree(fileName+"ll");
                break;
            }
        }
    }
    public GuessingGame( String filename ){
        this.fileName = filename;
    }

}
