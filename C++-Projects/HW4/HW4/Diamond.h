/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 4
 * Originally written: 2/6/2019
 * Edited and improved: 11/29/2020
 *
 * This file contains the declarations for the Diamond class, which
 * inherits from the Shape class.
 * 
*/

#include "Shape.h"

#ifndef _DIAMOND_
#define _DIAMOND_

class Diamond : public Shape {
public:
	Diamond(int x_coord, int y_coord, std::size_t shape_size);
	void setStars() override;
};

#endif
