package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Tiempo {
	
	private		int		minutos;
	private 	int		segundos;
	protected	JLabel	lbl;
	protected	Timer 	reloj;
	
	public Tiempo(){
		
		minutos = 0;
		segundos = 0;
		
		lbl = new JLabel("");
		
		actTiempo();
		
		reloj = new Timer(1000, new ActionListener(){      // Timer 1 seconds
            public void actionPerformed(ActionEvent e) {
            	
               segundos++;
               actTiempo();
               reloj.restart();
            }
        });
	}
	
	private void actTiempo(){
		
		if(segundos>60){
			minutos++;
			segundos = 0;
		}
		
		String min;
		String seg;
		
		if(minutos<10)	min = 0+""+minutos;
		else			min = ""+minutos;
		
		if(segundos<10)	seg = 0+""+segundos;
		else			seg = ""+segundos;
		
		lbl.setText("["+min+":"+seg+"]");
		
	}

}
