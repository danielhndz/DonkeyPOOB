package aplicacion;

/**
 * Un barril rojo que es lanzado por el DonkeyKong.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class BarrilRojo extends Barril {

	/**
	 * Crea un barril rojo que se mueve por todo el escenario.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public BarrilRojo(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
	
	@Override
	/**
	 * Mueve el barril rojo por el escenario.
	 * 
	 * @param limY Límite inferior del escenario del juego.
	 */
	public void muevase(int limY) {
		while (cuantificable.getPosy() < limY && !destruido) {
			try {
				Thread.sleep(4);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				this.cuantificable.changePosY(1);
			} catch (DonkeyPOOBException e) {
			}
			colisiona();
		}
		destruido = true;
	}
}