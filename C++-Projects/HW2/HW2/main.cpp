/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 2
 * Originally written: 1/18/2019
 * Edited documentation: 10/27/2020
 *
 * This file is the main routine to execute the SpellCheck class. This program stores the words from a dictionary file into 
 * an unordered set, prompts the user to enter the name of a text file he or she would like to spell check, then prints the
 * entered file to the console with *'s around mispelled words according to the provided dictionary file. All the white space
 * contained in the original file is preserved. 
 *
 * This code contains no known bugs.
*/


#include "SpellCheck.h"


int main() {
	
	SpellCheck a; //declares a SpellCheck object
	a.read_dictionary("dictionary.txt"); //reads the contents of the dictionary text file into the unordered set of the SpellCheck object
	std::cout << "Enter a file to spell check: "; //asks for name of text file
	std::string file; //declares the file name in the form of a string
	std::cin >> file; //user inputs the name of file local to source folder that needs to be spell checked
	a.process_file(file); //processes the file inputted and displays the spell checked file to the console

	return 0;
}