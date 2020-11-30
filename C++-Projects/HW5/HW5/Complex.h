/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 5
 * Originally written: 2/19/2019
 * Edited documentation: 11/29/2020
 * 
 * This program allows the user to create and mathematically manipulate complex
 * numbers through the use of several different operators. Constexpr functions 
 * were used when possible to allow them to be run at compile time. All constexpr
 * functions (member and non-member) were declared and defined in this header file,
 * following inline procedure.
 *
*/

#ifndef _COMPLEX_
#define _COMPLEX_

#include <string>
#include <sstream>

/*Class interface for the Complex class*/

class Complex {
private:
	double real; //real part of a complex number
	double imaginary; //imaginary part of a complex number

public:
	
	//default constructor setting real and imaginary parts to 0
	constexpr Complex(double real = 0, double imaginary = 0); 
	
	//binary += function, adding two complex numbers and changing the left one
	constexpr Complex& operator+=(const Complex& num); 
	
	//unary function returning a copy of a complex number
	constexpr Complex operator+() const;

	//unary prefix ++ incrementing 
	constexpr Complex& operator++(); 

	//binary postfix ++ incrementing
	constexpr Complex operator++(int unused); 

	//binary -= substracting two complex numbers and changing the left one
	constexpr Complex& operator-=(const Complex& num); 

	//unary, setting real and imaginary parts -
	constexpr Complex operator-() const; 

	//unary prefix -- decrementing
	constexpr Complex operator--();

	//binary postfix -- decrementing
	constexpr Complex operator--(int unused); 

	//binary *= multiplying complex numbers
	constexpr Complex& operator*=(Complex a); 

	//binary /= dividing complex numbers
	constexpr Complex& operator/=(Complex a); 

	//unary returning conjugate of complex number (change sign of imaginary part)
	constexpr Complex operator~() const; 

	//call operator
	constexpr Complex& operator()();

	//subscript operator, returning a value
	double operator[](std::string input) const; 
	
	//subscript operator, return a reference
	double& operator[](std::string input); 

	//converts real and imaginary parts into a fully written complex number as string
	operator std::string() const; 

	//reads in double values from user input
	friend std::istream& operator>>(std::istream& in, Complex& c); 
};

/*
 *
 *
 *
 *	All constexpr functions/constructor defined
 *	inside header file declared to maintain inline
 *	rules
 *
 *
 *
 *
*/

/**
 * Defines Complex constructor
 * Declaration in interface default sets values to 0
 * Definition sets them to private variables otherwise
 * 
 * @param real the real part of the number
 * @param imaginary the imaginary part
 * 
*/
constexpr Complex::Complex(double real, double imaginary) :
	real(real), imaginary(imaginary) {}

/*MEMBER FUNCTIONS MARKED CONSTEXPR*/

/**
 * Defines operator+= which adds to complex numbers, 
 * changing the left one
 * 
 * @param num reference to const Complex num
 * @return updated left Complex object
*/
constexpr Complex& Complex::operator+=(const Complex& num) {
	real += num.real;			//num accesses its real part through private variable, then adds to real
	imaginary += num.imaginary; //num accesses its imaginary part through private variable, then adds to imaginary
	return *this;
}

/**
 * Defines operator+
 * 
 * @return copy of Complex object
*/
constexpr Complex Complex::operator+() const {
	return *this;
}

/**
 * Defines operator++ prefix unary incrementor
 * Increments only the real part
 * 
 * @return updated incremented object
*/
constexpr Complex& Complex::operator++() {
	++real;		  //adds 1 to the real part
	return *this;
}

/**
* Defines operator++ postfix binary incrementor
* Creates a new Complex object and increments the real part
* 
* @param int unused
* @return Complex object created
*/
constexpr Complex Complex::operator++(int unused) {
	Complex copy(*this); //new Complex object copies the contents from the object
	++real;				 //adds 1 to the real part
	return copy;
}

/**
* Defines operator-= which subtracts two 
* complex numbers, changing the left
* 
* @param reference to const Complex num
* @return updated left Complex object
*/
constexpr Complex& Complex::operator-=(const Complex& num) {
	real -= num.real;			  //num accesses its real part through private variable, then subtracts from real
	imaginary -= num.imaginary;   //num accesses its imaginary part through private variable, then subtracts from imaginary
	return *this;
}

/**
* Defines operator- which negates the real and imaginary parts
* 
* @return Complex with real and imaginary negated
*/
constexpr Complex Complex::operator-() const {
	return Complex(-real, -imaginary);
}

/**
* Defines operator-- prefix unary decrementor
* Decrements only the real part
* 
* @return updated decremented object
*/
constexpr Complex Complex::operator--() {
	--real;			//1 is subtracted from real
	return *this;
}

/**
* Defines operator-- postfix binary decrementor
* Creates a new Complex object and decrements the real part
* 
* @param int unused
* @return Complex object created
*/
constexpr Complex Complex::operator--(int unused) {
	Complex copy(*this); //new Complex object copy copies the contents from the object
	--real;				 //real part is decremented
	return copy;
}

/**
* Defines operator*= which multiplies two complex numbers, 
* changing the left
* 
* @param Complex a object
* @return updated left Complex object
*/
constexpr Complex& Complex::operator*=(Complex a) {
	double real_product = (real*a.real - imaginary * a.imaginary);		//use mathematical principles to determine real result
	double imaginary_product = (imaginary*a.real + real * a.imaginary); //principles for imaginary result

	real = real_product;			//assign real result to variable real
	imaginary = imaginary_product;	//assign imaginary result to variable imaginary
	return *this;
}

/**
* Defines operator/= which divides two complex numbers, 
* changing the left
* 
* @param Complex a object
* @return updated left Complex object
*/
constexpr Complex& Complex::operator/=(Complex a) {
	double real_quotient = (real*a.real + imaginary * a.imaginary) / (a.real*a.real + a.imaginary*a.imaginary);		 //principles for real result
	double imaginary_quotient = (imaginary*a.real - real * a.imaginary) / (a.real*a.real + a.imaginary*a.imaginary); //principles for imaginary result

	real = real_quotient;			//assign real result to variable real
	imaginary = imaginary_quotient; //assign imaginary result to variable imaginary
	return *this;
}

/**
* Defines operator~ which conjugates a complex number
* 
* @return Complex object with negated imaginary part
*/
constexpr Complex Complex::operator~() const {
	return Complex(real, -imaginary);
}

/**
* Defines operator() taking no arguments and setting real and 
* imaginary parts of Complex object to 0 - the call operator
* 
* @return updated Complex object
*/
constexpr Complex& Complex::operator()() {
	real = 0;
	imaginary = 0;

	return *this;
}

/**
* Defines operator+ which returns the sum of 
* two complex numbers. It calls the operator+=
* defined above.
* 
* @param Complex left
* @param reference to const Complex right
* @return sum 
*/
constexpr Complex operator+(Complex left, const Complex& right) {
	return left += right;
}

/**
* Defines operator- which returns the difference 
* of two complex numbers. Calls the operator -=.
* 
* @param Complex left
* @param reference to const Complex right
* @return difference
*/
constexpr Complex operator-(Complex left, const Complex& right) {
	return left -= right;
}

/**
* Defines operator* which returns the product 
* of two complex numbers. Calls operator *=.
* 
* @param Complex left
* @param Complex right
* @return product
*/
constexpr Complex operator*(Complex left, Complex right) {
	return left *= right;
}

/**
* Defines operator/ which returns the quotient
* of two complex numbers. Calls operator /=.
*
* @param Complex left
* @param Complex right
* @return quotient
*/
constexpr Complex operator/(Complex left, Complex right) {
	return left /= right;
}

//Display with conditions, defined in cpp file
std::ostream& operator<<(std::ostream& out, Complex c); 

/*BOOLS defined in cpp file*/

//less than
bool operator<(const Complex& left, const Complex& right);

//greater than
bool operator>(const Complex& left, const Complex& right);

//equal 
bool operator==(const Complex& left, const Complex& right);

//less equal
bool operator<=(const Complex& left, const Complex& right);

//greater equal
bool operator>=(const Complex& left, const Complex& right);

//not equal
bool operator!=(const Complex& left, const Complex& right);


/**
* Defines operator""_i user defined literal
* @param long double imag
* @return Complex with new imaginary part from input
*/
constexpr Complex operator""_i(long double imag) {
		return Complex(0, imag);
}

#endif 
