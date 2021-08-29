package main;

//Importando bibliotecas do proprio java
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import input.MouseInput;

/**
 * Main responsavel em iniciar o programa, alem de iniciar
 * configuracoes da janela e controles.
 * @author gustavolr
 *
 */
public class Main extends Canvas implements Runnable,MouseListener {
	
	 //Adicionando um "serial number" ao canvas
		private static final long serialVersionUID = 1L;

		//Declarando atributos da minha classe
		
		private JFrame frame; 			
		
		private static boolean isRunning; 				// Variavel para manter o programa ligado
		
		private final static int WIDTH  = 240; 			// Comprimento da janela a ser criada
		private final static int HEIGHT = 160; 			// Altura da janela a ser criada
		public final static int  SCALE  = 4; 			    // x vezes que a janela sera aumentada
		private static Thread thread;					// Criando threads
		
		private SceneHandler scene_handler = new SceneHandler();
		
		public static BufferedImage layer; 					
		
	
		public Main(){
			
			// Configurando as dimensoes da tela
			
			// Em janela
			this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
			
			// Configurando as preferencias da minha janela
			initFrame();
			
			// Adicionar um Listener a esta classe, permitindo
			// com que inputs do teclado seja possivel
			this.addMouseListener(this);
			
		}
		
		public static int get_width() {
			return WIDTH;
		}
		
		public static int get_height() {
			return HEIGHT;
		}
		
		public static int get_scale() {
			return SCALE;
		}
		
		
		//Metodo para iniciar a janela
		private void initFrame() {

			// Configurando janela
			this.frame = new JFrame("Trabalho Pratico CG"); 
			frame.setResizable(false); 
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
			frame.add(this);
			frame.pack();
	
			
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			//Configurando a imagem de fundo
			layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
			
		}
		
		// Primeira funcao a ser chamada no programa
		public static void main(String[] args) {
			
			//Iniciando o nosso jogo
			Main cg = new Main();
			cg.start();
		}
		
		public synchronized void start() {
			
			//Iniciando as minhas threads
			thread = new Thread(this);
			
			//Meu jogo foi iniciado, logo, sera igual a "true"
			isRunning = true;
			
			//Iniciando as Threads
			thread.start();
			
		}
		
		//Funcao loop que irá rodar durante toda a duracao do programa
		public void run() {
			
			// Fazendo com que o programa rode a 60fps
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0.0;
			
			// Pedindo para que o canvas esteja em foco
			requestFocus();
			
			while(isRunning) {
				
				//Calculo do tempo até então decorrido
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				
				//Ao passar um segundo ou mais
				if ( delta >= 1) {
	
					tick();
					render();
					delta--;
				}
				
			}
			//Parar o programa
			try {
				stop();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Funcao que sera executada no fim do programa
		public void stop() throws InterruptedException {
			
			isRunning = false;
			thread.join();
		}

		//Funcao que chama as acoes de todas as coisas a cada frame
		public void tick() {
			
			scene_handler.tick();
			
		}
		

		//Funcao para renderizar imagens na tela
		public void render() {
			
			//Iniciando buffer para renderizar imagem
			BufferStrategy bs = this.getBufferStrategy();
			if ( bs == null ) {
				this.createBufferStrategy(3);
				return;
			}
			
			// Iniciar graphics para renderizar objetos na tela
			Graphics g = layer.getGraphics();
			g.dispose();
			g = bs.getDrawGraphics();
			
			scene_handler.render(g);
			
			//Mostrar tudo que foi pedido para ser renderizado
			
			bs.show();
			
		}
		

		@Override
		public void mouseClicked(MouseEvent m) {
			
			//Verificar se o botao direito do mouse foi pressionado
			if(m.getButton() == MouseEvent.BUTTON1) {
				MouseInput.set_right_button_press(true);
				MouseInput.set_x(m.getX()-6);
				MouseInput.set_y(m.getY()-6);
			}
			
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent m) {

			
		}

		@Override
		public void mouseReleased(MouseEvent m) {
			
			
		}
}
