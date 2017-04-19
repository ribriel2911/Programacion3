package Ingenieria;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Juego {
	
	private		Tablero		tablero;
	private 	Point		casillaVacia;
	private 	int			cantMovimientos;
	
	public Juego(){
		
		Random azar = new Random();
		
		ImageEditor ie = new ImageEditor("imagen"+azar.nextInt(4)+".jpg");
		Rectangle r = new Rectangle(3,3,400,300);
		
		tablero = new Tablero(r,ie.getImage());
		casillaVacia = new Point(0,0);
		cantMovimientos = 0;
	}
	
	public Juego(Rectangle r,Image im){
		
		tablero = new Tablero(r,im);
		casillaVacia = new Point(0,0);
		cantMovimientos = 0;
	}
	
	public Juego(int ancho,int alto){
		
		Rectangle r = new Rectangle(3,3,ancho,alto);
		
		Random azar = new Random();
		
		ImageEditor ie = new ImageEditor("imagen"+azar.nextInt(5)+".jpg");
		
		tablero = new Tablero(r,ie.getImage());
		casillaVacia = new Point(0,0);
		cantMovimientos = 0;
	}
	
	
	public Juego(Rectangle r){
		
		Random azar = new Random();
		
		ImageEditor ie = new ImageEditor("imagen"+azar.nextInt(4)+".jpg");
		
		tablero = new Tablero(r,ie.getImage());
		casillaVacia = new Point(0,0);
		cantMovimientos = 0;
	}
	
	public Juego(Image im){
		
		Rectangle r = new Rectangle(3,3,450,450);
		
		tablero = new Tablero(r,im);
		casillaVacia = new Point(0,0);
		cantMovimientos = 0;
	}
	
	public Juego(String direction){
		
		ImageEditor ie = new ImageEditor(direction);
		Rectangle r = new Rectangle(3,3,450,450);
		
		tablero = new Tablero(r,ie.getImage());
		casillaVacia = new Point(0,0);
		cantMovimientos = 0;
	}	
	
	public boolean trueque(int fila,int columna){
		
		Point x = new Point(fila,columna);
		
		if(tablero.estaCerca(casillaVacia,x)){
			
			if(tablero.estaAlineado(casillaVacia,x)){
			
				tablero.cambiarPosiciones(casillaVacia, x);
				cantMovimientos++;
				
				return true;
			}
		}
		return false;
	}
	
	public boolean gano(){
		
		int y=0;
		
		for(int i=0;i<getFilas();i++){
			
			int x=0;
			for(int j=0;j<getColumnas();j++){
				
				
				if ((tablero.casillas[i][j].casilla.x != x)
				|| (tablero.casillas[i][j].casilla.y != y)){
					
					return false;
				}
				x += tablero.ancho/tablero.columnas;
			}
			y += tablero.alto/tablero.filas;	
		}
		
		return true;
	}
	
	public int getMovimientos(){
		
		return cantMovimientos;
	}
	
	public int getCantCasillas(){return tablero.casillas.length;}
	
	public int getAnchoTab()	{return tablero.ancho;}
	
	public int getAltoTab()		{return tablero.alto;}
	
	public int getFilas()		{return tablero.filas;}
	
	public int getColumnas()	{return tablero.columnas;}
	
	public Image getImagenTablero() {
		
		return tablero.imagen;
	}
	
	public Image getImagenCasilla(int fila,int columna){
		
		return tablero.casillas[fila][columna].imagen;
	}
	
	public Rectangle getDatosCasilla(int fila,int columna){
		
		return new Rectangle(tablero.casillas[fila][columna].casilla);
	}

	public void resizeTablero(Point p){
		
		tablero.resize(p);
	}
}
