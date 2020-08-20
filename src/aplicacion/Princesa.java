package aplicacion;

import java.util.ArrayList;

/**
 * Princesa, es el personaje al que el rescatador quiere llegar.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class Princesa extends Personaje {
	
	/**
	 * Crea una nueva princesa.
	 * 
	 * @param nombre nombre de la princesa.
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public Princesa(String nombre, int x, int y, int ancho, int alto) {
		super(nombre, x, y, ancho, alto);
	}
	
	/**
	 * Busca los elementos con los que la princesa puede interactuar según su posición y su dirección de movimiento.
	 * 
	 * @return lista de elementos.
	 */
	public ArrayList<Elemento> elementosInteractuar(String c){
		return null;
	}
	
	/**
	 * Se ejecuta cuando un objeto entra en contacto con la princesa.
	 * 
	 * @param elementosInteractuar lista de elementos con los que la princesa podría interactuar.
	 * @param direccion dirección en la que la princesa se esta moviendo.
	 */
	public void interactue(ArrayList<Elemento> elementosInteractuar, String direccion) {}
	
	/**
	 * Se ejecuta cuando la princesa se cae.
	 */
	public void caigase() {}
	
}