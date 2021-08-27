package entity.geometry;

import java.awt.Color;
import java.awt.Graphics;

import entity.Entity;

public class Vertex extends Entity{

	public Vertex(int x, int y, int width, int height) {
		super(x,y,width, height);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(this.x, this.y, this.width, this.height);
	}

}
