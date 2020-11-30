/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 4
 * Originally written: 2/6/2019
 * Edited and improved: 11/29/2020
 *
 * This file tests and displays the shapes to the console. The coordinates are displayed
 * so that the upper left is the origin. As the row or x value increases, the numbers 
 * moving down the screen increase. As the column or y value increases, the numbers moving
 * right increase.
 * 
 * Everything works as it should. Some lines are commented out, but they can be tested to see
 * more shapes on display.
 *
*/

#include "Shape.h"
#include "Square.h"
#include "Diamond.h"
#include "Collection.h"
#include <iostream>
#include <memory>

int main() {
	
	//top left at (1,2), size 2x2
	std::shared_ptr<Shape> sp1 = std::make_shared<Square>(1, 2, 2); 
	
	//top point at (5, 5), size 2
	std::shared_ptr<Shape> sp2 = std::make_shared<Diamond>(5, 2, 2);
	
	//top point at (5,8), size 3
	std::shared_ptr<Shape> sp3 = std::make_shared<Diamond>(5, 8, 3);
	
	//top left at (-1,-1), size 4
	std::shared_ptr<Shape> sp4 = std::make_shared<Square>(0, 10, 4);
	
	//Screen with x from 0 to 10, y 0 to 10, inclusive
	Collection c(0, 10, 0, 10);
	
	// add the shapes
	c.addShape(sp1);
	c.addShape(sp2);
	//c.addShape(sp3);
	//c.addShape(sp4);
	//display the shapes
	c.display();
	
	//check total number of stars on display
	std::cout << "Total visible stars: " << c.totalStars() << '\n';
	
	//add another square
	c.addShape(std::make_shared<Square>(5, 5, 1)); //top left at (5,5), size 1x1
	//change all shapes to have size 2 including the newly added square
	c.setSize(2);
	//move sp3 right 2 and down by 4
	sp1->moveBy(2, 4);
	//move all shapes up 1
	c.moveBy(-1, 0);
	//display the shapes
	c.display();

	std::cout << '\n';
	
	//check total number of stars on display
	std::cout << "Total visible stars: " << c.totalStars() << '\n';

	return 0;
}
