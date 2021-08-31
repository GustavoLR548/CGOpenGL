package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import algorithms.rasterisation.DrawCircle;
import algorithms.rasterisation.DrawLine;
import entity.Entity;
import entity.geometry.Vertex;
import input.MouseInput;
import main.Main;

public class CanvasScene extends Scene{
	
	private ArrayList<Entity> entities;
	private CanvasController canvas_controller;

	public CanvasScene(String name) {
		super(0,150,Main.get_width() * Main.get_scale(), (Main.get_height() * Main.get_scale()), name, (byte)0);
		entities = new ArrayList<Entity>();
		this.canvas_controller = new CanvasController(0,0,this.width,150);
		
	}

	public CanvasScene(String name, byte render_priority) {
		super(0,150,Main.get_width() * Main.get_scale(), (Main.get_height() * Main.get_scale()), name, (byte)0);
		entities = new ArrayList<Entity>();
		this.canvas_controller = new CanvasController(0,0,this.width,150);
	}

	public void tick() {
		
		this.canvas_controller.tick();
		
		if(isColidding(MouseInput.get_mouse_entity())) {
			
		}
	}
	
	public void render(Graphics g) {
		
		// Renderizar fundo da tela
		g.setColor(Color.blue);
		g.fillRect(this.x, this.y, this.width, this.height);
		

		for(Entity e: entities) {
			e.render(g);
		}

		g.setColor(Color.red);
		//DrawCircle.bresenham(g, (Main.get_width()*Main.get_scale())/2, (Main.get_height()*Main.get_scale())/2, 100);
		if(isColidding(MouseInput.get_mouse_entity()))
			DrawLine.bresenham(g, (Main.get_width()*Main.get_scale())/2,(Main.get_height()*Main.get_scale())/2 , MouseInput.get_x(),MouseInput.get_y() );
		
		this.canvas_controller.render(g);
	}
}
