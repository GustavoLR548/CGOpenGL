package entity.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import entity.Entity;
import input.MouseInput;
import scene.canvas.CanvasAction;

public class Button extends Entity {
	
	private final int button_interval = 60;
	
	boolean is_pressed;
	
	CanvasAction pressed_action;
	
	FontMetrics metrics;
	
	Color button_color;
	Color selected;
	Color not_selected;
	Color pressed;
	Color border_color;
	
	String label = "Text";
	
	Font labelFont;
	
	int button_interval_frame = 0;

	public Button(String label, int x,int y,int width, int height, CanvasAction pressed_action) {
		super(x, y, width, height);
		
		this.pressed_action = pressed_action;
		
		is_pressed = false;
		
		this.selected     = new Color(155,155,155);
		this.not_selected = new Color(233,233,233);
		this.pressed      = Color.red;
		
		this.button_color = this.not_selected;
		this.border_color = Color.black;
		
		this.label = label;
		
		labelFont = new Font("Century", Font.BOLD, 20);
	}
	
	public CanvasAction get_action() {
		return this.pressed_action;
	}
	
	public boolean is_button_pressed() {
		return this.is_pressed;
	}
	
	public void release_button() {
		this.is_pressed = false;
		this.button_color = not_selected;
		this.button_interval_frame = button_interval;
	}
	
	public void set_button_color(Color c) {
		this.button_color = c;
	}
	
	public void set_border_color(Color c) {
		this.border_color = c;
	}
	
	public void tick() {
		
		if(button_interval_frame == 0) {
			if(isColidding(MouseInput.get_mouse_entity())) {
				if(MouseInput.is_right_button_clicked()) {	
					
					button_color = pressed;
					is_pressed = !is_pressed;
					button_interval_frame = button_interval;
				} else {
					button_color = selected;
				}
			} else {  
				if(is_pressed)
					button_color = pressed;
				else
					button_color = not_selected;
			}
		} else {
			button_interval_frame--;
		}
	}
	
	public void render(Graphics g) {
		
		g.setColor(button_color);
		g.fillRect(x, y, width, height);
		
		g.setColor(border_color);
		Graphics2D g2 = (Graphics2D) g;
		float thickness = 5;
		Stroke oldStroke = g2.getStroke();
		g2.setStroke(new BasicStroke(thickness));
		g2.drawRect(x, y, width, height);
		g2.setStroke(oldStroke);
		
		g.setColor(border_color);
		g.setFont(labelFont);
		
		metrics = g.getFontMetrics(labelFont);
		
		int x = this.x + (this.width - metrics.stringWidth(label)) / 2;
		
	    int y = this.y + ((this.height - metrics.getHeight()) / 2) + metrics.getAscent();
		
		g.drawString(this.label, x, y+7 );
	}
	

	
}
