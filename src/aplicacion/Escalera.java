package aplicacion;

/**
 * Escalera que permite el transporte sobre ella de personajes o barriles.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public abstract class Escalera extends ObjetoDeEstructura{
	
	/**
	 * Crea una escalera.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public Escalera(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
}