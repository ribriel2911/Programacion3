package Inicio;

import java.awt.Image;

public class BotonMenu {
	Image imagen;
	double x;
	double y;
	double escala;


	public BotonMenu(String imagen, int x,int y,double escala){
		this.imagen = Herramientas.cargarImagen(imagen);
		this.y = y;
		this.x = x;
		this.escala = escala;
	}
	
	public boolean estaPresionada(){
		int bool = 0;
		if(this.imagen.equals(java.awt.event.MouseEvent.MOUSE_ENTERED)){
			bool++;
		}

		return bool>=1;
	}
	
	public void dibujar(Inicio.Entorno entorno){
		double n = 0;
		entorno.dibujarImagen(imagen, x, y, 0, escala);
		
	}



}