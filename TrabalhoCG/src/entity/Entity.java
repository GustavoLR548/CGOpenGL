package entity;

import java.awt.Graphics;

public class Entity {

	private int x;
	private int y;
	
	private int width;
	private int height;
	
	public Entity(int width, int height) {
		this.width  = width;
		this.height = height;
	}
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		
		this.width  = width;
		this.height = height;
	}
	
	public int get_x() {
		return this.x;
	}
	
	public int get_y() {
		return this.y;
	}
	
	public int get_width() {
		return this.width;
	}
	
	public int get_height() {
		return this.height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
}
