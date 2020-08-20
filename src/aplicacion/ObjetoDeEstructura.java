package aplicacion;

/**
 * Objeto que hace parte de la estructura del escenario.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public abstract class ObjetoDeEstructura extends Elemento {
	
	/**
	 * Crea un objeto de tipo estructura.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public ObjetoDeEstructura(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
}