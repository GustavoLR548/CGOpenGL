package scene.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import entity.ui.Button;
import input.MouseInput;
import scene.Scene;

/**
 * Classe para gerenciar a parte superior do canvas, responsável em armazenar os botões
 * que o programa precisa para gerenciar entre as diferentes ações
 * @author gustavolr
 */
public class CanvasController extends Scene {
	
	// Botões do canvas e botão atualmente selecionado
	ArrayList<Button> canvas_buttons;
	Button curr_pressed_button;
	
	// Cor do controlado do canvas
	Color canvas_color;
	
	// Controlar as ações selecionadas
	CanvasAction curr_action;
	CanvasAction previous_action;
	
	public CanvasController(int x, int y, int width, int height) {
		super(x, y, width, height, "canvas_controller", (byte)0);
		
		this.curr_pressed_button = null;
		
		this.curr_action     = CanvasAction.Nothing;
		this.previous_action = CanvasAction.Nothing;
		
		this.canvas_buttons = new ArrayList<Button>();
		this.canvas_color   = new Color(211,211,211);
		
		this.canvas_buttons.add(new Button("Seleção",50, 30, 145, 40, CanvasAction.Selection));
		this.canvas_buttons.add(new Button("Desenhar Linha DDA",250, 30, 290, 40, CanvasAction.Draw_line_dda));
		this.canvas_buttons.add(new Button("Desenhar Linha Bresenham",250, 80, 290, 40, CanvasAction.Draw_line_bresenham));
		this.canvas_buttons.add(new Button("Desenhar Círculo",550, 30, 200, 40, CanvasAction.Draw_circle));
		this.canvas_buttons.add(new Button("Cohen Clipping",760, 30, 200, 40, CanvasAction.Clipping_cohen));
		this.canvas_buttons.add(new Button("Liang Clipping",760, 80, 200, 40, CanvasAction.Clipping_liang));
		this.canvas_buttons.add(new Button("Limpar tela",50, 80, 145, 40, CanvasAction.Clear_screen));
	}
	
	public CanvasAction get_action() {
		return this.curr_action;
	}
	
	public CanvasAction get_previous_action() {
		return this.previous_action;
	}
	
	public void reset_curr_action() {
		this.curr_action = CanvasAction.Nothing;
	}
	
	public void reset_previous_action() {
		this.previous_action = CanvasAction.Nothing;
	}
	
	public void tick() {
		
		if(isColidding(MouseInput.get_mouse_entity())) {
			
			for(Button b : canvas_buttons) {
				b.tick();

				if(b.is_button_pressed() && b != this.curr_pressed_button) {
					this.previous_action = this.curr_action;
					this.curr_action = b.get_action();
					
					if(this.curr_pressed_button != null) 
						this.curr_pressed_button.release_button();
		
					this.curr_pressed_button = b;
				}
			}
		}
			
	}
	
	public void render(Graphics g) {
		g.setColor(this.canvas_color);
		g.fillRect(0, 0, this.width, this.height);

		for(Button b : canvas_buttons) {
			b.render(g);
		}

	}

}
