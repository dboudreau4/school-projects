/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 4
 * Originally written: 2/6/2019
 * Edited and improved: 11/29/2020
 *
 * This file contains the declarations for the Collection class,
 * which is responsible for storing and displaying shapes as well as 
 * displaying the screen where the shapes are shown in the console.
 * It holds the shapes through a vector of shared pointers of shapes.
 * The screen member is a 2D vector of characters used for displaying
 * the shapes which are shown by having asterisks at the points they
 * take up.
 *
*/

#include <memory>
#include "Shape.h"

#ifndef _COLLECTION_
#define _COLLECTION_

class Collection {
private:
	int x_low;
	int x_high;
	int y_low;
	int y_high;
	std::vector<std::shared_ptr<Shape>> shapes;
	std::vector<std::vector<char>> screen;
	
public:
	Collection(int x_low, int x_high, int y_low, int y_high);
	void addShape(std::shared_ptr<Shape> a);
	void display();
	int totalStars();
	void moveBy(int x_move, int y_move);
	void setSize(std::size_t new_all_size);
	void clearScreen();
};

#endif 
