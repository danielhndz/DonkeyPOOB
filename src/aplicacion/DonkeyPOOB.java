package aplicacion;

import java.util.ArrayList;

/**
 * DonkeyKong, personaje que juega el papel de villano en el juego, lanza barriles para que el rescatador no cumpla su meta.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public class DonkeyPOOB extends Personaje{

	/**
	 * Crea el villano del juego.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public DonkeyPOOB(int x, int y, int ancho, int alto){
		super("DonkeyKong", x, y, ancho, alto);
		new ArrayList<Barril>();
		new ArrayList<ObjetoDeEstructura>();
	}	
	
	/**
	 * Retorna los elementos con los que, seg�n su posici�n y direcci�n de movimiento, el personaje puede interactuar.
	 */
	public ArrayList<Elemento> elementosInteractuar(String c){
		return null;
	}
	
	/**
	 * Interact�a con los elementos.
	 * 
	 * @param elementosInteractuar lista de elementos con los que puede interactuar.
	 * @param direccion direcci�n del movimiento del personaje.
	 */
	public void interactue(ArrayList<Elemento> elementosInteractuar, String direccion) {}
	
	/**
	 * Se ejecuta cuando el personaje se cae.
	 */
	public void caigase() {}
}