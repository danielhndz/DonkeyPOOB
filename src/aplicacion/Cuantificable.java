package aplicacion;

/**
 * Almacena la posici�n y las dimensiones de cualquier objeto que pertenezca al juego y deba ser mostrado.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public class Cuantificable {
	
	private final int xInicial;
	private final int yInicial;
	private int posx;
	private int posy;
	private int ancho;
	private int alto;
	
	/**
	 * Crea un cuantificable que almacena los datos correspondientes a los gr�ficos del objeto en cuesti�n.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public Cuantificable(int x, int y, int ancho, int alto) {
		xInicial = x;
		yInicial = y;
		posx = x;
		posy = y;
		this.ancho = ancho;
		this.alto= alto;
	}
	
	/**
	 * Retorna la posici�n en el eje horizontal.
	 * 
	 * @return posici�n en el eje horizontal.
	 */
	public int getPosx() {
		return posx;
	}
	
	/**
	 * Retorna la posici�n en el eje vertical.
	 * 
	 * @return posici�n en el eje vertical.
	 */
	public int getPosy() {
		return posy;
	}
	
	/**
	 * Retorna la longitud en pixeles del ancho de su imagen.
	 * 
	 * @return longitud en pixeles del ancho de su imagen.
	 */
	public int getAncho() {
		return ancho;
	}
	
	/**
	 * Retorna la longitud en p�xeles del alto de su imagen.
	 * 
	 * @return longitud en p�exeles del alto de su imagen.
	 */
	public int getAlto() {
		return alto;
	}
	
	/**
	 * Cambia la posici�n en el eje horizontal.
	 * 
	 * @param dx cambio en p�xeles de la posici�n en el eje horizontal.
	 * @throws DonkeyPOOBException 
	 */
	public void changePosX(int dx) throws DonkeyPOOBException {
		posx += dx;
		if (posx+dx < 0 || posx+dx > 1000) {
			throw new DonkeyPOOBException(DonkeyPOOBException.POSICION_FUERA_DE_LIMITES);
		}
	}
	
	/**
	 * Cambia la posici�n en el eje vertical.
	 * 
	 * @param dy cambio en p�xeles de la posici�n en el eje vertical.
	 * @throws DonkeyPOOBException 
	 */
	public void changePosY(int dy) throws DonkeyPOOBException {
		posy += dy;
		if (posx+dy < 0 || posx+dy > 680) {
			throw new DonkeyPOOBException(DonkeyPOOBException.POSICION_FUERA_DE_LIMITES);
		}
	}
	
	/**
	 * Asigna una nueva posici�n, tanto en el eje horizontal como en el vertical.
	 * 
	 * @param nuevaPosicion posici�n que se quiere asignar.
	 * @throws DonkeyPOOBException 
	 */
	public void setPos(int[] nuevaPosicion) throws DonkeyPOOBException {
		if (nuevaPosicion[0] < 0 || nuevaPosicion[0] > 1000 || nuevaPosicion[1] <0 || nuevaPosicion[1] > 680 ) {
			throw new DonkeyPOOBException(DonkeyPOOBException.POSICION_FUERA_DE_LIMITES);
		}
		posx = nuevaPosicion[0];
		posy = nuevaPosicion[1];
	}
	
	/**
	 * Devuelve el objeto a su posici�n inicial al comenzar el juego.
	 */
	public void reiniciar() {
		posx = xInicial;
		posy = yInicial;
	}
}