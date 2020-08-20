package aplicacion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Personaje del juego.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public abstract class Personaje implements Serializable{
	
	public String nombre;
	public boolean saltando;
	public boolean estaVivo;
	protected Cuantificable cuantificable;
	
	/**
	 * Crea un nuevo personaje.
	 * 
	 * @param nombre del personaje.
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.s
	 */
	public Personaje(String nombre, int x, int y, int ancho, int alto) {
		this.setNombre(nombre);
		cuantificable = new Cuantificable(x,y,ancho,alto);
		saltando = false;
	}
	
	/**
	 * Retorna el cuantificable asociado al personaje.
	 * 
	 * @return cuantificable.
	 */
	public Cuantificable getCuantificable() {
		return cuantificable;
	}
	
	/**
	 * Ejecuta la interacción del personaje con cada uno de los elementos con los que puede interactuar.
	 * 
	 * @param elementosInteractuar lista de elementos con los que puede interactuar.
	 * @param direccion dirección del movimiento del personaje.
	 */
	public abstract void interactue(ArrayList<Elemento> elementosInteractuar, String direccion);
	
	/**
	 * Busca los elementos con los que el personaje puede interactuar según su posición y dirección de movimiento.
	 * 
	 * @param direccion dirección en la que el personaje se esta moviendo.
	 */
	public abstract ArrayList<Elemento> elementosInteractuar(String direccion);
	
	/**
	 * Se ejecuta cuando el personaje se cae.
	 */
	public abstract void caigase();

	/**
	 * Retorna el nombre del personaje.
	 * 
	 * @return nombre del personaje.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Asigna un valor al nombre del personaje.
	 * 
	 * @param nombre valor que se le quiere asignar como nombre al personaje.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}