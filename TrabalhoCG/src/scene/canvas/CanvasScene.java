package scene.canvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.ArrayList;

import algorithms.clipping.LineClipping;
import entity.Entity;
import entity.geometry.Circle;
import entity.geometry.Line;
import entity.geometry.LineType;
import entity.geometry.Vertex;
import input.MouseInput;
import main.Main;
import scene.Scene;

public class CanvasScene extends Scene{
	
	private ArrayList<Vertex> vertexes;
	private ArrayList<Entity> entities;
	
	private Entity selected_entity;
	
	private Rectangle clippingRectangle;
	
	private CanvasController canvas_controller;

	public CanvasScene(String name) {
		super(0,150,Main.get_width() * Main.get_scale(), (Main.get_height() * Main.get_scale()), name, (byte)0);
		
		vertexes = new ArrayList<Vertex>();
		entities = new ArrayList<Entity>();
		
		this.canvas_controller = new CanvasController(0,0,this.width,150);
		
	}

	public CanvasScene(String name, byte render_priority) {
		super(0,150,Main.get_width() * Main.get_scale(), (Main.get_height() * Main.get_scale()), name, (byte)0);
		
		vertexes = new ArrayList<Vertex>();
		entities = new ArrayList<Entity>();
		
		this.canvas_controller = new CanvasController(0,0,this.width,150);
	}

	public void tick() {
		
		this.canvas_controller.tick();
		
		if(isColidding(MouseInput.get_mouse_entity())) {
			
			draw_operation();
		}
		
		reset_operation();
	}
	
	public void draw_operation() {
		
		if((canvas_controller.get_action() == CanvasAction.Draw_line_dda || 
		   canvas_controller.get_action() == CanvasAction.Draw_line_bresenham) &&
		   MouseInput.is_right_button_clicked()) {
			
			vertexes.add(new Vertex(MouseInput.get_x(), MouseInput.get_y(), 10, 10));
			
			if(vertexes.size() == 2) {
				
				entities.add(new Line(vertexes.get(0),
										vertexes.get(1), 
										LineType.convertCanvasAction(canvas_controller.get_action())));
				vertexes.clear();
			}
			
		} else if(canvas_controller.get_action() == CanvasAction.Draw_circle &&
				  MouseInput.is_right_button_clicked()) {
			entities.add(new Circle(MouseInput.get_x(), MouseInput.get_y(), 50));
			
		} else if(canvas_controller.get_action() == CanvasAction.Selection) {
			for(Entity e : this.entities) {	
				if(e.isColidding(MouseInput.get_mouse_entity()) && MouseInput.is_right_button_pressed()) 
					this.selected_entity = e;
				
			}

			
			if(MouseInput.is_right_button_pressed()) {
				this.selected_entity.translation();
			}
			
			
		} else if((canvas_controller.get_action() == CanvasAction.Clipping_cohen || canvas_controller.get_action() == CanvasAction.Clipping_liang)&&
				  MouseInput.is_right_button_clicked()) {
			
			clippingRectangle = new Rectangle(MouseInput.get_x(), MouseInput.get_y(), 350, 350);
			LineClipping.defineClippingRectangle(MouseInput.get_x(), MouseInput.get_x() + 350, MouseInput.get_y(), MouseInput.get_y() + 350);
		}
	}
	
	public void reset_operation() {
		
		if(this.canvas_controller.get_previous_action() == CanvasAction.Clipping_cohen ||
		   this.canvas_controller.get_previous_action() == CanvasAction.Clipping_liang ) {
			
			this.clippingRectangle = null;
			LineClipping.resetClippingRectangle();
			this.canvas_controller.reset_previous_action();
		}
	}
	
	public Line clipLine(Graphics g, Line l) {
		if(this.canvas_controller.get_action() == CanvasAction.Clipping_cohen) 
			return LineClipping.cohenSutherlandClip(g, l);
		
		return LineClipping.liangBarskyClipping(g, l);
	}
	
	public void render(Graphics g) {
		
		// Renderizar fundo da tela
		g.setColor(Color.blue);
		g.fillRect(this.x, this.y, this.width, this.height);
		
		g.setColor(Color.red);
		for(Entity e: entities) {
		
			if(e instanceof Line) {
				Line tmp = this.clipLine(g, (Line)e);
				if(tmp != null) 
					tmp.render(g);
				
				continue;
			} 				
			
			e.render(g);
			
		}
		
	
		for(Vertex v: vertexes) 
			v.render(g);
		

		this.canvas_controller.render(g);
		
		g.setColor(Color.red);
		if(this.clippingRectangle != null) {
			Graphics2D g2 = (Graphics2D) g;
			float thickness = 5;
			Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke(thickness));
			g2.drawRect(
					this.clippingRectangle.x,
					this.clippingRectangle.y,
					this.clippingRectangle.width,
					this.clippingRectangle.height
			);
			g2.setStroke(oldStroke);
		}
	}
}
