package aplicacion;

import java.util.ArrayList;

/**
 * Princesa, es el personaje al que el rescatador quiere llegar.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public class Princesa extends Personaje {
	
	/**
	 * Crea una nueva princesa.
	 * 
	 * @param nombre nombre de la princesa.
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public Princesa(String nombre, int x, int y, int ancho, int alto) {
		super(nombre, x, y, ancho, alto);
	}
	
	/**
	 * Busca los elementos con los que la princesa puede interactuar seg�n su posici�n y su direcci�n de movimiento.
	 * 
	 * @return lista de elementos.
	 */
	public ArrayList<Elemento> elementosInteractuar(String c){
		return null;
	}
	
	/**
	 * Se ejecuta cuando un objeto entra en contacto con la princesa.
	 * 
	 * @param elementosInteractuar lista de elementos con los que la princesa podr�a interactuar.
	 * @param direccion direcci�n en la que la princesa se esta moviendo.
	 */
	public void interactue(ArrayList<Elemento> elementosInteractuar, String direccion) {}
	
	/**
	 * Se ejecuta cuando la princesa se cae.
	 */
	public void caigase() {}
	
}