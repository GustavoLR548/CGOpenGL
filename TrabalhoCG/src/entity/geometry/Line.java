package entity.geometry;

import java.awt.Color;
import java.awt.Graphics;

import algorithms.rasterisation.DrawLine;
import algorithms.transformation.AllTransformations;
import entity.Entity;

/**
 * Classe para gerenciar geração de linhas
 * @author gustavolr
 *
 */
public class Line extends Entity{

	// Vertex final e inicial da linha
	Vertex v1;
	Vertex v2;
	
	// Tipo de linha, pode ser tanto bresenham ou DDA
	LineType type;
	
	public Line(Vertex v1, Vertex v2, LineType type) {
		super(0,0,0,0);
		this.v1 = v1;
		this.v2 = v2;
		
		this.color = Color.blue;

		if(v1.get_y() > v2.get_y()) 
			this.y = v2.get_y();
		else 
			this.y = v1.get_y();
		
		
		if(v1.get_x() > v2.get_x())
			this.x = v2.get_x();
		else 
			this.x = v1.get_x();
		
		
		this.width  = Math.abs(v1.get_x() - v2.get_x());
		this.height = Math.abs(v1.get_y() - v2.get_y());
		
		this.type = type;
	}
	
	
	public Vertex get_v1() {
		return this.v1;
	}
	
	public Vertex get_v2() {
		return this.v2;
	}
	
	public LineType get_line_type() {
		return this.type;
	}
	
	public void set_v1(Vertex v1) {
		this.v1 = v1;
	}
	
	public void set_v2(Vertex v2) {
		this.v2 = v2;
	}
	
	
	public void translation(int mouse_x, int mouse_y) {
		
		int dx1 = this.x - v1.get_x();
		int dx2 = this.x - v2.get_x();
		
		int dy1 = this.y - v1.get_y();
		int dy2 = this.y - v2.get_y();

		this.x = this.x + (mouse_x - this.x) - width/2;
		this.y = this.y + (mouse_y - this.y) - height/2;
		
		v1.set_x(x);
		v1.set_y(y);
	
		
		v2.set_x(x);
		v2.set_y(y);
		
		this.v1 = AllTransformations.translate(v1, dx1, dy1);
		this.v2 = AllTransformations.translate(v2, dx2, dy2);
		
	}
	
	public void scaling(float scalex, float scaley) {

		this.x *= scalex;
		this.y *= scaley;
	
		this.v1 = AllTransformations.scaling(v1, scaley);
		this.v2 = AllTransformations.scaling(v2, scaley);
	}
	
	public void rotation(int angle) {
		
		System.out.println("v1 " + v1.get_x() + " " + v1.get_y());
		System.out.println("v2 " + v2.get_x() + " " + v2.get_y());
		this.v1 = AllTransformations.rotating(v1, angle);
		this.v2 = AllTransformations.rotating(v2, angle);
		System.out.println("v1 " + v1.get_x() + " " + v1.get_y());
		System.out.println("v2 " + v2.get_x() + " " + v2.get_y());
	}
	
	public void render(Graphics g) {
		
		//render_mask(g);
		
		g.setColor(this.color);
		if(this.type == LineType.DDA) 
			DrawLine.dda(g, v1.get_x(), v1.get_y(), v2.get_x(), v2.get_y());
		else 
			DrawLine.bresenham(g, v1.get_x(), v1.get_y(), v2.get_x(), v2.get_y());
		
	}
	
}
