/*
 * This file contains the SmartAI class and its methods. The SmartAI is 
 * different from the RandomAI in that it plays its move with basic strategy
 * such as checking to see if the opponent has a winning move and if so,
 * it blocks it. It also checks to see if it has a winning move and plays it.
 */
package ConnectFour;

import java.util.Scanner;

/**
 * SmartAI class and its methods
 * 
 * @author David Boudreau
 */
public class SmartAI implements CFPlayer {

    public String name;
    public String color;
    
    /**
     * The AI checks for a winning a move first then a blocking move.
     * If neither is found, it uses a RandomAI to play a random move.
     * 
     * @param g (CFGame object)
     * @return an int column number between 0 and 6
     */
    @Override
    public int nextMove(CFGame g) {

        int columnToPlay = -1;
        RandomAI rand = new RandomAI();
        int checks = 0;
        
        //Check columns for winning move
        for (int j = 0; j <= 6; j++) {
            //If winning move found
            if (g.winningMove(j) == true) { //Check each column of a copied board
                columnToPlay = j;           //for a winning move
                System.out.println("Winning move");
                break;
            } 
            checks++;
        }
        System.out.println("Checks: " + checks);
        
        //If column not picked yet from winning move, check for blocking move
        if(columnToPlay < 0){
        
            for (int j = 0; j <= 6; j++) {
                //if blocking move found
                if(g.blockingMove(j) == true){ //Check each column of a copied
                    columnToPlay = j;          //board for opponent's winning move
                    System.out.println("Blocking move");
                    break;
                }  
                checks++;
            }
        }
        System.out.println("Checks: " + checks);
        
        //If no winning or blocking move found, play random move
        if(checks >= 14){
            columnToPlay = rand.nextMove(g);
            System.out.println("Random move");
        }
        return columnToPlay;

    }
    
    /**
     * Prompts the user to the type a name for the SmartAI and sets
     * the name variable to it
     */
    @Override
    public void setName(){
        System.out.println("Enter a name for the Smart AI: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        name = input;
    }

    /**
     * 
     * @return name of the AI
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Sets the color of the AI
     * 
     * @param col (red or black)
     */
    @Override
    public void setColor(String col){
        color = col;
    }
    
    /**
     * 
     * @return color of the AI
     */
    @Override
    public String getColor(){
        return color;
    }

}
