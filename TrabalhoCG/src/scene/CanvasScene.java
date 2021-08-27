package scene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import algorithms.drawline.DrawLine;
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
		
		if(MouseInput.is_right_button_pressed()) {
			entities.add(new Vertex(MouseInput.get_x(), MouseInput.get_y(), 10, 10));
			MouseInput.set_right_button_press(false);
		}
		
		System.out.println(entities.size());
	}
	
	public void render(Graphics g) {
		
		// Renderizar fundo da tela
		g.setColor(Color.blue);
		g.fillRect(0, 0, Main.get_width()*Main.get_scale(), Main.get_height()*Main.get_scale());
		

		for(Entity e: entities) {
			e.render(g);
		}
		
		if(entities.size() > 20) {
			g.setColor(Color.red);
			DrawLine.DDA(g, entities.get(0).get_x(),entities.get(0).get_y() , entities.get(10).get_x(),entities.get(10).get_y() );
		}
	}
}
