/*
 * This file runs the connect four game in the console. It has three constructors:
 * human vs AI, AI vs AI, and human vs human. It also contains the class to
 * set up the human player and its methods.
 */
package ConnectFour;

import java.util.Random;
import java.util.Scanner;

/**
 * This class uses the methods from the CFGame class
 * 
 * @author David Boudreau
 */
public class ConsoleCF extends CFGame {

    CFPlayer player1;   //red
    CFPlayer player2;   //black

    /**
     * Constructor to set up a human vs. computer (random or smart) game.
     * With all ConsoleCF constructors, the player who goes first is
     * randomly chosen.
     * 
     * @param ai (random or smart AI player)
     */
    public ConsoleCF(CFPlayer ai) {

        HumanPlayer human = new HumanPlayer();
        Random rand = new Random();
        int n = rand.nextInt(100);

        //AI goes first
        if (n % 2 == 0) {
            player1 = ai;
            player2 = human;
        } 
        //Human goes first
        else {
            player1 = human;
            player2 = ai;
        }
        
        //colors are set - player 1 is always red
        //these are not used for the console version, but are important
        //for the GUI version
        player1.setColor("red");
        player2.setColor("black");

    }
 
    /**
     * Constructor to put 2 AI against each other (any combination: random vs
     * random, random vs smart, or smart vs smart)
     * 
     * @param ai1 (random or smart player)
     * @param ai2 (random or smart player)
     */
    public ConsoleCF(CFPlayer ai1, CFPlayer ai2) {

        Random rand = new Random();
        int n = rand.nextInt(100);

        if (n % 2 == 0) {
            player1 = ai1;
            player2 = ai2;
        } else {
            player1 = ai2;
            player2 = ai1;
        }
        
        player1.setColor("red");
        player2.setColor("black");

    }
    
    /**
     * Constructor to set up a human vs. human game
     * 
     */
    public ConsoleCF() {

        HumanPlayer human1 = new HumanPlayer();
        HumanPlayer human2 = new HumanPlayer();
        Random rand = new Random();
        int n = rand.nextInt(100);

        //human1 goes first
        if (n % 2 == 0) {
            player1 = human1;
            player2 = human2;
        } 
        //human2 goes first
        else {
            player1 = human2;
            player2 = human1;
        }
        
        player1.setColor("red");
        player2.setColor("black");

    }

    /**
     * Method to play a game all the way through
     * 
     */
    public void playOut() {
        int turn_num = 1; //same as turn from CFGame
        System.out.println("****Player 1 Name Choice****");
        player1.setName();  //prompts user to name self or AI opponent through input
        System.out.println("****Player 2 Name Choice****");
        player2.setName(); //prompts user to name self or AI opponent
        System.out.println("Player 1: " + player1.getName()); //prints the player names
        System.out.println("Player 2: " + player2.getName());
        System.out.println();
        
        while (turn_num <= 42) {
                
                //says player 1's turn
                System.out.println(player1.getName() + "'s turn");
                play(player1.nextMove(this)); //player 1 moves
                turn_num++;
                
                if(isGameOver() == true){ //check if game is over
                    break;
                }
            
                System.out.println(player2.getName() + "'s turn");
                play(player2.nextMove(this));
                turn_num++;
                
                if(isGameOver() == true){
                    break;
                }
                
        }
    }

    /**
     * Triggered when game ends
     * 
     * @return name of the winner
     */
    public String getWinner() {

        switch (winner()) {
            case 0:
                return "draw";
            case 1:
                //red wins
                return player1.getName() + " wins";
            case -1:
                return player2.getName() + " wins";
            default:
                return "Error";
        }

    }

    /**
     * Class setting up a human player and its methods
     * 
     */
    private static class HumanPlayer implements CFPlayer {
        
        public String name;
        public String color;
        
        /**
         * Takes user input for a column to play
         * 
         * @param g (CFGame object)
         * @return column number entered
         */
        @Override
        public int nextMove(CFGame g) {
            
            System.out.println("Enter a column number: ");
            Scanner move = new Scanner(System.in);
            int a = move.nextInt();

            while (g.validColumn(a) == false) {
                System.out.println("Please enter a new column number: ");
                a = move.nextInt();
            }

            return a;

        }
        
        /**
         * Takes user input to name the player
         */
        @Override
        public void setName(){
            System.out.println("Enter your name: ");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            name = input;
        }
        
        /**
         * 
         * @return name of the player
         */
        @Override 
        public String getName() {
            return name;
        }
        
        @Override
        public void setColor(String col){
            color = col;
        }
    
        /**
         * 
         * @return color of the player (red or black)
         */
        @Override
        public String getColor(){
            return color;
        }

    }

    public static void main(String[] args) {
        /*TESTING FOR HUMAN VS. RANDOM AI*
        RandomAI r = new RandomAI();
        
        ConsoleCF human_v_randomAI = new ConsoleCF(r);
        human_v_randomAI.playOut();
        System.out.println(human_v_randomAI.getWinner());*/
        
        /*TESTING FOR HUMAN VS. HUMAN
        ConsoleCF human_v_human = new ConsoleCF();
        human_v_human.playOut();
        System.out.println(human_v_human.getWinner());*/
        
        /*TESTING FOR HUMAN VS. SMART AI*/
        /*SmartAI smart = new SmartAI();
        
        ConsoleCF human_v_smartAI = new ConsoleCF(smart);
        human_v_smartAI.playOut();
        System.out.println(human_v_smartAI.getWinner());*/
        
        /*TESTING FOR SMART AI VS. SMART AI
        SmartAI smart1 = new SmartAI();
        SmartAI smart2 = new SmartAI();
        
        ConsoleCF smart_v_smart = new ConsoleCF(smart1, smart2);
        smart_v_smart.playOut();
        System.out.println(smart_v_smart.getWinner());*/
        
        /*TESTING FOR SMART VS. RANDOM AI
        SmartAI smart = new SmartAI();
        RandomAI rand = new RandomAI();
        
        ConsoleCF smart_v_random = new ConsoleCF(smart, rand);
        smart_v_random.playOut();
        System.out.println(smart_v_random.getWinner());*/
    }

}
