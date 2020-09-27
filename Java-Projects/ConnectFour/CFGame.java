/* CFGame code - the backbones of the main Connect 4 game containing the 
 * logic that sets up the game board using a 2D array, playing moves,
 * and detecting winning moves (if a certain play results in 4 in a row.
 * 
 */
package ConnectFour;
import java.util.Arrays;

/**
 * The class containing all important code and set up for the game
 * 
 * @author David Boudreau
 */
public class CFGame {

    //state[i][j]= 0 means the i,j slot is empty
    //state[i][j]= 1 means the i,j slot has red
    //state[i][j]= -1 means the i,j slot has black
    private final int[][] state;
    private final boolean isRedTurn;
    private int turn;
    private int currentColumn; //keeps track of the column just played

    {
        state = new int[6][7];  //2D array of 6 rows, 7 columns
        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 6; j++) {
                state[i][j] = 0;    //fill the array with zeros
            }
        }
        isRedTurn = true; //red goes first
        turn = 1;
    }

    /**
     * Copies the state the board - is used often in many of the methods 
     * to avoid making changes to the actual board
     * 
     * @return copy of the game board
     */
    public int[][] getState() {

        int[][] ret_arr = new int[state.length][];
        for(int i = 0; i < state.length; i++){
            ret_arr[i] = state[i].clone();
        }
        
        return ret_arr;
    }

    /**
     * Red always goes first, so the mod of the turn number is used to
     * figure out whose turn it is
     * 
     * @return whether it's the red player's turn or not
     */
    public boolean isRedTurn() {

        if (turn % 2 == 1) {
            return isRedTurn;
        } else {
            return false;
        }
    }
    
    /**
     * If a column played is illegal, the reason is printed
     * 
     * @param column (column played)
     * @return whether a column played is legal or not
     */
    public boolean validColumn(int column){
        //Column outside of range
        if (column > 6 || column < 0) {
            System.out.println("Column outside range. Please pick a valid column.");
            return false;
        } 
        //Check if column is full
        else if (state[0][column] == 1 || state[0][column] == -1) {
            System.out.println("Column is full. Please pick a valid column.");
            return false;
        }
        return true;
    }
    
    /**
     * This method places the piece (-1 or 1) into the actual game board and
     * calls another method to determine if the play was a winning move
     * 
     * @param column (column played)
     * @return a call to the connected method taking only a row and col number
     */
    public boolean play(int column) { //pass index of column, not 1 based

        currentColumn = column;
        
        //Invalid column number
        if (validColumn(column) == false) {
            return false;
        } 
        else {   //if valid

            int row = -1; //the row the piece will be dropped into

            if (isRedTurn() == true) {    //if red turn
                for (int i = 5; i >= 0; i--) { //Check from the bottom up
                    if (state[i][column] == 0) {  //if empty
                        state[i][column] = 1;
                        row = i;
                        break;
                    }
                }
            } else {     //if black turn
                for (int i = 5; i >= 0; i--) { //Check from the bottom up
                    if (state[i][column] == 0) {  //if empty
                        state[i][column] = -1;
                        row = i;
                        break;
                    }
                }
            }
            
            showBoard(); //Every time play is called, the board is shown so both
                         //players can see it
           
            turn++;

            return connected(row, column);

        }

    }
    
    /**
     * Checks if a move is a winner without actually playing a move like the
     * play method. This was needed to track if the last move played is a winning
     * move without making changes to an array. It simply accesses the information.
     * 
     * @param column (column checked)
     * @return call to connected method that takes a copied array
     */
    public boolean check_last(int column){
        
        //Needed to avoid a bug when the game hasn't started yet
        if(turn <= 1){
            return false;
        }
        
        int[][] stateCopy = getState();
        int i = 0; //row of the first filled position
        
        while(stateCopy[i][column] == 0 && i <= 4){ //check from the top down
            i++;
        }
        
        return connected(i, column, stateCopy);

        
    }
    
    
    /**
     * Code is same as play, but column is passed to a copy of state array.
     * Used to play a move into a copy of the array to check if it's a winner.
     * This was made when an issue came up when coding the 2 AI classes. The 
     * smart AI was unable to check for winning moves with the play method because
     * it would make moves while doing the checks.
     * 
     * @param column (column checked)
     * @return a call to the connected method that takes a copied array
     */
    public boolean winningMove(int column){
        
        int[][] stateCopy = getState();
        
        
        if (validColumn(column) == false) {
            return false;
        } 
         else {   //if valid

            int row = -1; //the row the piece will be dropped into

            if (isRedTurn() == true) {    //if red turn
                for (int i = 5; i >= 0; i--) { //Check from the bottom up
                    if (stateCopy[i][column] == 0) {  //if empty
                        stateCopy[i][column] = 1;
                        row = i;
                        break;
                    }
                }
            } else {     //if black turn
                for (int i = 5; i >= 0; i--) { //Check from the bottom up
                    if (stateCopy[i][column] == 0) {  //if empty
                        stateCopy[i][column] = -1;
                        row = i;
                        break;
                    }
                }
            }

            return connected(row, column, stateCopy);

        }
    }
    
    /**
     * Similar to winningMove, but checks for the opponent's winning move
     * to block it
     * 
     * @param column (checked column)
     * @return call to connected to check for opponent's winning move
     */
    public boolean blockingMove(int column){
        
        int[][] stateCopy = getState();
        
        
        if (validColumn(column) == false) {
            return false;
        } 
         else {   //if valid

            int row = -1; //the row the piece will be dropped into

            //If red's turn, look for black's winning move
            if (isRedTurn() == true) {
                for (int i = 5; i >= 0; i--) { //Check from the bottom up
                    if (stateCopy[i][column] == 0) {  //if empty
                        stateCopy[i][column] = -1;
                        row = i;
                        break;
                    }
                }
            } 
            //If black's turn, look for red's winning move
            else {
                for (int i = 5; i >= 0; i--) { //Check from the bottom up
                    if (stateCopy[i][column] == 0) {  //if empty
                        stateCopy[i][column] = 1;
                        row = i;
                        break;
                    }
                }
            }

            return connected(row, column, stateCopy);

        }
    }
    
    /**
     * One of the connected methods taking a copy of the board. It checks
     * vertical, horizontal, and diagonals (lower left to upper right, lower
     * right to upper left)
     * 
     * @param a (row number)
     * @param b (column number)
     * @param tempState (copied array of board)
     * @return whether the move passed is a 4 in a row
     */
    public boolean connected(int a, int b, int[][] tempState){
        
        int current = tempState[a][b]; //Color 1 or -1 of the piece

        //Check vertical 4 in a row
        int counter = 0;
        int start_row = a;
        
        //Start from location and check locations directly below
        while (start_row < 5 && tempState[start_row + 1][b] == current) {
            counter++;
            start_row++;
        }

        if (counter >= 3) { //if 4 in a row, return true
            return true;
        }

        //Check horizontal 4 in a row
        counter = 0;
        int start_col = b;

        //Start from location and check locations to the right
        while (start_col < 6 && tempState[a][start_col + 1] == current) {
            counter++;
            start_col++;
        }

        start_col = b;
        
        //Start from location and check locations to the left
        while (start_col > 0 && tempState[a][start_col - 1] == current) {
            counter++;
            start_col--;
        }

        if (counter >= 3) {
            return true;
        }

        //Check diagonal: upper left to lower right
        counter = 0;
        start_row = a;
        start_col = b;

        while (start_row < 5 && start_col < 6 && tempState[start_row + 1][start_col + 1] == current) {
            counter++;
            start_row++;
            start_col++;
        }
        
        //Check diagonal: lower right to upper left
        start_row = a;
        start_col = b;
        while (start_row > 0 && start_col > 0 && tempState[start_row - 1][start_col - 1] == current) {
            counter++;
            start_row--;
            start_col--;
        }

        if (counter >= 3) {
            return true;
        }

        //Check diagonal: upper right to lower left
        counter = 0;
        start_row = a;
        start_row = b;

        while (start_row < 5 && start_col > 0 && tempState[start_row + 1][start_col - 1] == current) {
            counter++;
            start_row++;
            start_col--;
        }

        //Check diagonal: lower left to upper right
        start_row = a;
        start_col = b;

        while (start_row > 0 && start_col < 6 && tempState[start_row - 1][start_col + 1] == current) {
            counter++;
            start_row--;
            start_col++;
        }

        if (counter >= 3) {
            return true;
        }

        
        
        return false;
    }

    //Real state: check if last play was 4 in a row (row, column)
    /**
     * Does the same as the connected above, but looks at the actual board, not
     * a copy
     * 
     * @param a (row number)
     * @param b (column number)
     * @return whether the move passed is a 4 in a row
     */
    public boolean connected(int a, int b) {

        int current = state[a][b]; //Color 1 or -1 of the piece

        //Check vertical 4 in a row
        int counter = 0;
        int start_row = a;
        
        //Start from location and check locations directly below
        while (start_row < 5 && state[start_row + 1][b] == current) {
            counter++;
            start_row++;
            //System.out.println(start_row);
        }

        if (counter >= 3) { //if 4 in a row, return true
            return true;
        }

        //Check horizontal 4 in a row
        counter = 0;
        int start_col = b;

        //Start from location and check locations to the right
        while (start_col < 6 && state[a][start_col + 1] == current) {
            counter++;
            start_col++;
        }

        start_col = b;
        
        //Start from location and check locations to the left
        while (start_col > 0 && state[a][start_col - 1] == current) {
            counter++;
            start_col--;
        }

        if (counter >= 3) {
            return true;
        }

        //Check diagonal: upper left to lower right
        counter = 0;
        start_row = a;
        start_col = b;

        while (start_row < 5 && start_col < 6 && state[start_row + 1][start_col + 1] == current) {
            counter++;
            start_row++;
            start_col++;
        }
        
        //Check diagonal: lower right to upper left
        start_row = a;
        start_col = b;
        while (start_row > 0 && start_col > 0 && state[start_row - 1][start_col - 1] == current) {
            counter++;
            start_row--;
            start_col--;
        }

        if (counter >= 3) {
            return true;
        }

        //Check diagonal: upper right to lower left
        counter = 0;
        start_row = a;
        start_row = b;

        while (start_row < 5 && start_col > 0 && state[start_row + 1][start_col - 1] == current) {
            counter++;
            start_row++;
            start_col--;
        }

        //Check diagonal: lower left to upper right
        start_row = a;
        start_col = b;

        while (start_row > 0 && start_col < 6 && state[start_row - 1][start_col + 1] == current) {
            counter++;
            start_row--;
            start_col++;
        }

        if (counter >= 3) {
            return true;
        }

        return false;

    }

    /**
     * If the board is full (max moves 42) it's true, otherwise call
     * the method to check if the last play was a winning move
     * 
     * @return a call to check the last move played
     */
    public boolean isGameOver() {

        //if all spaces filled
        if (turn == 42) {
            return true;
        } 

        return check_last(currentColumn);
    }

    /**
     * If the game is over, the winner is determined
     * 
     * @return -1 if black piece won, 1 if red won, or 0 for a draw
     */
    public int winner() {

        if (isGameOver() == true) {
            //if board is full with no winner, it's a draw
            if (turn == 42 && check_last(currentColumn) == false) {
                return 0;

            } //if there is a winner
            else if (check_last(currentColumn) == true) {

                //if it's red's turn, black wins due to increment in play method
                if (isRedTurn() == true) {
                    return -1;
                } //if it's not red's turn, red wins due to increment in play method
                else {
                    return 1;
                }

            }
        }

        //if something went wrong or isGameOver is false
        return 25;
        

    }
    
    /**
     * Prints the board with bars to better represent the state of the game.
     * Open slots are shown by a 0 and taken ones have either a 1 or -1 for
     * each player
     * 
     */
    public void showBoard(){
        
        System.out.println("Turn number: " + turn); //Turn number is shown above
        
        //Column labels C0 to C6 shown above
        for(int lbl = 0; lbl <= 6; lbl++){
            System.out.print(" | C" + lbl + "| ");
        }
        
        System.out.println();
        
        for (int i = 0; i <= 5; i++) {
                for (int j = 0; j <= 6; j++) {
                    if(state[i][j] == 1 || state[i][j] == 0){
                        System.out.print(" | " + state[i][j] + " | ");  
                    }
                    else if(state[i][j] == -1){
                        System.out.print(" | " + state[i][j] + "| ");
                    }
                }
                System.out.println();
                System.out.println();
            }
    }
    
    /**
     * This was made for testing purposes to show the copied state
     * 
     * @param tempBoard (2D array)
     */
    public void showTempBoard(int[][] tempBoard){
        System.out.println(Arrays.deepToString(tempBoard));
    }
    
    
    //FOR TESTING: The methods all work as they should. Uncomment this code
    //to see how the methods work.
    /*public static void main(String[] args) {

        CFGame game = new CFGame();
        //game.showBoard();
        
        //Turn 1
        System.out.println(game.play(0));
        //Turn 2
        System.out.println(game.play(1));
        //Check last move
        System.out.println("Last move winner?: " + game.check_last(1));
        //Turn 3
        System.out.println(game.play(0));
        //Turn 4
        System.out.println(game.play(1));
        //Turn 5
        System.out.println(game.play(0));
        //Turn 6
        System.out.println(game.play(1));
        //Dummy turn 7 - check if playing column 0 is a win
        System.out.println(game.winningMove(0));
        System.out.println(game.isGameOver());
        //Turn 7
        System.out.println(game.play(0));
        System.out.println(game.isGameOver());
        //Turn 8
        System.out.println(game.play(2));
        System.out.println(game.isGameOver());
        
        /*System.out.println(game.play(0));
        System.out.println(game.play(1));
        System.out.println(game.winningMove(0));
        System.out.println(game.play(0));
        System.out.println(game.winningMove(0));
        System.out.println(game.play(0));*/
        
        
        
        /*System.out.println(game.play(5));
        System.out.println(game.play(5));
        System.out.println(game.play(4));
        System.out.println(game.play(4));
        System.out.println(game.play(3));
        System.out.println(game.play(3));
        System.out.println(game.play(2));
        System.out.println(game.play(2));
        System.out.println(game.play(1));
        System.out.println(game.play(1));
        System.out.println(game.play(0));
        System.out.println(game.play(0));
       
        
       
        

    }*/

}
