/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 3
 * Originally written: 1/18/2019
 * Edited and improved: 11/11/2020
 *
 * This file is the main routine to test the functionality of the VectorString class. 
 * 
 * This code contains no known bugs.
*/

#include"VectorString.h"
#include<iostream>
#include<string>

pic10b::VectorString foo() {
	return pic10b::VectorString(2);
}

int main() {

	/*Testing for default constructor*/
	pic10b::VectorString vs;
	std::cout << "vs stats: " << vs.size() << " " << vs.capacity() << '\n';
	vs.push_back("hello");
	std::cout << "vs stores: " << vs.at(0) << '\n';

	/*Testing for copy constructor and constructor with given capacity*/
	pic10b::VectorString vs2(foo());
	std::cout << "vs2 stats: size - " << vs2.size() << " capacity - " << vs2.capacity() << '\n';
	std::cout << "vs2 stores: ";
	vs2.print();
	
	/*Testing constructor with given capacity and string*/
	pic10b::VectorString vs3(4, "beta");
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 stores: ";
	vs3.print();

	/*Testing pop, push_back, insertAt, at, and deleteAt*/
	vs3.pop_back();
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 after pop stores: ";
	vs3.print();

	vs3.push_back("delta");
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 after push stores: ";
	vs3.print();

	vs3.push_back("epsilon");
	vs3.at(1) = "gamma";	//replaces epsilon at index 1
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 after push and insert stores: ";
	vs3.print();
	
	vs3.insertAt(0, "alpha");
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 after alpha at beginning insert stores: ";
	vs3.print();

	vs3.push_back("zeta");
	vs3.push_back("theta");
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 after push of zeta and theta stores: ";
	vs3.print();
	
	vs3.insertAt(vs3.size() - 1, "eta");
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 after eta insertion stores: ";
	vs3.print();
	
	vs3.deleteAt(3);
	std::cout << "vs3 stats: size - " << vs3.size() << " capacity - " << vs3.capacity() << '\n';
	std::cout << "vs3 after index 3 deletion stores: ";
	vs3.print();

	return 0;
}