import java.util.Objects;

public class Testing {

    public static void main(String[] args) {
        Guess cow = new Guess("cow");
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
        System.out.println(isAnimal.getData());

        GuessingGame game = new GuessingGame("fileName");
        game.play();
    }
}
