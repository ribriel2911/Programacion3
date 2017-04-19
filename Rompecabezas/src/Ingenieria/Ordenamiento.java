package Ingenieria;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Ordenamiento {	
	
	public static ArrayList<Point> posicionar(Rectangle r) {

		int columnas	=	r.x;
		int filas		=	r.y;
		int ancho		=	r.width;
		int alto		=	r.height;
		
		int tam = columnas*filas;
		
		ArrayList<Point> posiciones = new ArrayList<Point>();
		
		int x = 0;
		int y = 0;
		
		int cont = 0;
		
		for(int i=0;i<tam;i++){
			
			posiciones.add(new Point(x,y));
			
			x += ancho;
						
			cont++;
			
			if(cont>columnas-1){
				
				y += alto;
				x = 0;
				cont = 0;
			}
		
		}
		return posiciones;
	}
	
	public static <T> ArrayList<T> desordenar(ArrayList<T> ini){
		
		ArrayList<T> ret = new ArrayList<T>();
		
		if(ini.size()<=0) return ret;
		
		Random azar = new Random();
		
		int corte = azar.nextInt(ini.size());
		
		ret.add(ini.remove(corte));
		
		ArrayList<T> nuevo = desordenar(ini);
		
		ret.addAll(nuevo);
		
		return ret;
		
	}
	
}
