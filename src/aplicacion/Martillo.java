package aplicacion;

/**
 * Objeto sorpresa que le permite al rescatador que lo atrape destruir barriles por un tiempo limitado.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 *
 * @version 09/12/2019
 */
public class Martillo extends ObjetoSorpresa{

	/**
	 * Crea un martillo.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public Martillo(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
	
	/**
	 * Se ejecuta cuando un rescatador lo atrapa. Le permite destruir barriles por tiempo limitado y retorna la misma posici�n.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 * @param direccion direcci�n en que la se mueve el rescatador.
	 */
	public int[] interactue(int x, int y, int ancho, int alto, String direccion) {
		Jumpman rescatador = (Jumpman) Escenario.personajes.get("Mario:1");
		rescatador.tieneMartillo = true;
		Hilos temporizadorMartillo = new Hilos(rescatador, "temporizadorMartillo");
		new Thread(temporizadorMartillo).start();
		destruido = true;
		int[] posicionEsperada = {x, y};
		return posicionEsperada;
	}

}
