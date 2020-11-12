/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 3
 * Originally written: 1/18/2019
 * Edited and improved: 11/11/2020
 *
 * This file contains the VectorString class, its constructors and member functions. The goal
 * was to make it function like a vector of strings including dynamic memory allocation, which
 * was achieved by using a unique pointer member variable to store an array of strings.
 *
 * This code contains no known bugs.
*/

#include "VectorString.h"
#include <iostream>

namespace pic10b {
	
	/*Defines default constructor*/
	VectorString::VectorString() :vec_size(0), vec_capacity(1), store(new std::string[vec_capacity]) {}

	/*Defines constructor given a specific size*/
	VectorString::VectorString(size_t a) : vec_size(0), vec_capacity(a), store(new std::string[vec_capacity]) {}

	/*Defines constructor given a capacity and string*/
	VectorString::VectorString(size_t b, std::string a) : vec_size(1), vec_capacity(b), store(new std::string[vec_capacity]) {
		store[0] = a;
	}

	/*Defines copy constructor*/
	VectorString::VectorString(const VectorString& str) : vec_size(str.vec_size), vec_capacity(str.vec_capacity), store(nullptr) {
		store.reset(new std::string[str.vec_size]);
		for (std::size_t i = 0; i < str.vec_size; ++i) {
			store[i] = str.store[i];
		}
	}

	/*Defines move constructor*/
	VectorString::VectorString(VectorString&& str): VectorString() {
		std::swap(store, str.store);
		std::swap(vec_size, str.vec_size);
	}

	/**
	 * Defines size
	 * @return number of elements
	*/
	std::size_t VectorString::size() {
		return vec_size;
	}

	/**
	 * Defines capacity
	 * @return number of elements that can be stored, which
	 * is usually twice the number of elements currently stored
	*/
	std::size_t VectorString::capacity() {
		return vec_capacity;
	}

	/**
	 * Defines push_back which adds an element to the end
	 * If more memory needs to be allocated, the unique 
	 * pointer is resized to twice the original capacity
	 * 
	 * @param a the string being added
	*/
	void VectorString::push_back(std::string a) {

		if (vec_size == vec_capacity) {
			std::unique_ptr<std::string[]> temp_ptr(new std::string[vec_capacity * 2]);
			for (std::size_t i = 0; i < vec_capacity; ++i) {
				temp_ptr[i] = store[i];
			}
			store.reset();
			store = std::move(temp_ptr);
			vec_capacity *= 2;
		}

		store[vec_size] = a;
		vec_size++;
	}

	/**
	 * Defines pop_back which removes the last element
	*/
	void VectorString::pop_back() {
		vec_size--;
	}

	/**
	 * Defines deleteAt which removes an element
	 * at a specified index and rearranges the 
	 * remaining elements to get rid of gaps
	 * 
	 * @param i index of the element being removed
	*/
	void VectorString::deleteAt(size_t i) {
	
		if (i + 1 == vec_size) {
			pop_back();
		}
		else {
			for (size_t k = i + 1; k < vec_size; ++k) {
				store[k - 1] = store[k];
			}
			vec_size--;
		}
	}

	/**
	 * Defines insertAt which inserts a string at a
	 * specifed index. If more memory needs to be allocated,
	 * the push_back function is called. If not, the
	 * elements from the intended index to the end are moved
	 * over and the intended element is inserted.
	 * 
	 * @param j index inserted at
	 * @param a string being inserted
	*/
	void VectorString::insertAt(size_t j, std::string a) {

		if (j == vec_capacity) {
			push_back(a);
		}
		else {
			for (size_t i = vec_size; i > j; --i) {
				store[i] = store[i - 1];
			}
			store[j] = a;
			vec_size++;
		}
	}

	/**
	 * Defines at which returns an element at a 
	 * given index by reference
	 * @param k index element being retrieved from
	 * @return element being retrieved
	*/
	std::string& VectorString::at(size_t k) {
		return store[k];
	}

	/**
	 * Defines print which simply prints the 
	 * contents of the string array 
	*/
	void VectorString::print() {
		for (std::size_t i = 0; i < vec_size; ++i) {
			std::cout << at(i) << ",";
		}
		std::cout << '\n' << '\n';
	}

}


