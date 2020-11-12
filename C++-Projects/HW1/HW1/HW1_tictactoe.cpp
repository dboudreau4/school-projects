/*
 * Author: David Boudreau 
 * Initially written for UCLA PIC10B homework assignment
 * Initially written: January 11, 2019
 * Edited: October 26, 2020
 * 
 * This program sets up a tic tac toe game between two players in the console. A class was created to keep track of player names, 
 * scores, and tokens (x or o) with member functions to allow access and manipulation of variables. Player names are inputted and 
 * stored in individual player objects and intial scores are set to 0. The console prompts the player to input a row and column
 * number to place their token and the tokens are placed in a 2D 4x4 vector of strings. The first column and first row contain the 
 * column and row numbers, so those values are never manipulated and it's assumed that the players will only enter values of 1, 2, 
 * or 3 for the row or column values (ignore zero based indexing for placing tokens) so that the row and column labels are never touched. 
 * The number of rounds is chosen through user input and once the rounds are exhausted, the program ends and displays the score results 
 * along with who won the series.
 *
 * Before this edit, this program was not concise. I manage to cut it down between 100 and 150 lines by writing functions to place
 * tokens, to check if a move is valid, and to check for wins. Memory efficiency was not a priority for this project, but it can 
 * definitely be improved.
*/

#include<iostream>
#include<string>
#include<vector>

/*Creates the Player class with all variables and member functions*/
class Player {
private:
	std::string name;
	int score;
	std::string token;

public:
	Player(std::string name, int score, std::string token);
	int update();
	int getScore();
	std::string getName();
	std::string getToken();

};

/**
 * Defines Player
 * @param name the name of the player
 * @param score the player's score
 * @param token either x or o depending on the player
*/

Player::Player(std::string name, int score, std::string token) :name(name), score(score), token(token) {}

/**
 * Defines update
 * @return score the updated player score
*/
int Player::update() {
	score++;
	return score;
}

/**
 * Defines getScore
 * @return score of player
*/
int Player::getScore() {
	return score;
}

/**
 * Defines getName
 * @return name stored in player object
*/
std::string Player::getName() {
	return name;
}

/**
 * Defines getToken
 * @return token stored in player object
*/
std::string Player::getToken() {
	return token;
}

/*Initialize an empty 2D vector for the board*/
std::vector<std::vector<std::string>> board{

		{" ", "1", "2", "3"},
		{"1", " ", " ", " "},
		{"2", " ", " ", " "},
		{"3", " ", " ", " "}
};

/**
 * Defines printBoard
 * Prints the 2D vector as a 4x4 square with
 * bars between the values for better readability
*/
void printBoard() {
	for (int i = 0; i < board.size(); ++i){
		for (int j = 0; j < board[i].size(); ++j){
			std::cout << board[i][j] << "|";
		}
		std::cout << std::endl;
	}
	std::cout << std::endl;
}

/**
 * Defines resetBoard
 * Replaces playable positions with spaces
*/
void resetBoard() {
	for (int i = 1; i < board.size(); ++i) {
		for (int j = 1; j < board[i].size(); ++j) {
			board[i][j] = " ";
		}
	}
}

/**
 * Defines isPlayable
 * @param row index being checked
 * @param col index being checked
 * @return false if the space already contains a token
*/
bool isPlayable(int row, int col) {
	if (board[row][col] == "o" || board[row][col] == "x") {
		return false;
	}

	return true;
}

/**
 * Defines placeToken
 * Places the given player's token at the given
 * row and column position. If a space is already 
 * taken, the user is prompted to enter a new 
 * position.
 * @param row index in board
 * @param col index in board
 * @param player 
*/
void placeToken(int row, int col, Player player) {
	if (isPlayable(row, col) == true) {
		board[row][col] = player.getToken();
	}
	else {
		std::cout << std::endl << "Invalid move. Play a different move. Enter your row and column position as: row col ";
		int positionx;
		int positiony;
		std::cin >> positionx >> positiony;
		placeToken(positionx, positiony, player);
	}
}

/**
 * Defines checkWin
 * Checks for 3 in a row horizonally, vertically,
 * and diagonally by looping through the board.
 * This is called at the end of each turn. If a win
 * is found, the string value of the winning token
 * (either x or o) is returned.
 * 
 * @return string value of the winning move
*/
std::string checkWin() {
	
	for (int i = 1; i < board.size(); ++i) {
		for (int j = 1; j < board[i].size(); ++j) {
			
			//Check horizontals
			if (j == 1) {
				if (board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2]) {
					return board[i][j];
				}
			}

			//Check verticals
			if (i == 1) {
				if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j]) {
					return board[i][j];
				}
			}
			
			if (i == 1 && j == 1) {
				//Check diagonal top left to bottom right
				if (board[i][j] == board[i + 1][j + 1] && board[i + 1][j + 1] == board[i + 2][j + 2]) {
					return board[i][j];
				}
				//Check diagonal bottom left to top right
				if (board[i + 2][j] == board[i + 1][j + 1] && board[i + 1][j + 1] == board[i][j + 2]) {
					return board[i + 2][j];
				}
			}
		}
	}
	return "No wins";
}

/**
 * Contains the game setup. An outer loop controlls how many
 * games are played, which is user defined, and an inner loop
 * controls the flow of individual games.
 * 
 * @return 0 if properly run
*/
int main() {
	/*Establishes the initial setup and creation
	of two player objects.
	*/
	std::cout << "This is a Tic Tac Toe game!\n"
		<< "Please enter the name of player 1: ";
	std::string player1;
	std::cin >> player1;
	int score1 = 0;
	std::string token1 = "x";
	Player player_1 = Player(player1, score1, token1);
	std::cout << "Thanks. Please enter the name of player 2: ";
	std::string player2;
	std::cin >> player2;
	int score2 = 0;
	std::string token2 = "o";
	Player player_2 = Player(player2, score2, token2);
	std::cout << "How many rounds would you like to play? ";
	size_t rounds;
	std::cin >> rounds;
	std::cout << "Let the game begin!\n";
	
	/*The outer loop controlling the entire series*/
	for (rounds; rounds > 0; --rounds) {

		//Resets the board
		resetBoard();

		//Prints the initial game board
		printBoard();

		//Displays the current round
		std::cout << rounds << " games remaining" << std::endl;

		/*The inner loop controlling each round*/
		for (size_t turn = 0; turn < 9; ++turn) {


			if (turn % 2 == 0) { //Player1 goes first
				std::cout << std::endl << "It is " << player_1.getName() << "'s turn.\n"
					<< "Where would you like to play? Enter your row and column position as: row col ";
				int positionx; 
				int positiony;
				std::cin >> positionx >> positiony;

				/*An 'x' is placed using the placeToken function, which
				simply replaces whatever is at a position with a player's
				token*/
				placeToken(positionx, positiony, player_1);
			}

			else { //turn % 2 == 1, so Player2's turn
				std::cout << std::endl << "It is " << player_2.getName() << "'s turn.\n"
					<< "Where would you like to play? Enter your row position and column: row col: ";
				int positionx;
				int positiony;
				std::cin >> positionx >> positiony;

				/*Same as for player1, but an 'o' is placed at the position*/
				placeToken(positionx, positiony, player_2);

			}

			//Displays the updated board information with replacements
			printBoard();

			/*Checks for a win, returns the winning token, outputs the winner of the round
			based on the token, and sets turn to 9 to terminate the inner loop for this game
			and start another game if there are more rounds*/
			if (checkWin() == "x") {
				std::cout << player_1.getName() << " has won the round!\n" << "Presently, "
					<< player_1.getName() << " has " << player_1.update() << " points and "
					<< player_2.getName() << " has " << player_2.getScore() << " points.\n";
				turn = 9;
			}

			if (checkWin() == "o") {
				std::cout << player_2.getName() << " has won the round!\n" << "Presently, "
					<< player_1.getName() << " has " << player_1.getScore() << " points and "
					<< player_2.getName() << " has " << player_2.update() << " points.\n";
				turn = 9;
			}

			if (turn == 8 && checkWin() == "No wins") {
				std::cout << "This game is a draw! " << "Presently, "
					<< player_1.getName() << " has " << player_1.getScore() << " points and "
					<< player_2.getName() << " has " << player_2.getScore() << " points.\n";
				turn = 9;
			}
		}


	}
		/*Once the outer loop terminates (rounds are exhausted), the final results are
		presented. The winning player or a draw is displayed.*/
		if (player_1.getScore() > player_2.getScore()) {
			std::cout << player_1.getName() << " won the series.\n";
		}
		else if (player_1.getScore() < player_2.getScore()) {
			std::cout << player_2.getName() << " won the series.\n";
		}
		else if (player_1.getScore() == player_2.getScore()) {
			std::cout << "This match is a draw.\n";
		}
		
	
		return 0;
}

