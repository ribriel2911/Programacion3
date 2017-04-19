package Ingenieria;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Tablero {
	
	protected int filas;
	protected int alto;
	protected int columnas;
	protected int ancho;
	protected Casilla[][] casillas;
	protected Image imagen;
	protected Image[] partes;
	
	protected Tablero(Rectangle tab,Image im){
		
		try{
	
			filas = tab.y;
			alto = tab.height;
			columnas = tab.x;
			ancho = tab.width;
			
			casillas = new Casilla[filas][columnas];
			
			ImageEditor imid = new ImageEditor(im);
			imid.resize(ancho, alto);
			
			imagen = imid.getImage();
			partes = imid.trim(filas,columnas);
			
			inicializarCasillas();
		}
		catch(ArithmeticException e){
			System.out.println("Tablero: La cantidad de filas o columnas del tablero no pueden ser nulas.");
		}
		catch(IllegalArgumentException e){
			System.out.println("Tablero: Las dimensiones del tablero no pueden ser negativas.");
		}
		catch (NegativeArraySizeException e)	{
			System.out.println("Tablero: La cantidad de filas o columnas del tablero no pueden ser negativas.");
		}
		catch (NullPointerException e){
			System.out.println("Tablero: Uno de los parametros del constructor no esta inicializado.");
		}
	}
	
	private void inicializarCasillas(){
		
		Rectangle pos = new Rectangle(columnas,filas,ancho/columnas,alto/filas);
		
		ArrayList<Point> posiciones = Ordenamiento.posicionar(pos);
		
		posiciones = Ordenamiento.desordenar(posiciones);
		
		for(int i=0;i<filas;i++){
			
			for(int j=0;j<columnas;j++){
				
				Point posicion = posiciones.get((i*columnas)+j);
				
				int x = posicion.x;
				int y = posicion.y;
				int anchoCasilla = ancho/columnas;
				int altoCasilla = alto/filas;
				Image imagenCasilla = partes[(i*columnas)+j];
				casillas[i][j] = new Casilla(new Rectangle(x,y,anchoCasilla,altoCasilla),imagenCasilla);
			}
		}
	}
	
	protected void resize(Point tab){
		
		try{
			
			if	((tab.x!=ancho)
			||	(tab.y!=alto)){
			
				alto = tab.y;
				ancho = tab.x;
				
				ImageEditor imid = new ImageEditor(imagen);
				imid.resize(ancho, alto);
				partes = imid.trim(filas,columnas);
				
				resizeCasillas();
			}
		}
		catch(IllegalArgumentException e){
			System.out.println("Tablero: Las dimensiones del tablero no pueden ser negativas.");
		}
	}
	
	private void resizeCasillas(){
		
		for(int i=0;i<filas;i++){
			
			for(int j=0;j<columnas;j++){
				
				casillas[i][j].resize(ancho/columnas, alto/filas,partes[(i*columnas)+j]);
			}
		}
	}
	
	private boolean chequearCasilla(Point a){
		
		if	((a==null)
		||	(a.x<0)
		||	(a.x>=filas)
		||	(a.y<0)
		||	(a.y>=columnas)
		||	(casillas[a.x][a.y]==null)){
			
			return false;
		}
		
		
		return true;
	}
	
	protected boolean cambiarPosiciones(Point a, Point b){
		
		boolean flag = false;
		
		if	((chequearCasilla(a))
		&&	(chequearCasilla(b))){
			
		
			Casilla o = casillas[a.x][a.y];
			Casilla p = casillas[b.x][b.y];
			
			if(o.casilla.x != p.casilla.x){
	
				int aux = o.casilla.x;
				o.casilla.x = p.casilla.x;
				p.casilla.x = aux;
				
				flag = true;
			}
			
			if(o.casilla.y!=p.casilla.y){
	
				int aux = o.casilla.y;
				o.casilla.y = p.casilla.y;
				p.casilla.y = aux;
				
				flag = true;
			}
		}
		return flag;
	}
	
	protected boolean estaCerca(Point a, Point b){
		
		if	((chequearCasilla(a))
		&&	(chequearCasilla(b))){
				
			Casilla o = casillas[a.x][a.y];
			Casilla p = casillas[b.x][b.y];
			
			return	(o.casilla.x-p.casilla.x <= o.casilla.width)
				&& 	(o.casilla.x-p.casilla.x >= -o.casilla.width)
				&& 	(o.casilla.y-p.casilla.y <= o.casilla.height)
				&& 	(o.casilla.y-p.casilla.y >= -o.casilla.height);
		}
		
		return false;
	}
	
	protected boolean estaAlineado(Point a, Point b){
		
		if	((chequearCasilla(a))
		&&	(chequearCasilla(b))){
						
			Casilla o = casillas[a.x][a.y];
			Casilla p = casillas[b.x][b.y];
			
			return (o.casilla.x == p.casilla.x)
				|| (o.casilla.y == p.casilla.y);
		}
		
		return false;
	}
}
