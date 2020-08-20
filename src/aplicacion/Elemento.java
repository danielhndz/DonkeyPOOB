package aplicacion;

import java.io.Serializable;

/**
 * Cualquier elemento del juego que no sea un personaje.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez 
 * @version 09/12/2019
 */
public abstract class Elemento implements Serializable {
	
	public Cuantificable cuantificable;
	public boolean destruido;
	
	/**
	 * Crea un elemento que debería ser visible en el juego.
	 * 
	 * @param posx posición en el eje horizontal.
	 * @param posy posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public Elemento(int posx, int posy, int ancho, int alto) {
		cuantificable = new Cuantificable(posx, posy, ancho, alto);
		destruido = false;
	}
	
	/**
	 * Pregunta si un objeto puede interactuar con este.
	 * 
	 * @param x posición en el eje horizontal del objeto.
	 * @param y posición en el eje vertical del objeto.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param direccion dirección en la que se mueve el objeto.
	 * @return true si puede interactuar, false en caso contrario.
	 */
	public boolean esInteractuable(int x, int y, int ancho, int alto, String direccion) {
		return false;
	}
	
	/**
	 * Ejecuta la interacción correspodiente entre un objeto y este, luego retorna la posición en la que debería quedar el objeto.
	 * 
	 * @param x posición en el eje horizontal del objeto.
	 * @param y posición en el eje vertical del objeto.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param direccion dirección en la que se mueve el objeto.
	 * @return posición en la que debería quedar el objeto.
	 */
	public int[] interactue(int x, int y, int ancho, int alto, String direccion) {
		int[] posicionEsperada = {x, y};
		return posicionEsperada;
	}
	
}