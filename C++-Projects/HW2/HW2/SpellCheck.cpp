/*Author: David Boudreau
* 
* This file contains both member and non-member function implementations for the SpellCheck class.
*/

#include "SpellCheck.h"

/*Non-member functions*/

/**
 * Defines is_white_space which checks whether 
 * a given char is a white space character
 * 
 * @param a the char being checked
 * @return true if the char is white space (space, tab, newline)
*/
bool is_white_space(char& a) {
	
	if (a == ' ' || a == '\t' || a == '\n') {
		return true;
	}
	else {
		return false;
	}
}

/**
 * Defines final_punctuation which checks if the final
 * character of a string is a punctuation mark
 * 
 * @param a the string being checked 
 * @return true if it is a punctuation mark
*/
bool final_punctuation(std::string& a) {

	char last = a[a.size() - 1];
	if (last == '.' || last == ',' || last == '!' || last == '?' || last == ';' || last == ':') {
		return true;
	}
	else {
		return false;
	}

}

/**
 * Defines depunctuate which converts the first character in a string 
 * to lowercase if it's uppercase and removes final punctuation if present
 * 
 * @param a the string being converted
*/
void depunctuate(std::string& a) {

	char first = a[0];
	
	if (('A' <= first) && (first <= 'Z')){ //if the first character exists within this range
		first += ('a' - 'A'); //the first character is converted to its lower case equivalent
	}
	a[0] = first; //inserts the lower case letter into the string's first index
	if(final_punctuation(a)==true){ //if it ends in punctuation
		a.pop_back(); //the punctuation is removed
	}
	
}


/*SpellCheck class functions*/

/**Default constructs SpellCheck object 
*/

SpellCheck::SpellCheck() {}

/**Defines read_dictionary
@param string name of dictionary file
Reads 
*/

/**
 * Defines read_dictionary which reads in the contents 
 * of the dictionary file and stores the words in the 
 * private class variable unordered set dictionary
 * 
 * @param inputDictionaryFile the name of the dictionary file
*/
void SpellCheck::read_dictionary(std::string inputDictionaryFile) {
	
	std::ifstream inputdict; //establishes a new input file stream
	inputdict.open(inputDictionaryFile); //applies the parameter to the stream
	std::string word; //makes a blank string
	while (getline(inputdict, word)) {
		dictionary.insert(word); //inserts the dictionary words into the unordered set dictionary
	}
	inputdict.close();
}

/**
 * Defines is_valid which calls non-member function depunctuate before 
 * the check begins (the dictionary doesn't account for first letter 
 * capitalization or ending punctuation)
 * 
 * @param word accepted to check if it exists within the dictionary file
 * @return true if the word exists
*/
bool SpellCheck::is_valid(std::string word) {

	depunctuate(word);
	auto it1 = dictionary.find(word); //tells the dictionary unordered set to find the word
	if (it1 != dictionary.end()) { //iterates through the unordered set until the word is found
		return true;
	}
	else {
		return false;
	}

}

/**Defines process_file
@param string name of the file wanting to be spell checked
The file is put into an file stream
*/

/**
 * Defines process_file which puts the file into a file stream
 * and is processed by checking if words are spelled correctly
 * and adding asterisks around mispelled words
 * 
 * @param checkFile the name of the file being spell checked
*/
void SpellCheck::process_file(std::string checkFile) {
	
	std::ifstream article(checkFile); //file name is put into an input file stream
	std::string word;
	
	while (!article.eof()) { //loop will continue until it reaches the end of the file
		
		char ch = article.peek(); //starts at the beginning of the file
		if (is_white_space(ch)==true) { //if there is white space in the file
			std::cout << ch; //the white space is printed to the console
			ch = article.get(); //the iterator advances to the next character
			}
		else { //once a non-white space character is reached
			article >> word; //the file stream will read in the word
			char last = word[word.length() - 1];
			if (is_valid(word) == true) { //if the word exists in the dictionary
				std::cout << word; //the word is printed to the console
			}
			else if(final_punctuation(word)==true){ //if it doesn't exist and there is a final punctuation
				word.pop_back(); //remove the punctuation
				std::cout << "*" << word << "*"<< last; //print the word in *'s with the final punctuation after
			}
			else { //if it doesn't exist
				std::cout << "*" << word << "*"; //print the word in *'s
			}
		}
	}
	article.close(); //close the file stream

}





