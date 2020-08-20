package aplicacion;

/**
 * Soga que permite al rescatador subir a una plataforma, solo permite subir y un solo uso.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public class Soga extends ObjetoSorpresa{
	
	public boolean usado;

	/**
	 * Crea una nueva soga.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public Soga(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
		usado = false;
	}
	

	@Override
	/**
	 * Pregunta si la soga puede interactuar con un rescatador dada su posici�n y direcci�n de movimiento.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 * @param direccion direcci�n en la que se mueve el rescatador.
	 */
	public boolean esInteractuable(int x, int y, int ancho, int alto, String direccion) {
		if (x < this.cuantificable.getPosx() && x + ancho > this.cuantificable.getPosx() + this.cuantificable.getAncho() &&
			(direccion.equals("U"))) {
			if ((y + alto == cuantificable.getPosy()) && usado) {
			 	destruido = true;
				return false;
			}else if ( y + alto  > this.cuantificable.getPosy() && direccion.equals("U") && 
						y  <= this.cuantificable.getPosy() + this.cuantificable.getAlto()) {
				return true;
			}
		} return false;
	}
	
	@Override
	/**
	 * Se ejecuta cuando un rescatador quiere subir por la soga, retorna la posici�n adecuada en la que deber�a quedar el rescatador.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 * @param direccion direcci�n en la que se mueve el rescatador.
	 * @return posici�n en la que deber�a quedar el rescatador.
	 */
	public int[] interactue(int x, int y, int ancho, int alto, String direccion) {
		int[] posicionEsperada = new int[2];
		posicionEsperada[0] = x;
		if (direccion.equals("U")) {
			posicionEsperada[1] = y - 1;
		}else if (direccion.equals("D")){
			posicionEsperada[1] = y + 1;
		}
		usado = true;
		return posicionEsperada;
	}

}