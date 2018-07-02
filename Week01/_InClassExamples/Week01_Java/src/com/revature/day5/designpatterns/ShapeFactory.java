package com.revature.day5.designpatterns;

/*
 * The factory design pattern aims to abstract the creation of a specific object
 * type of a class instead of the user itself.
 * This is useful for complex class types that require a bit of configuration that can
 * instead be taken from the user handled by a factory class.
 */
public class ShapeFactory {
	public static Shape getShape(String shape){
		switch(shape.toLowerCase()){
		case "square":
			return new Square();
		case "triangle":
			return new Triangle();
		default:
			return null;
		}
	}
}
