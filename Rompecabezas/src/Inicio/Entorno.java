package Inicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

public class Entorno extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Board board;
    private InterfaceJuego juego;
    
    
    public final char TECLA_ARRIBA = 38;
    public final char TECLA_ABAJO = 40;
    public final char TECLA_DERECHA = 39;
    public final char TECLA_IZQUIERDA = 37;
    public final char TECLA_ENTER = 10;
    public final char TECLA_CTRL = 17;
    public final char TECLA_ALT = 18;
    public final char TECLA_SHIFT = 16;
    public final char TECLA_ESPACIO = 32;
    public final char TECLA_INSERT = 155;
    public final char TECLA_DELETE = 127;
    public final char TECLA_INICIO = 36;
    public final char TECLA_FIN = 35;

    
    /**
     * Construye un entorno y da comienzo al juego.
     * @param juego El juego que define las reglas del combate
     * @param titulo Un título para la ventana del juego
     * @param ancho El ancho de la ventana
     * @param alto El alto de la ventana
     */
	public Entorno(InterfaceJuego juego, String titulo, int ancho, int alto)
    {
		this.juego = juego;		
		board = new Board(this.juego);
		board.setSize(ancho, alto);
				
        add(board, BorderLayout.CENTER);
        this.pack();        
        Insets ins = this.getInsets();
        setSize(ancho + ins.left + ins.right, alto + ins.bottom + ins.top);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle(titulo);
        setVisible(true);
    }
	
	/**
	 * Inicia el entorno. A partir de este llamado, se ejecutará la función
	 * tick() del Juego cada cierta cantidad de milisegundos.
	 */
	public void iniciar()
	{
		board.iniciar();
	}
	
	/**
	 * @return El ancho de la pantalla
	 */
	public int ancho()
	{
		return board.getWidth();
	}
	
	/**
	 * @return El alto de la pantalla
	 */
	public int alto()
	{
		return board.getHeight();
	}

	/**
	 * Dibuja una imagen en la pantalla en las coordenadas especificadas 
	 * y la rota en el ángulo especificado.
	 * @param imagen La imagen a dibujar
	 * @param x La coordenada x
	 * @param y La coordenada y
	 * @param angulo El ángulo de rotación para la imagen (¡medido en radianes!)
	 */
	public void dibujarImagen(Image imagen, double x, double y, double angulo)
	{
		dibujarImagen(imagen, x, y, angulo, 1.0);
	}
	
	/**
	 * Dibuja una imagen en la pantalla en las coordenadas especificadas 
	 * y la rota en el ángulo especificado.
	 * @param imagen La imagen a dibujar
	 * @param x La coordenada x
	 * @param y La coordenada y
	 * @param angulo El ángulo de rotación para la imagen (¡medido en radianes!)
	 * @param escala El factor de escala a utilizar para agrandar o achicar la imagen
	 */
	public void dibujarImagen(Image imagen, double x, double y, double angulo, double escala)
	{
		dibujarImagenConCentro(imagen, x, y, imagen.getWidth(null)/2, imagen.getHeight(null)/2, angulo, escala);
	}
	
	/**
	 * Dibuja una imagen en la pantalla en las coordenadas especificadas 
	 * y la rota en el ángulo especificado. Se puede especificar en qué 
	 * coordenadas (relativas a la imagen) se desea que esté el centro de 
	 * rotación de la imagen.
	 * @param imagen La imagen a dibujar
	 * @param x La coordenada x
	 * @param y La coordenada y
	 * @param angulo El ángulo de rotación para la imagen (¡medido en radianes!)
	 * @param escala El factor de escala a utilizar para agrandar o achicar la imagen
	 */
	public void dibujarImagenConCentro(Image imagen, double x, double y, double centro_x, double centro_y, double angulo, double escala)
	{
		Graphics2D g2d = board.getG2D();
		
		if( g2d == null )
			return;
		
        AffineTransform transform = AffineTransform.getTranslateInstance(x, y);
        transform.concatenate( AffineTransform.getRotateInstance(angulo) );
        transform.concatenate( AffineTransform.getTranslateInstance(-escala*centro_x, -escala*centro_y) );
        if (escala != 1.0)
        	transform.concatenate( AffineTransform.getScaleInstance(escala, escala) );

        g2d.drawImage(imagen, transform, null);
	}
	/**
	 * Cambia la fuente para las próximas escrituras de texto.
	 * @param font El nombre de la fuente
	 * @param tamano El tamaño para las letras del texto
	 * @param color El color del texto
	 */

	/**
	 * Indica si la tecla especificada está siendo presionada en este momento. 
	 * @param key La tecla a consultar
	 * @return Verdadero si la tecla está siendo presionada y Falso en caso contrario.
	 */
	public boolean estaPresionada(char key)
	{
		if (97 <= key && key <= 122) // la paso a mayusculas
			key -= 32;
		
		boolean[] keys = board.getKeys();
		if( key < 0 || key >= keys.length )
			throw new RuntimeException( "Error! Se consultó si la tecla " + (int) key + " está presionada, pero esa tecla no existe." );
		
		return keys[key];
	}

}
