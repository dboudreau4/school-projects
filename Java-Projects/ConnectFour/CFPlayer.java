/*
 * Interface needed for the 2 AI and human player classes
 */
package ConnectFour;

/**
 * Interface setting up the basics for any kind of player in the game
 * 
 * @author David Boudreau
 */
public interface CFPlayer {
    
    
    /**
     * How players make a move
     * 
     * @param g (game object)
     * @return column number of the move
     */
    int nextMove(CFGame g);

    /**
     * Set the name of the player from user input
     * 
     */
    void setName();
    
    /**
     * Get the name of the player
     * 
     * @return name of the player
     */
    String getName();
    
    /**
     * Set the color (red or black) of a player
     * 
     * @param color 
     */
    void setColor(String color);
    
    /**
     * Get the color of the player
     * 
     * @return color of the player
     */
    String getColor();

}
