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

/**
 * Principal cena para a geração do programa
 * @author gustavolr
 *
 */
public class CanvasScene extends Scene{
	
	// Conteudo a ser gerado na tela
	private ArrayList<Vertex> vertexes;
	private ArrayList<Entity> entities;
	private Entity selected_entity;
	
	// Retangulo de clipping
	private Rectangle clippingRectangle;
	
	// Cena com os botões
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
		
		if(this.selected_entity != null) {
			
			selected_entity_operations();
		}
		
		if(this.canvas_controller.get_action() == CanvasAction.Clear_screen) {
			this.vertexes.clear();
			this.entities.clear();
			this.canvas_controller.reset_previous_action();
		}
		
		reset_operation();
	}
	
	/**
	 * Operações para serem feitas com a entidade selecionada
	 */
	public void selected_entity_operations() {
		if(!this.selected_entity.isColidding(MouseInput.get_mouse_entity()) && MouseInput.is_right_button_clicked()) {
		
			
			//this.selected_entity.translation(MouseInput.get_x(), MouseInput.get_y());
			//this.selected_entity.translation(MouseInput.get_x(), MouseInput.get_y());
			
			this.selected_entity.scaling(
					(float)MouseInput.get_x() /this.selected_entity.get_x(),
					(float)MouseInput.get_y() /this.selected_entity.get_y());
			
			//double dx = MouseInput.get_x() - this.selected_entity.get_x();
			//double dy = MouseInput.get_y() - this.selected_entity.get_y();
			/*
			this.selected_entity.rotation((int)Math.atan2(dy, dx));
			*/
			
		}
	}
	
	/**
	 * Todas as operações que envolve cliques do mouse e alguma operação que foi
	 * selecionadas no controlador
	 */
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
				if(e.isColidding(MouseInput.get_mouse_entity()) && MouseInput.is_right_button_clicked()) {
					
					if(this.selected_entity != null) {
						this.selected_entity.set_color(Color.blue);
					}
					
					this.selected_entity = e;
					this.selected_entity.set_color(Color.red);
				}
				
			}
						
		} else if((canvas_controller.get_action() == CanvasAction.Clipping_cohen || canvas_controller.get_action() == CanvasAction.Clipping_liang)&&
				  MouseInput.is_right_button_clicked()) {
			
			clippingRectangle = new Rectangle(MouseInput.get_x(), MouseInput.get_y(), 350, 350);
			LineClipping.defineClippingRectangle(MouseInput.get_x(), MouseInput.get_x() + 350, MouseInput.get_y(), MouseInput.get_y() + 350);
		}
	}
	
	/**
	 * Resetar canvas, em relação a ultima operação selecionada
	 */
	public void reset_operation() {
		
		if(this.canvas_controller.get_previous_action() == CanvasAction.Clipping_cohen ||
		   this.canvas_controller.get_previous_action() == CanvasAction.Clipping_liang ) {
			
			this.clippingRectangle = null;
			LineClipping.resetClippingRectangle();
			this.canvas_controller.reset_previous_action();
		}
		
		if(this.canvas_controller.get_previous_action() == CanvasAction.Selection) {
			
			if(this.selected_entity != null ) {
				this.selected_entity.set_color(Color.blue);
				this.selected_entity = null;
			}
		}
	}
	
	/**
	 * Escolher qual algoritmo de clipping será usado
	 * @param g é o renderizador gráfico
	 * @param l é a linha ser clipada
	 * @return a linha clipada usando o algoritmo selecionado
	 */
	public Line clipLine(Graphics g, Line l) {
		if(this.canvas_controller.get_action() == CanvasAction.Clipping_cohen) 
			return LineClipping.cohenSutherlandClip(g, l);
		
		return LineClipping.liangBarskyClipping(g, l);
	}
	
	public void render(Graphics g) {
		
		// Renderizar fundo da tela
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, this.width, this.height);
		
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
		
		// Renderizar o retângulo de clipping
		g.setColor(Color.red);
		if(this.clippingRectangle != null) {
			Graphics2D g2 = (Graphics2D) g;
			float thickness = 18;
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
