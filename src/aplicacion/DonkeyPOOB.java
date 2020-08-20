package aplicacion;

import java.util.ArrayList;

/**
 * DonkeyKong, personaje que juega el papel de villano en el juego, lanza barriles para que el rescatador no cumpla su meta.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class DonkeyPOOB extends Personaje{

	/**
	 * Crea el villano del juego.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public DonkeyPOOB(int x, int y, int ancho, int alto){
		super("DonkeyKong", x, y, ancho, alto);
		new ArrayList<Barril>();
		new ArrayList<ObjetoDeEstructura>();
	}	
	
	/**
	 * Retorna los elementos con los que, según su posición y dirección de movimiento, el personaje puede interactuar.
	 */
	public ArrayList<Elemento> elementosInteractuar(String c){
		return null;
	}
	
	/**
	 * Interactúa con los elementos.
	 * 
	 * @param elementosInteractuar lista de elementos con los que puede interactuar.
	 * @param direccion dirección del movimiento del personaje.
	 */
	public void interactue(ArrayList<Elemento> elementosInteractuar, String direccion) {}
	
	/**
	 * Se ejecuta cuando el personaje se cae.
	 */
	public void caigase() {}
}