/* Author: David Boudreau
 * Written for: UCLA PIC 10B Homework 4
 * Originally written: 2/6/2019
 * Edited and improved: 11/29/2020
 *
 * This file contains the Shape class declarations. The Shape class is the base class
 * for Square and Diamond, so they inherit its functionality. The Shape class sets an
 * x and y coordinate (or row and column number on the screen) and size for the shape 
 * and keeps track of its points through a vector of pairs of points.
 *
*/

#include<vector>
#include<utility>

#ifndef _SHAPE_
#define _SHAPE_

class Shape {
private:
	int x_coord;
	int y_coord;
	std::size_t shape_size;
	std::vector <std::pair<int, int>> points;

protected:
	virtual void setStars() = 0;
	void add_point(int a, int b);
	void clear_all();

public:
	
	Shape(int x_coord, int y_coord, std::size_t shape_size);
	int get_x() const;
	int get_y() const;
	size_t get_size() const;
	std::vector<std::pair<int, int>> get_points() const; 
	void moveBy(int a, int b);
	void setSize(size_t new_size);
};

#endif 
