/**
 * @Author : BEYZA YÜKSEL 
**/

package com.SnakeGame;

import java.util.ArrayList;

public class Snake {
	// Encapsulation all attributes.
    private String direction;
    // Snake speed is 30.
    private final int speed = 30; 
    private int width;
    private int height;
    
    // Store coordinates as x and y of Snake
    private ArrayList<Integer> x = new ArrayList<Integer>();
    private ArrayList<Integer> y = new ArrayList<Integer>();

    // Default Constructor
    public Snake() {
    	// First coordinates assigned to the snake when snake starts the game.
        x.add(100);
        y.add(100);

        x.add(100);
        y.add(70);
        
        // Snake first direction is "s" (s means down)
        this.direction = "s";
        // Snake first width is 30.
        this.width = 30;
        // Snake first height is 30.
        this.height = 30;
        
        
    }

    // Get direction of Snake (Direction key: a, s, d, w)
    // a key : Snake moves Left Direction
    // s key : Snake moves Down direction
    // d key : Snake moves Right direction
    // w key : Snake moves Up direction
    public String getDirection() {
        return direction;
    }

    // Set new direction to snake ( Directions are a,s,d,w in keyboard )
    public void setDirection(String direction) {
        this.direction = direction;
    }

    // Getters - Setters 
    
    // Get Snake Speed
    public int getSpeed() {
        return speed;
    }

    // Get Snake Width
    public int getWidth() {
        return width;
    }

    // Set width of snake
    public void setWidth(int width) {
        this.width = width;
    }
    // Get Snake Height
    public int getHeight() {
        return height;
    }

    // Set height of snake
    public void setHeight(int height) {
        this.height = height;
    }
   
    // Get x coordinates of snake from ArrayList
    public ArrayList<Integer> getX() {
        return x;
    }

    // Set new x coordinate to snake 
    public void setX(ArrayList<Integer> x) {
        this.x = x;
    }
    
    // Get y coordinates of snake from ArrayList
    public ArrayList<Integer> getY() {
        return y;
    }

    // Set new y coordinate to snake 
    public void setY(ArrayList<Integer> y) {
        this.y = y;
    }


}
