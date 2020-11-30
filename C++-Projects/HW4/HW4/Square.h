/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 4
 * Originally written: 2/6/2019
 * Edited and improved: 11/29/2020
 *
 * The file contains the declarations for the Square class, which inherits 
 * from the Shape class. The virtual setStars function from the Shape class is
 * overidden here.
 * 
*/
#include "Shape.h"

#ifndef _SQUARE_
#define _SQUARE_

class Square : public Shape {
public:
	Square(int x_coord, int y_coord, std::size_t shape_size);
	void setStars() override;
};

#endif 
