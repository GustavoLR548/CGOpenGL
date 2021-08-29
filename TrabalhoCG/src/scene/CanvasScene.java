package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import algorithms.drawline.DrawLine;
import algorithms.drawfigure.DrawCircle;
import entity.Entity;
import entity.geometry.Vertex;
import input.MouseInput;
import main.Main;

public class CanvasScene extends Scene{
	
	private ArrayList<Entity> entities;

	public CanvasScene(String name) {
		super(name);
		entities = new ArrayList<Entity>();
		
	}

	public CanvasScene(String name, byte render_priority) {
		super(name,render_priority);
		entities = new ArrayList<Entity>();
	}

	public void tick() {
		
		//if(MouseInput.is_right_button_pressed()) {
		//	entities.add(new Vertex(MouseInput.get_x(), MouseInput.get_y(), 10, 10));
		//}
	}
	
	public void render(Graphics g) {
		
		// Renderizar fundo da tela
		g.setColor(Color.blue);
		g.fillRect(0, 0, Main.get_width()*Main.get_scale(), Main.get_height()*Main.get_scale());
		

		for(Entity e: entities) {
			e.render(g);
		}

		g.setColor(Color.red);
		//DrawCircle.bresenham(g, (Main.get_width()*Main.get_scale())/2, (Main.get_height()*Main.get_scale())/2, 100);
		DrawLine.bresenham(g, (Main.get_width()*Main.get_scale())/2,(Main.get_height()*Main.get_scale())/2 , MouseInput.get_x(),MouseInput.get_y() );
	}
}
