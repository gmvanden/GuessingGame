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
                if(Line.substring(0) == "Q"){
                    String nodeName = Line.substring(2,Line.length());
                    LinkedBinaryTreeNode<String> node = new Question<>(nodeName);
                    tree.add(node);
                }
                if(Line.substring(0) == "Q"){
                    String nodeName = Line.substring(2,Line.length());
                    LinkedBinaryTreeNode<String> node = new Guess<>(nodeName);
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
        return null;
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
            while(Questioning) {
                //establish question using root and branches
                //output question
                //input answer
                //evaluate answer
                //determine continuation, victory or loss

                // if loss or victory is determined, break loop
                //if(lost || won){Questioning = false; break;}
                Questioning = false;
            }

            //save tree
            System.out.println("would you like to play again?(y/n)\n"); // make sure this is always the last thing to run
            if(in.nextLine().contains("n")){
                playing = false;
                break;
            }
        }
    }
    public GuessingGame( String filename ){
        this.fileName = filename;
    }

}
