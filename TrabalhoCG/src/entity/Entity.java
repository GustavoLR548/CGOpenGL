package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entity.geometry.Transformation2D;

/**
 * Classe generica para armazenar objetos genericos
 * dentro do canvas
 * @author gustavolr
 *
 */
public class Entity implements Transformation2D {

	protected int x;
	protected int y;
	
	protected int width;
	protected int height;
	
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
	
	public void set_x(int x) {
		this.x = x;
	}
	
	public void set_y(int y) {
		this.y = y;
	}
	
	public void set_width(int width) {
		this.width = width;
	}
	
	public void set_height(int height) {
		this.height = height;
	}
	
	public boolean isColidding(Entity e1) {
		
		Rectangle e1Mask = new Rectangle(e1.x , e1.y ,e1.width,e1.height);
		Rectangle e2Mask = new Rectangle(this.x , this.y ,this.width, this.height);
		
		boolean isIntersecting = e1Mask.intersects(e2Mask);
		
		return isIntersecting;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {

	}
	
	public void render_mask(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(200,0,0,100));
		g2.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public void translation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reflexion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scaling() {
		// TODO Auto-generated method stub
		
	}
}
