/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 5
 * Originally written: 2/19/2019
 * Edited documentation: 11/29/2020
 *
 * This file contains all the testing for the Complex class.
*/

#include "Complex.h"
#include<vector>
#include<algorithm>
#include<iostream>

int main() {
	//Some warmups
	Complex u{ 2,3 }, v{ 1,1 };
	std::cout << "simple stuff: " << u + v << '\n' << u - v << '\n' << u * v << '\n' << u / v << '\n';

	//Constexptr stuff
	constexpr Complex z0 = 1.77_i;
	constexpr Complex z0_conj = ~z0;
	constexpr Complex z0_copy = +z0;
	constexpr Complex z0_negated = -z0;

	//Display this constexpr stuff
	std::cout << "z0, z0 conj, z0 copy, z0 neg: " << z0 << " " << z0_conj << " " << z0_copy << " " << z0_negated << '\n';

	//Create Complex numbers through constructors
	constexpr Complex z1, z2{ 3, 4 };
	constexpr Complex z3 = (Complex{ 1 } += (4 - 3._i));
	Complex z4 = z3;
	z4 *= 2; //multiply by 2 (which will be converted to Complex(2,0)

	//Create with user-defined literal
	Complex z5 = 3.14 + 14.3_i;

	std::cout << "Numbers z1, z2, z3, z4, z5: " << z1 << " " << z2 << " " << z3 << " " << z4 << " " << z5 << '\n';

	//Turn z5 to its conjugate
	z5 = ~z5;
	std::cout << "z5 after conjugation: " << z5 << '\n';

	std::cout << "z5*z3: " << z5 * z3 << '\n';

	//Do some arithmetic to them and check values
	z4 += z3;	z4 /= z5;
	Complex z6 = z1 + z2 + z3 - z4;
	Complex z7 = z6;
	z7();

	std::cout << "z4, z5, z6, z7: " << z4 << " " << z5 << " " << z6 << " " << z7 << '\n';
	
	//Read in with std::cin
	std::cout << "Enter two doubles to set real and imaginary parts: ";
	std::cin >> z4;
	std::cout << "-z4 and +z5: " << -z4 << " " << +z5 << '\n';
	
	//Access real and imaginary parts
	z4["real"] = 0.14;
	std::cout << "real(z4) and imag(z4): " << z4["real"] << " " << z4["imag"] << '\n';
	
	//Increment and decrement operators
	++++z4, z5------;
	std::cout << "z4 with two pre++ and z5 with 3 post--: " << z4 << " " << z5 << '\n';

	//Store items in a vector, sort them
	std::vector<Complex> vec{ z1, z2, z3, z4, z5, z6, z7 }; // vector of complex
	std::vector<std::string> vec2{ z1, z2, z3, z4, z5, z6, z7 }; // vector of strings because of conversion operator

	std::cout << "The elements: ";
	for (const std::string& complexString : vec2) { //print each as a string
		std::cout << complexString << " ";
	}

	std::sort(std::begin(vec), std::end(vec)); //sort the Complex numbers
	std::cout << "\nsorted vector: ";

	for (const Complex& number : vec) { //print the sorted list
		std::cout << number << " ";
	}
	std::cout << '\n';

	try { //try accessing invalid index
		z2["reel"];
	}
	catch (const std::out_of_range& ORR) { //upon failure, print the error and continue to run the program
		std::cerr << ORR.what() << '\n';
	}

	//access real part of a constant number
	const Complex z8 = z7;
	std::cout << "z8 imag: " << z8["imag"];

	std::cin.get();
	std::cin.get();

	
	return 0;
}