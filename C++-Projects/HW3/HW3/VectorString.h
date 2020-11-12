/* Author: David Boudreau
 *
 * This file is the header file declaring the member variables, constructors and member
 * functions for the VectorString class.
 *
 * This code contains no known bugs.
*/

#ifndef _VECTOR_STRING_
#define _VECTOR_STRING_

#include<string>
#include<memory>

//the class was written in the pic10b namespace established here
namespace pic10b{
	//this lays out the member variables, constructors, and member functions for the class
	class VectorString {
	
	private:
	std::size_t vec_size;//the number of elements in the vector
	std::size_t vec_capacity;//how many elements the vector can hold with current memory
	std::unique_ptr<std::string[]> store;//smart pointer variable to store an array of strings

	public:
		VectorString();//default constructor
		VectorString(size_t a);//constructor given a desired size
		VectorString(size_t b, std::string a);//constructor given a desired size and string
		VectorString(const VectorString& str);//copy constructor
		VectorString(VectorString&& str);//move constructor
		std::size_t size();//returns size
		std::size_t capacity();//returns capacity
		void push_back(std::string a);//adds a string element to end of vector
		void pop_back();//removes last element
		void deleteAt(size_t i);//deletes element at given index
		void insertAt(size_t j, std::string a);//inserts element at given index
		std::string& at(size_t k);//returns element at given index by reference
		void print(); //prints the vector
	};
}

#endif
