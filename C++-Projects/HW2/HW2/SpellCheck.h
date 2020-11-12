/* Author: David Boudreau
 * 
 * This header file defines the SpellCheck class and declares its functions and variables.
 * 
*/


#ifndef _SPELL_CHECK_
#define _SPELL_CHECK 

#include<string>
#include<fstream>
#include<iostream>
#include<unordered_set>

class SpellCheck{
private:
	std::unordered_set<std::string> dictionary;

public:
	SpellCheck();
	std::string inputDictionaryFile;
	void read_dictionary(std::string inputDictionaryFile);
	std::string word;
	bool is_valid(std::string word);
	std::string checkFile;
	void process_file(std::string checkFile);
};

#endif 

