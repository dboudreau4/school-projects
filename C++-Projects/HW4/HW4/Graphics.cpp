/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 4
 * Originally written: 2/6/2019
 * Edited and improved: 11/29/2020
 *
 * This file contains definitions for all classes.
 *
*/

#include "Shape.h"
#include "Square.h"
#include "Collection.h"
#include "Diamond.h"
#include<iostream>

/*SHAPE DEFINITIONS*/

/**
 * Defines the Shape class constructor
 * 
 * @param x_coord 
 * @param y_coord 
 * @param shape_size 
*/
Shape::Shape(int x_coord, int y_coord, std::size_t shape_size) 
	: x_coord(x_coord), y_coord(y_coord), shape_size(shape_size) {}

/**
 * Defines add_point which adds a new 
 * point from the input into the points
 * member vector of pairs
 * 
 * @param a the x coordinate of the point
 * @param b the y coordinate
*/
void Shape::add_point(int a, int b) {
	points.push_back(std::make_pair(a, b));
}

/**
 * Defines clear_all which clears the 
 * points contained in the vector of pairs
*/
void Shape::clear_all() {
	points.clear();
}

/**
 * Defines get_x
 * @return the x coordinate of the point/shape
*/
int Shape::get_x() const {
	return x_coord;
}

/**
 * Defines get_y
 * @return the y coordinate of the point/shape
*/
int Shape::get_y() const {
	return y_coord;
}

/**
 * Defines get_size
 * @return the size of the shape
*/
size_t Shape::get_size() const{
	return shape_size;
}

/**
 * Defines setStars
 * Virtual function overriden by Sqaure and Diamond classes
*/
void Shape::setStars() {} //defined in subclasses

/**
 * Defines moveBy
 * Shifts the x and y coordinates by the input
 * Calls setStars once the shape has been moved
 * 
 * @param a amount to move the x coordinate
 * @param b amount to move the y coordinate
*/
void Shape::moveBy(int a, int b) {
	x_coord += a;
	y_coord += b;
	setStars();
}

/**
 * Defines setSize which resets the size of a shape
 * to the input size and calls setStars at the end
 * 
 * @param new_size 
*/
void Shape::setSize(std::size_t new_size) { 
	shape_size = new_size;
	setStars();
}

/**
 * Defines get_points
 * @return the vector of pairs member
*/
std::vector<std::pair<int, int>> Shape::get_points() const {
	return points;
}

/*SQUARE DEFINITIONS*/

/**
 * Defines the Square class constructor
 * Inherits from the Shape class
 * Calls setStars to set the coordinates of the square
 * 
 * @param x_coord 
 * @param y_coord 
 * @param shape_size side length
*/
Square::Square(int x_coord, int y_coord, std::size_t shape_size) 
	: Shape(x_coord, y_coord, shape_size) {
	setStars();
}

/**
 * Defines setStars which clears the
 * existing points and adds the new point
 * 
 * The x and y coordinate members represent
 * the top left of a square object. All other
 * points are placed relative to that starting
 * point.
 * 
*/
void Square::setStars() {					
	clear_all();

	for (int row = 0; row < get_size(); ++row) {
		for (int col = 0; col < get_size(); ++col) {
			add_point((get_x() + row), (get_y() + col));
		}
	}
}

/*DIAMOND DEFINITIONS*/

/**
 * Defines the Diamond class constructor
 * Inherits from the Shape class
 * Calls setStars to set the coordinates of the diamond
 * 
 * @param x_coord 
 * @param y_coord 
 * @param shape_size half the length between	
 * far left and right points or top and bottom
*/
Diamond::Diamond(int x_coord, int y_coord, std::size_t shape_size) 
	: Shape(x_coord, y_coord, shape_size) {
	setStars();
}

/**
 * Defines setStars which clears the
 * existing points and adds the new point
 * 
 * The x and y coordinate members represent
 * the top point of a diamond object. All other
 * points are placed relative to that starting
 * point.
 *
*/
void Diamond::setStars() {					
	clear_all();

	int x_vertex = get_x();
	int y_vertex = get_y();

	//Places top half points
	for (int row_incr = 0; row_incr <= get_size(); ++row_incr) {
		for (int col_incr = 0; col_incr <= 2 * row_incr; ++col_incr) {
			add_point(x_vertex + row_incr, y_vertex + row_incr - col_incr);
		}
		
	}
	
	//Places bottom half points
	int x_bottom_vertex = 2 * get_size() + get_x();
	for (int row_incr = 0; row_incr <= get_size(); ++row_incr) {
		for (int col_incr = 0; col_incr <= 2 * row_incr; ++col_incr) {
			add_point(x_bottom_vertex - row_incr, y_vertex + row_incr - col_incr);
		}
	}
}

/*COLLECTION DEFINTIONS*/

/**
 * Defines the Collection class constructor
 * given the x and y coordinate bounds for
 * displaying the shapes
 * 
 * @param x_low 
 * @param x_high 
 * @param y_low 
 * @param y_high 
*/
Collection::Collection(int x_low, int x_high, int y_low, int y_high) 
	: x_low(x_low), x_high(x_high), y_low(y_low), y_high(y_high), 
	screen(std::size_t(x_high) - std::size_t(x_low) + 1, std::vector<char>(std::size_t(y_high) - std::size_t(y_low) + 1, '0')) {}

/**
 * Defines addShape which takes a shared pointer of Shape
 * and adds it to the collection of shapes
 * 
 * @param a the shared pointer
*/
void Collection::addShape(std::shared_ptr<Shape> a) {
	shapes.push_back(a);
}

/**
 * Defines display which prints the shapes in the
 * collection to the console. Empty coordinates are
 * shown as zeros and coordinates where shapes are
 * located are shown as asterisks.
*/
void Collection::display() {
	
	//Add shapes to the screen vector - only points within screen bounds are added
	for (auto& s : shapes) {
		for (std::pair<int, int> a : s->get_points()) {
			if ((a.first >= x_low && a.second >= y_low) && (a.first <= x_high && a.second <= y_high)) {
				screen[std::size_t(a.first) - std::size_t(x_low)][std::size_t(a.second) - std::size_t(y_low)] = '*';
			}
		}
	}

	//Display the screen vector
	for (std::size_t i = 0; i < screen.size(); ++i) {
		for (std::size_t j = 0; j < screen[i].size(); ++j) {
			std::cout << screen[i][j];
		}
		std::cout << std::endl;
	}

	std::cout << std::endl;
}

/**
 * Defines totalStars
 * @return the number of stars or
 * coordinates filled by the shapes
*/
int Collection::totalStars() {
	
	int starCount = 0;
	for (std::size_t i = 0; i < screen.size(); ++i) {
		for (std::size_t j = 0; j < screen[i].size(); ++j) {
			if (screen[i][j] == '*') {
				starCount += 1;
			}
		}
	}
	return starCount;
}

/**
 * Defines moveBy which moves all shapes in 
 * the collection by the x and y inputs
 * 
 * @param x_move amount to move horizontally
 * @param y_move amount to move vertically
*/
void Collection::moveBy(int x_move, int y_move) {
	clearScreen();
	for (auto& s : shapes) {
		s->moveBy(x_move, y_move);
	}
}

/**
 * Defines setSize which resizes all the shapes
 * in the collection to the input size
 * 
 * @param new_all_size 
*/
void Collection::setSize(size_t new_all_size) {
	clearScreen();
	for (auto& s : shapes) {
		s->setSize(new_all_size);
	}
}

/**
 * Defines clearScreen which resets the screen member
 * vector to hold 0's again. This is called anytime 
 * something happens to existing shapes in the screen 
 * like resizing or moving them.
*/
void Collection::clearScreen() {
	for (std::size_t i = 0; i < screen.size(); ++i) {
		for (std::size_t j = 0; j < screen[i].size(); ++j) {
			screen[i][j] = '0';
		}
	}
}





