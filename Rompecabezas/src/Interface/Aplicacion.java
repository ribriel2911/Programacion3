package Interface;

import Ingenieria.*;
import Inicio.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JLabel;

public class Aplicacion {
	
	private JFrame 			frame;
	
	private JInternalFrame 	internalFrame;
	private FrameTablero	tab;
	private FrameGanador	win;	
	
	private Juego			juego;
	
	private ImageEditor 	ie;
	private Tiempo 			timer;
	
	private	JLabel 			marcoSup;
	private JLabel 			marcoDer;
	private JLabel			imagenAformar;
	private JLabel			tiempo;
	private Clip			sonido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion window = new Aplicacion();
					window.frame.setVisible(true);
					
					window.sonido = Herramientas.cargarSonido("gtaCancionMain.wav");
					window.sonido.loop(Clip.LOOP_CONTINUOUSLY);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(70,0,1225, 745);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int anchoTab = (frame.getWidth()*3)/4;
		int altoTab = (frame.getHeight()*3)/4;
		
		juego = new Juego(anchoTab,altoTab);
				
		timer= new Tiempo();
		tab = new FrameTablero(frame.getWidth(),frame.getHeight(),juego);
		win= new FrameGanador(tab.frame.getBounds(),timer.lbl.getText(),juego.getMovimientos());
		
		internalFrame = tab.frame;
		
		internalFrame.setVisible(true);
		frame.getContentPane().add(internalFrame);
		
		//Quita los bordes del frame
		internalFrame.setBorder(null);;
		((BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);
		
		marcoSup = new JLabel("");
		marcoDer = new JLabel("");
		tiempo = new JLabel("");
		imagenAformar = new JLabel("");
		
		
		tiempo = timer.lbl;
		
		Font aux = tiempo.getFont();
		
		tiempo.setFont(new Font(aux.getFontName(), aux.getStyle(), 26));
		tiempo.setForeground(Color.white); 
		
		Border blackline = BorderFactory.createLineBorder(Color.white);
		
		imagenAformar.setBorder(blackline);
			
		//Dibuja el marco
		dibujarLabel(marcoSup,0,0,internalFrame.getWidth(),internalFrame.getLocation().y,"nombre.jpg");
        dibujarLabel(marcoDer,marcoSup.getWidth(),0,frame.getWidth()-internalFrame.getWidth(), frame.getHeight(),"marcoDer.jpg");
		dibujarLabel(imagenAformar,marcoDer.getX()+(marcoDer.getWidth()/4),marcoDer.getY()+(marcoDer.getWidth()/4),marcoDer.getWidth()/2,marcoDer.getWidth()/2,tab.getImage());
		int x = marcoDer.getX()+(marcoDer.getWidth()/2)-52;
		int y = marcoDer.getHeight()/2;	
		tiempo.setBounds(x, y, 104, 26);
		
		frame.getContentPane().add(marcoSup);
		frame.getContentPane().add(tiempo);
		frame.getContentPane().add(imagenAformar);
		frame.getContentPane().add(marcoDer);	
		
		timer.reloj.start();
		
		//Si detecta un cambio en las dimensiones
		//del frame redimensiona la pantalla
		resize();
		//Si se cierra el frame del tablero cambia al de ganador.
		cambiarFrame();
	}
	
	private void resize(){
		
		frame.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
	            	
				tab.resize(frame.getWidth(),frame.getHeight());
	            win.resize(frame.getWidth(),frame.getHeight());
		        dibujarLabel(marcoSup,0,0,internalFrame.getWidth(),internalFrame.getLocation().y,"nombre.jpg");
		        dibujarLabel(marcoDer,marcoSup.getWidth(),0,frame.getWidth()-internalFrame.getWidth(), frame.getHeight(),"marcoDer.jpg");
				dibujarLabel(imagenAformar,marcoDer.getX()+(marcoDer.getWidth()/4),marcoDer.getY()+(marcoDer.getWidth()/4),marcoDer.getWidth()/2,marcoDer.getWidth()/2,tab.getImage());
				int x = marcoDer.getX()+(marcoDer.getWidth()/2)-52;
				int y = marcoDer.getHeight()/2;	
				tiempo.setBounds(x, y, 104, 26);
			}
	    });
	}
	
	private void cambiarFrame(){
		
		
		internalFrame.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentHidden(java.awt.event.ComponentEvent evt) {
				
				timer.reloj.stop();
				
				frame.getContentPane().remove(internalFrame);
				
				win= new FrameGanador(tab.frame.getBounds(),timer.lbl.getText(),juego.getMovimientos());
				
				internalFrame = win.frame;
				
				frame.getContentPane().add(internalFrame);
				
				//Quita los bordes del frame
				internalFrame.setBorder(null);;
				((BasicInternalFrameUI) internalFrame.getUI()).setNorthPane(null);
			}
		});
	}
	
	private void dibujarLabel(JLabel l,int x,int y,int ancho, int alto, String imagen){
		
		l.setBounds(x, y, ancho, alto);
		
		ie = new ImageEditor(imagen);
		ie.resize(l.getWidth(), l.getHeight());
		l.setIcon(new ImageIcon(ie.getImage()));
	}
	
	private void dibujarLabel(JLabel l,int x,int y,int ancho, int alto, Image imagen){
		
		l.setBounds(x, y, ancho, alto);
		
		ie = new ImageEditor(imagen);
		ie.resize(l.getWidth(), l.getHeight());
		l.setIcon(new ImageIcon(ie.getImage()));
	}
}
