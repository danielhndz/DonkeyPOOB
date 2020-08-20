package aplicacion;

import java.io.Serializable;

/**
 * Cualquier elemento del juego que no sea un personaje.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez 
 * @version 09/12/2019
 */
public abstract class Elemento implements Serializable {
	
	public Cuantificable cuantificable;
	public boolean destruido;
	
	/**
	 * Crea un elemento que deber�a ser visible en el juego.
	 * 
	 * @param posx posici�n en el eje horizontal.
	 * @param posy posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public Elemento(int posx, int posy, int ancho, int alto) {
		cuantificable = new Cuantificable(posx, posy, ancho, alto);
		destruido = false;
	}
	
	/**
	 * Pregunta si un objeto puede interactuar con este.
	 * 
	 * @param x posici�n en el eje horizontal del objeto.
	 * @param y posici�n en el eje vertical del objeto.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 * @param direccion direcci�n en la que se mueve el objeto.
	 * @return true si puede interactuar, false en caso contrario.
	 */
	public boolean esInteractuable(int x, int y, int ancho, int alto, String direccion) {
		return false;
	}
	
	/**
	 * Ejecuta la interacci�n correspodiente entre un objeto y este, luego retorna la posici�n en la que deber�a quedar el objeto.
	 * 
	 * @param x posici�n en el eje horizontal del objeto.
	 * @param y posici�n en el eje vertical del objeto.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 * @param direccion direcci�n en la que se mueve el objeto.
	 * @return posici�n en la que deber�a quedar el objeto.
	 */
	public int[] interactue(int x, int y, int ancho, int alto, String direccion) {
		int[] posicionEsperada = {x, y};
		return posicionEsperada;
	}
	
}