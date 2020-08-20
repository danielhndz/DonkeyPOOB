package aplicacion;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Un barril azul que es lanzado por el DonkeyKong.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public  class BarrilAzul extends Barril{
	
	ArrayList<Plataforma> plataformas;
	ArrayList<Escalera> escaleras;

	/**
	 * Crea un barril azul que se mueve por todo el escenario.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public BarrilAzul(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
		HashMap<String, Elemento> p = Escenario.elementos;
		plataformas = new ArrayList<Plataforma>();
		escaleras = new ArrayList<Escalera>();
		for (Entry<String, Elemento> e: p.entrySet()) {
			if (e.getValue() instanceof Plataforma) {
				plataformas.add((Plataforma) e.getValue());
			} else if (e.getValue() instanceof Escalera) {
				escaleras.add((Escalera) e.getValue());
			}
		}
	}
	
	@Override
	/**
	 * Retorna el objeto con el que el barril azul debería interactuar en su posición actual, teniendo en cuenta la dirección en la que
	 * se esta moviendo.
	 * 
	 * @return Objeto a interactuar y dirección del movimiento.
	 */
	public SimpleEntry<ObjetoDeEstructura, String> objetoInteractuar() {
		for (Escalera e: escaleras) {
			if (e.esInteractuable(cuantificable.getPosx(), cuantificable.getPosy(), cuantificable.getAncho(),
					              cuantificable.getAlto(), "D")) {
				return new SimpleEntry<ObjetoDeEstructura, String>(e,"D");
			}
		}
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

}