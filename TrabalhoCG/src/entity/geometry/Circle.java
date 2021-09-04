package entity.geometry;

import java.awt.Graphics;

import algorithms.rasterisation.DrawCircle;
import entity.Entity;

public class Circle extends Entity{
	
	boolean is_being_pressed;
	
	public Circle(int x, int y,int r) {
		super(x, y, r, r);
		this.is_being_pressed = false;
	}
	
	public void render(Graphics g) {

		DrawCircle.bresenham(g, x, y, width);
	}

}
