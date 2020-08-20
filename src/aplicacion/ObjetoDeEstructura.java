package aplicacion;

/**
 * Objeto que hace parte de la estructura del escenario.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public abstract class ObjetoDeEstructura extends Elemento {
	
	/**
	 * Crea un objeto de tipo estructura.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public ObjetoDeEstructura(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
}