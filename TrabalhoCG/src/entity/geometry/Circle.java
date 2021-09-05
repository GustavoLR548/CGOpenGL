package entity.geometry;

import java.awt.Color;
import java.awt.Graphics;

import algorithms.rasterisation.DrawCircle;
import entity.Entity;

/**
 * Classe para gerenciar o círculo
 * @author gustavolr
 *
 */
public class Circle extends Entity{
	
	public Circle(int x, int y,int r) {
		super(x, y, r, r);
		this.color = Color.blue;
	}
	
	public void translation(int mx, int my) {
		
		this.x = mx;
		this.y = my;
	}
	
	public void scaling(int sx, int sy) {
		this.width *= sy;
	}
	
	public void render(Graphics g) {

		g.setColor(this.color);
		DrawCircle.bresenham(g, x, y, width);
	}

}
