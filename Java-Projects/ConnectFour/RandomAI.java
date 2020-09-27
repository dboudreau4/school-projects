/*
 * This file contains the RandomAI class, which is a player that plays
 * random moves with no strategy by choosing random columns.
 */
package ConnectFour;

/**
 * This class contains the Random AI methods implemented from the
 * CFPlayer interface
 * 
 * @author David Boudreau
 */
import java.util.Random;
import java.util.Scanner;

public class RandomAI implements CFPlayer {
    
    public String name;
    public String color;
    
    /**
     * Has the AI choose a random number between 0 and 6
     * 
     * @param g (CFGame object)
     * @return an int between 0 and 6 
     */
    @Override
    public int nextMove(CFGame g) {

        
        Random rand = new Random();
        int n = rand.nextInt(7);

         //Invalid move - play a different move
        while (g.validColumn(n) == false) {
            n = rand.nextInt(7);
        }
        System.out.println("Column picked: " + n);
        return n;

    }

    /**
     * Prompts the user to type a name for the AI and sets the name
     * variable to that input
     */
    @Override
    public void setName(){
        System.out.println("Enter a name for the Random AI: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        name = input;
    }
    
    /**
     * 
     * @return the name of the AI
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Sets the color of the AI (red or black)
     * 
     * @param col (color)
     */
    @Override
    public void setColor(String col){
        color = col;
    }
    
    /**
     * 
     * @return color (red or black)
     */
    @Override
    public String getColor(){
        return color;
    }

}
