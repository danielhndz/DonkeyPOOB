package aplicacion;

import java.awt.Rectangle;

/**
 * Objeto sorpresa que le da puntos, vidas o habilidades al rescatador que lo atrape.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public abstract class ObjetoSorpresa extends Elemento {
	
	/**
	 * Crea un objeto sorpresa.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public ObjetoSorpresa(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
	
	@Override
	/**
	 * Pregunta si un rescatador puede interactuar con el objeto sorpresa.
	 * 
	 * @param x posición en el eje horizontal del rescatador.
	 * @param y posición en el eje vertical del rescatador.
	 * @param ancho longitud en píxeles del ancho de su imagen del rescatador.
	 * @param alto longitud en píxeles del alto de su imagen del rescatador.
	 */
	public boolean esInteractuable(int x, int y, int ancho, int alto, String direccion) {
		Rectangle rectangleJumpman = new Rectangle(x, y, ancho, alto);
		if(rectangleJumpman.contains(cuantificable.getPosx(), cuantificable.getPosy()) || 
		   rectangleJumpman.contains(cuantificable.getPosx() + cuantificable.getAncho(), cuantificable.getPosy())||
		   rectangleJumpman.contains(cuantificable.getPosx(), cuantificable.getPosy() + cuantificable.getAlto()) ||
		   rectangleJumpman.contains(cuantificable.getPosx() + cuantificable.getAncho(), cuantificable.getPosy() + cuantificable.getAlto())) {
			return true;
		}
		return false;
	}
	
}