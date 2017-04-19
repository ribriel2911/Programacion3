package Ingenieria;

import java.awt.Image;
import java.awt.Rectangle;

public class Casilla {
	
	protected Rectangle casilla;
	protected Image imagen;

	
	protected Casilla(Rectangle cas, Image imagen){
		
		try{
			casilla = cas;
			this.imagen = imagen;
		}
		catch(NullPointerException e){
			System.out.println("Casilla: Uno de los parametros del constructor no esta inicializado.");
		}
	}
	
	protected void resize(int ancho, int alto,Image im){
		
		imagen = im;
		
		int difX = casilla.width-ancho;
		int difY = casilla.height-alto;
		
		if(difX!=0){
		
			int aux = casilla.x/casilla.width;
			
			casilla.x = aux  * ancho;
			
			casilla.width = ancho;
		}
		if(difY!=0){
			
			int aux = casilla.y/casilla.height;
			
			casilla.y = aux  * alto;
			
			casilla.height = alto;
		}
	}

}
