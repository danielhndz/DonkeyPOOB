package aplicacion;

/**
 * Un hongo que invierte los controles del personaje que lo atrape.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class Hongo extends ObjetoSorpresa {
	
	/**
	 * Crea un hongo en el escenario.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public Hongo(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
	
	/**
	 * Se ejecuta cuando un rescatador atrapa el hongo, luego retorna la posición en la que debería quedar el rescatador.
	 * 
	 * @param x posición en el eje horizontal del rescatador.
	 * @param y posición en el eje vertical del rescatador.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param direccion dirección del movimiento del rescatador.
	 */
	public int[] interactue(int x, int y, int ancho, int alto, String direccion) {
		Jumpman rescatador = (Jumpman) Escenario.personajes.get("Mario:1");
		rescatador.tieneHongo = true;
		Hilos temporizadorHongo = new Hilos(rescatador, "temporizadorHongo");
		new Thread(temporizadorHongo).start();
		destruido = true;
		int[] posicionEsperada = {x, y};
		return posicionEsperada;
	}
}