package aplicacion;

import java.util.Map.Entry;

/**
 * Objeto sorpresa que brinda puntos o vidas al personaje que lo atrape.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class Bonus extends ObjetoSorpresa {
	
	private int vidas;
	private int puntos;

	/**
	 * Crea un objeto sorpresa.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param vidas vidas adicionales que otorga.
	 * @param puntos puntos adicionales que otorga.
	 */
	public Bonus(int x, int y, int ancho, int alto, int vidas, int puntos) {
		super(x, y, ancho, alto);
		this.vidas = vidas;
		this.puntos = puntos;
	}
	
	@Override
	/**
	 * Se ejecuta cuando un rescatador atrapa el objeto sorpresa.
	 * 
	 * @param x posición en el eje horizontal del rescatador.
	 * @param y posición en el eje vertical del rescatador.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param direccion dirección en la que se esta moviendo el rescatador.
	 */
	public int[] interactue(int x, int y, int ancho, int alto, String direccion) {
		for(Entry<String, Personaje> personaje: Escenario.personajes.entrySet()) {
			String[] words = personaje.getKey().split(":");
			if (words[0].equals("Mario") || words[0].equals("Luigi") && 
				(personaje.getValue().getCuantificable().getPosx() == x && personaje.getValue().getCuantificable().getPosy() == y)) {
				Jumpman player = (Jumpman) personaje.getValue();
				player.vidas += vidas;
				player.puntaje += puntos;
				destruido = true;
			}
		}
		return new int[] {x,y};
	}
	
}