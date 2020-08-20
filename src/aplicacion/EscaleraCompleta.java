package aplicacion;

/**
 * Escalera completa que permite el transporte sobre ella de personajes y barriles.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public class EscaleraCompleta extends Escalera {

	/**
	 * Crea una escalera completa.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public EscaleraCompleta(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
	
	@Override
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
		if (x >= this.cuantificable.getPosx() && x + ancho <= this.cuantificable.getPosx() + this.cuantificable.getAncho() &&
			(direccion.equals("U") || direccion.equals("D"))) {
			if (( y + alto  > this.cuantificable.getPosy() && direccion.equals("U") &&
				  y <= this.cuantificable.getPosy() + this.cuantificable.getAlto())||
				( y + alto < this.cuantificable.getPosy() + this.cuantificable.getAlto() && direccion.equals("D") && 
				  y + alto >= this.cuantificable.getPosy())) {
				return true;
			}
		} return false;
	}
	
	@Override
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
		int[] posicionEsperada = new int[2];
		posicionEsperada[0] = x;
		if (direccion.equals("U")) {
			posicionEsperada[1] = y - 1;
		}else if (direccion.equals("D")){
			posicionEsperada[1] = y + 1;
		}
		return posicionEsperada;
	}
}