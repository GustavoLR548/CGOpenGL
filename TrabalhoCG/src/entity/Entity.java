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

	// Posições
	protected int x;
	protected int y;
	
	// Medidas
	protected int width;
	protected int height;
	
	// Cor da entidade
	protected Color color;

	public Entity(int width, int height) {
		this.width  = width;
		this.height = height;
		this.x = 0;
		this.y = 0;
	}
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		
		this.width  = width;
		this.height = height;
	}
	
	// Getters
	
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
	
	// Setters
	
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
	
	public void set_color(Color c) {
		this.color = c;
	}
	
	/**
	 * Verificar coilisão entre duas entidades
	 * @param e1 a entidade a ser comparada
	 * @return booleano indicando se há interseção entre as entidades
	 */
	public boolean isColidding(Entity e1) {
		
		Rectangle e1Mask = new Rectangle(e1.x , e1.y ,e1.width,e1.height);
		Rectangle e2Mask = new Rectangle(this.x , this.y ,this.width, this.height);
		
		boolean isIntersecting = e1Mask.intersects(e2Mask);
		
		return isIntersecting;
	}
	
	/**
	 * Função que roda a cada frame indicando o que a entidade está processando
	 */
	public void tick() {
		
	}
	
	/**
	 * Função para renderizar a entidade na tela
	 * @param g é o renderizador gráfico
	 */
	public void render(Graphics g) {

	}
	
	/**
	 * Renderizar máscara de colisão para fins de 
	 * debug
	 * @param g g é o renderizador gráfico
	 */
	public void render_mask(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(200,0,0,100));
		g2.fillRect(this.x, this.y, this.width, this.height);
	}
	
	/**
	 * Operações de Transformação 2D, cada entidade pode implementar 
	 * sua operação de forma diferente, dependendo de como a estrutura
	 * é formada
	 */

	@Override
	public void translation(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotation(int angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reflexion(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scaling(float scalex, float scaley) {
		// TODO Auto-generated method stub
		
	}



}
