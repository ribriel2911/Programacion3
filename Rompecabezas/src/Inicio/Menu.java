package Inicio;

import java.awt.Image;
import Interface.*;
import javax.sound.sampled.Clip;

public class Menu extends InterfaceJuego{
	
	public Entorno entorno;
	public BotonMenu jugar;
	public BotonMenu selector;
	public Image salir;
	public Image titulo;
	public Image personaje;
	public Image personaje2;
	int controlador = 0;
	int delay_abajo = 0;
	int delay_arriba = 0;
	public Clip sonido;
	public Clip sonidoCom;
	//contadores del sonido
	int contSonido = 0;
	int contSonido1 = 0;
	int contSonidoCom = 0;
	boolean enter;
	
	public Menu()
	{
		CopiadorABin.iniciar();
		entorno = new Entorno(this, "Rompecabeza", 1200, 700);
		this.sonidoCom = Herramientas.cargarSonido("SanAndreas.wav");
		sonidoCom.start();
		this.selector = new BotonMenu("cursor2.png",300,250,1);
		this.jugar = new BotonMenu("jugarGta.jpg",600,250,1);
		this.salir = Herramientas.cargarImagen("finGta.jpg");
		this.titulo = Herramientas.cargarImagen("gta.jpg");
		this.personaje = Herramientas.cargarImagen("trevor.png");
		this.personaje2 = Herramientas.cargarImagen("michael.png");
		enter = false;
		entorno.iniciar();
	}
	
	public void tick()
	{

		jugar.dibujar(entorno);
		selector.dibujar(entorno);
	
		
		entorno.dibujarImagen(this.titulo, 600, 100, 0);
		entorno.dibujarImagen(this.salir, 600, 680, 0);
		entorno.dibujarImagen(this.personaje,150, 400, 0);
		entorno.dibujarImagen(this.personaje2, 1000, 400, 0);
		
		//Chequeamos las teclas presionadas
		
		if(this.entorno.estaPresionada(entorno.TECLA_FIN))
		{
			System.exit(1);
		}
		
		//Chequeamos el selector
		if(entorno.estaPresionada(entorno.TECLA_ENTER)){
			
			
		}
		
		if(entorno.estaPresionada(entorno.TECLA_ENTER)&&!enter)
		{	
			entorno.setVisible(false);
			Aplicacion.main(null);
			sonidoCom.stop();
			enter = true;
			return;
		}
		
		if(!entorno.estaPresionada(entorno.TECLA_ENTER)){
			enter = false;
		}
		
	}
	
	public static void main(String args[])
	{
		new Menu();
	}

}
