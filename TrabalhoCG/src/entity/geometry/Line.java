package entity.geometry;

import java.awt.Graphics;

import algorithms.rasterisation.DrawLine;
import entity.Entity;

public class Line extends Entity{

	Vertex v1;
	Vertex v2;
	
	LineType type;
	
	public Line(Vertex v1, Vertex v2, LineType type) {
		super(0,0,0,0);
		this.v1 = v1;
		this.v2 = v2;

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
	
	public void set_v1(Vertex v1) {
		this.v1 = v1;
	}
	
	public void set_v2(Vertex v2) {
		this.v2 = v2;
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
	
	public void render(Graphics g) {
		
		if(this.type == LineType.DDA) 
			DrawLine.dda(g, v1.get_x(), v1.get_y(), v2.get_x(), v2.get_y());
		else 
			DrawLine.bresenham(g, v1.get_x(), v1.get_y(), v2.get_x(), v2.get_y());
		
	}
	
}
