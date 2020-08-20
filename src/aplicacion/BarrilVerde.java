package aplicacion;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 * Un barril verde que es lanzado por el DonkeyKong.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public class BarrilVerde extends Barril {

	public ArrayList<Plataforma> plataformas;

	/**
	 * Crea un barril verde que se mueve por todo el escenario.
	 * 
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public BarrilVerde(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
		HashMap<String, Elemento> p = Escenario.elementos;
		plataformas = new ArrayList<Plataforma>();
		for (Entry<String, Elemento> e: p.entrySet()) {
			if (e.getValue() instanceof Plataforma) {
				plataformas.add((Plataforma) e.getValue());
			}
		}
	}
	
	@Override
	/**
	 * Retorna el objeto con el que el barril verde deber�a interactuar en su posici�n actual, teniendo en cuenta la direcci�n en la que se
	 * esta moviendo.
	 * 
	 * @return Objeto a interactuar y direcci�n del movimiento.
	 */
	public SimpleEntry<ObjetoDeEstructura, String> objetoInteractuar() {
		for (Plataforma p: plataformas) {
			String direccion = "";
			if (p.getPendiente() == "+") {
				direccion = "L";
			} else if (p.getPendiente() == "-") {
				direccion = "R";
			}
			if (!direccion.equals("") && p.esInteractuable(cuantificable.getPosx(), cuantificable.getPosy(), cuantificable.getAncho(),
					              cuantificable.getAlto(), direccion)) {
				return new SimpleEntry<ObjetoDeEstructura, String>(p,direccion);
			}
		}
		return null;
	}
	
	@Override
	/**
	 * Se ejecuta cuando un barril verde colisiona con otro Jumpman del juego.
	 */
	public void colisiona() {
		for(Entry<String, Personaje> personaje: Escenario.personajes.entrySet()) {
			String[] words = personaje.getKey().split(":");
			if (words[0].equals("Mario") || words[0].equals("Luigi")) {
				Jumpman player = (Jumpman) personaje.getValue();
				Rectangle rectangleJumpman = new Rectangle(player.getCuantificable().getPosx(), player.getCuantificable().getPosy(),
						                                   player.getCuantificable().getAncho(), player.getCuantificable().getAlto());
				if (rectangleJumpman.contains(cuantificable.getPosx(), cuantificable.getPosy()) ||
					rectangleJumpman.contains(cuantificable.getPosx() + cuantificable.getAlto(), cuantificable.getPosy())) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					destruido = true;
					player.vidas++;
				}
			}
		}
	}
}