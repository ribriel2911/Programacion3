package Interface;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

import Ingenieria.Juego;

public class FrameTablero {
	
	protected JInternalFrame frame;
	private Juego juego;
	private JButton[][] botones;
	
	public FrameTablero(int ancho,int alto,Juego game){
		
		int anchoTab = (ancho*3)/4;
		int altoTab = (alto*3)/4;
		int xTablero = 0;
		int yTablero = (alto-altoTab)-38;
		
		juego = game;
		
		Rectangle tablero = new Rectangle(xTablero,yTablero,anchoTab,altoTab);
		
		frame = new JInternalFrame();
		frame.setBounds(tablero);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		botones = new JButton[juego.getFilas()][juego.getColumnas()];
		
		for(int i=0;i<juego.getFilas();i++){
			for(int j=0;j<juego.getColumnas();j++){
				
				JButton boton = new JButton(""+((i*juego.getColumnas())+j));
				
				Rectangle casilla = juego.getDatosCasilla(i,j);
				boton.setBounds(casilla);
				boton.setForeground(new Color(255, 255, 255, 0));
				
				if(i!=0 || j!=0){

					boton.setIcon(new ImageIcon(juego.getImagenCasilla(i, j)));
					boton.setIconTextGap(-juego.getDatosCasilla(i, j).width);
					
					boton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							
							@SuppressWarnings("deprecation")
							int aux = Integer.parseInt(boton.getLabel());
							int columna = aux%juego.getColumnas();
							int fila = aux/juego.getColumnas();
							
							if(juego.trueque(fila,columna)){
								
								Point llegada = juego.getDatosCasilla(fila,columna).getLocation();
								Boton.animacion(boton, llegada, 2);
								botones[0][0].setBounds(juego.getDatosCasilla(0,0));
							}
							
							if(juego.gano()){
								
								System.out.println("gano!!!");
								
								frame.setVisible(false);
							}
						}
					});
					
				}
					
				botones[i][j] = boton;
				frame.getContentPane().add(botones[i][j]);
			}
		}
	}
	
	public void resize(int ancho,int alto){
		
		int anchoTab = (ancho*3)/4;
		int altoTab = (alto*3)/4;
		int xTablero = 0;
		int yTablero = (alto-altoTab)-38;
		
		Rectangle tablero = new Rectangle(xTablero,yTablero,anchoTab,altoTab);
		
		
	
		juego.resizeTablero(new Point(anchoTab,altoTab));
		
		for (int i=0;i<botones.length;i++){
			
			for(int j=0;j<botones[i].length;j++){
				
				Rectangle casilla = juego.getDatosCasilla(i,j);
				
				botones[i][j].setBounds(casilla);
				
				if(i!=0 || j!=0){
					
					botones[i][j].setIcon(new ImageIcon(juego.getImagenCasilla(i, j)));
					botones[i][j].setIconTextGap(-juego.getDatosCasilla(i, j).width);
				}
			}
		}
		
		frame.setBounds(tablero);
	}
	
	public Image getImage(){
		
		return juego.getImagenTablero();
	}

}
