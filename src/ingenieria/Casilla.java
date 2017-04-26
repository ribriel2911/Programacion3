package ingenieria;

import java.awt.Point;

public class Casilla {
	
	Point _posicion;
	boolean _esNegra;
	Ficha _ficha;
	
	public Casilla(int x, int y, boolean color){
		
		_posicion = new Point(x,y);
		_esNegra = color;
	}
	
	public void asignarFicha(Ficha f){
		
		_ficha = f;
	}
}
