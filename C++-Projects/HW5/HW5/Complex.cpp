/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 5
 * Originally written: 2/19/2019
 * Edited documentation: 11/29/2020
 *
 * This cpp file defines all non constexpr functions from the header file.
*/

#include "Complex.h"
#include <iostream>
#include<stdexcept>

/*MEMBER FUNCTIONS NOT CONSTEXPR*/

/**
 * Defines return by value subscript operator
 * Throws exception if bad input
 * 
 * @param input string either "real" or "imag"
 * @return real or imaginary part of Complex 
 * depending on the input
*/
double Complex::operator[](std::string input) const{
	if (input == "real") {
		return real;
	}
	else if (input == "imag") {
		return imaginary;
	}
	else {
		std::string error_message = "Invalid index: " + input;
		throw std::out_of_range(error_message);
	}
}

/**Define operator[] subscript operator, return reference
@param std::string input
@return real part of Complex if input is "real"
@return imaginary part of Complex if input is "imag"
@throw std::out_of_range with invalid input if something else
*/

/**
 * Defines return by reference subscript operator
 * Throws exception if bad input
 * 
 * @param input string either "real" or "imag"
 * @return real or imaginary part of Complex 
 * depending on the input
*/
double& Complex::operator[](std::string input) {
	if (input == "real") {
		return real;
	}
	else if (input == "imag") {
		return imaginary;
	}
	else {
		std::string error_message = "Invalid index: " + input;
		throw std::out_of_range(error_message);
	}
}

/**
* Defines operator std::string() which converts real and imaginary
* parts of Complex object into a string showing the full complex number
* 
* @return the fully converted complex number
*/
Complex::operator std::string() const{
	std::string converted;		 //empty string to be filled later
	std::string error = "Error"; //string with error message
	std::ostringstream change;	 //output string stream
	
	if (imaginary > 0) {							//if imaginary is positive
		change << real << "+" << imaginary << "i";
		converted = change.str();					//string converted is given contents of the output
		return converted;
	}
	else if(imaginary < 0){							//if imaginary is negative
		change << real << imaginary << "i";			//no '-' needed because the double already has it
		converted = change.str();					//string converted given contents of the ouput
		return converted;
	}
	else if (imaginary == 0) {						//if imaginary is 0			
		change << real;								//output only the real part
		converted = change.str();					//converted given contents of output
		return converted;
	}
	else {
		return error;								//output error message if something somehow goes wrong
	}

}

/*NON MEMBER FUNCTIONS*/


/**
* Defines operator<< which displays the complex number depending on
* conditions
* @param out the output stream
* @param Complex c object to be printed
* @return out in all cases depending on the sign of the imaginary part
*/
std::ostream& operator<<(std::ostream& out, Complex c){
	
	if (c["imag"] > 0) {								//if imaginary part positive
		out << c["real"] << "+" << c["imag"] << "i";
		return out;
	}
	else if (c["imag"] < 0) {							//if imaginary is negative
		out << c["real"] << c["imag"] << "i";
		return out;
	}
	else if(c["imag"]==0){								//if imaginary is 0
		out << c["real"];
		return out;
	}
	else {												//if something else, output "Error"
		out << "Error";
	}
}

/**
* Defines friend operator>> which allows two doubles to be input by
* the user, which will be assigned to the real and imaginary parts 
* of a Complex object
* 
* @param in the input stream
* @param reference to Complex c
* @return in if no error is thrown
*/
std::istream& operator>>(std::istream& in, Complex& c){
	
	in >> c.real >> c.imaginary;

	if (in.fail()) {
		throw std::runtime_error("read error");
	}

	return in;
}

/*		
*		All bools depend on conversion operator
*		Can't be constexpr because they depend on 
*		non-constexpr conversion operator
*
*/

/**
* Defines operator< for less than
* Calls on the conversion operator to determine true/false
* 
* @param reference to const Complex left
* @param reference to const Complex right
* @return true if left less than right
*/
bool operator<(const Complex& left, const Complex& right){
	return left.operator std::string() < right.operator std::string();
}

/**
* Defines operator> for greater than
* Calls on the conversion operator to determine true/false
*
* @param reference to const Complex left
* @param reference to const Complex right
* @return true if left greater than right
*/
bool operator>(const Complex& left, const Complex& right){
	return left.operator std::string() > right.operator std::string();
}

/**
* Defines operator== for equivalence
* Calls on the conversion operator to determine true/false
*
* @param reference to const Complex left
* @param reference to const Complex right
* @return true if left equal to right
*/
bool operator==(const Complex& left, const Complex& right){
	return !((left < right) || (right < left));
}

/**
* Defines operator<= for less than or equal
* Calls on the conversion operator to determine true/false
*
* @param reference to const Complex left
* @param reference to const Complex right
* @return true if left less than or equal to right
*/
bool operator<=(const Complex& left, const Complex& right){
	return left.operator std::string() <= right.operator std::string();
}

/**
* Defines operator>= for greater than or equal 
* Calls on the conversion operator to determine true/false
*
* @param reference to const Complex left
* @param reference to const Complex right
* @return true if left greater than or equal to right
*/
bool operator>=(const Complex& left, const Complex& right){
	return left.operator std::string() >= right.operator std::string();
}

/**
* Defines operator!= for not equal
* Calls on the conversion operator to determine true/false
*
* @param reference to const Complex left
* @param reference to const Complex right
* @return true if left not equal to right
*/
bool operator!=(const Complex& left, const Complex& right){
	return left.operator std::string() != right.operator std::string();
}




