package Interface;

import java.awt.Point;

import javax.swing.JButton;

public class Boton {
	
	public static void animacion(JButton b1,Point mov,int vel){
		
		if(b1.getLocation().x != mov.x){
			
			int valor = (-1);
		
			if(b1.getLocation().x < mov.x){
			
				valor = valor*(-1);
			}

			for(int i=0;i<b1.getWidth()*vel;i++){
				
				if(i%vel==0){
					b1.setLocation(b1.getLocation().x+valor,b1.getLocation().y);
				}
				
				b1.update(b1.getGraphics());
			}
		}
		
		if(b1.getLocation().y != mov.y){
			
			int valor = (-1);
			
			if(b1.getLocation().y < mov.y){
				
				valor = valor*(-1);
			}

			for(int i=0;i<b1.getHeight()*vel;i++){
				
				if(i%vel==0){
					b1.setLocation(b1.getLocation().x,b1.getLocation().y+valor);
				}
				
				b1.update(b1.getGraphics());
			}
		}
	}

}
