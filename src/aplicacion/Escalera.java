package aplicacion;

/**
 * Escalera que permite el transporte sobre ella de personajes o barriles.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public abstract class Escalera extends ObjetoDeEstructura{
	
	/**
	 * Crea una escalera.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public Escalera(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
}