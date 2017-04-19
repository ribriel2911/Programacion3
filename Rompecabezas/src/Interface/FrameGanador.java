package Interface;

import Ingenieria.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class FrameGanador{
	
	JInternalFrame frame;
	JLabel fondo;
	JLabel felicitacion;
	JLabel tiempo;
	JLabel movimientos;
	private ImageEditor ie;
	
	public FrameGanador(Rectangle rec, String time,int mov)
	{
		frame = new JInternalFrame();
		frame.setBounds(rec);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		fondo = new JLabel("");
		fondo.setBounds(0,0,frame.getWidth(),frame.getHeight());
		ie = new ImageEditor("fondowin.jpg");
		ie.resize(fondo.getWidth(), fondo.getHeight());
		fondo.setIcon(new ImageIcon(ie.getImage()));
		
		felicitacion = new JLabel("Felicitaciones!!!");
		
		Font aux = felicitacion.getFont();
		felicitacion.setFont(new Font(aux.getFontName(), aux.getStyle(), 26));
		felicitacion.setForeground(Color.white); 
		
		int ancho = felicitacion.getText().length()*13;
		int alto = 26;
		int x = (frame.getWidth()-ancho)/4;
		int y = alto;
		
		felicitacion.setBounds(x,y,ancho,alto);
		
		String tx = "Su tiempo fue: "+time;
		
		tiempo = new JLabel(tx);
		
		aux = tiempo.getFont();
		tiempo.setFont(new Font(aux.getFontName(), aux.getStyle(), 26));
		tiempo.setForeground(Color.white); 
		tiempo.setBounds(20,frame.getHeight()/2,tx.length()*13,26);
		
		tx = "Sus movimientos fueron: "+ mov;
		
		movimientos = new JLabel(tx);
		
		aux = movimientos.getFont();
		movimientos.setFont(new Font(aux.getFontName(), aux.getStyle(), 26));
		movimientos.setForeground(Color.white); 
		movimientos.setBounds(20,(frame.getHeight()/2)+52,tx.length()*26,26);
		
		frame.getContentPane().add(felicitacion);
		frame.getContentPane().add(tiempo);
		frame.getContentPane().add(movimientos);
		frame.getContentPane().add(fondo);
		
	}
	
	public void resize(int ancho,int alto){
		
		int anchof = (ancho*3)/4;
		int altof = (alto*3)/4;
		int x = 0;
		int y = (alto-altof)-38;

		frame.setBounds(x, y,anchof, altof);
		
		fondo.setBounds(0,0,frame.getWidth(),frame.getHeight());
		ie = new ImageEditor("fondowin.jpg");
		ie.resize(fondo.getWidth(), fondo.getHeight());
		fondo.setIcon(new ImageIcon(ie.getImage()));
		
		anchof = felicitacion.getText().length()*13;
		altof = 26;
		x = (frame.getWidth()-anchof)/4;
		y = altof;
		
		felicitacion.setBounds(x,y,anchof,altof);
		tiempo.setBounds(20,frame.getHeight()/2,tiempo.getText().length()*26,26);
		movimientos.setBounds(20,(frame.getHeight()/2)+52,movimientos.getText().length()*26,26);
		
	}
	

}
