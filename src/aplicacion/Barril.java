package aplicacion;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.awt.Rectangle;

/**
 * Un barril que es lanzado por el DonkeyKong.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public abstract class Barril extends Elemento{
	
	/**
	 * Crea un barril que se mueve por todo el escenario.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public Barril(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
	}
	
	/**
	 * Se ejecuta cuando un barril colisiona con otro Jumpman del juego.
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
					if (!player.tieneMartillo) {
						player.vidas--;
						player.abortarSalto = true;
						player.estaVivo = false;
						player.getCuantificable().reiniciar();
						player.estaVivo = true;
						return;
					}
				}
			}
		}
	}
	
	/**
	 * Mueve el barril por el escenario.
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
			SimpleEntry<ObjetoDeEstructura, String> objetoEstructura = objetoInteractuar();
			if (objetoEstructura == null) {
				try {
					this.cuantificable.changePosY(1);
				} catch (DonkeyPOOBException e) {
				}
			}else {
				int [] nuevaPosicion = objetoEstructura.getKey().interactue(cuantificable.getPosx(), 
									   cuantificable.getPosy(),cuantificable.getAncho(), cuantificable.getAlto(),
									   objetoEstructura.getValue());
				try {
					cuantificable.setPos(nuevaPosicion);
				} catch (DonkeyPOOBException e) {
				}
			}
			colisiona();
		} destruido = true;
		
	}
	
	/**
	 * Retorna el objeto con el que el barril debería interactuar en su posición actual, teniendo en cuenta la dirección en la que se esta
	 * moviendo.
	 * 
	 * @return Objeto a interactuar y dirección del movimiento.
	 */
	public SimpleEntry<ObjetoDeEstructura, String>  objetoInteractuar(){
		return null;
	}
}