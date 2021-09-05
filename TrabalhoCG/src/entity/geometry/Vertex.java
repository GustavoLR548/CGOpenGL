package entity.geometry;

import java.awt.Color;
import java.awt.Graphics;

import entity.Entity;

/**
 * Classe para armazenar pontos no canvas
 * @author gustavolr
 *
 */
public class Vertex extends Entity{

	public Vertex(int x,int y) {
		super(x,y,10,10);
		this.color = Color.blue;
	}
	
	public Vertex(int x, int y, int width, int height) {
		super(x,y,width, height);
		this.color = Color.blue;
	}
	
	public void render(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x, this.y, this.width, this.height);
	}

}
