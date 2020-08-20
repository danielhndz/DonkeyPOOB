package aplicacion;

import java.util.ArrayList;

/**
 * Personaje principal del juego, es controlado por la IA con perfil de temeroso.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class JumpmanTemeroso extends Jumpman {

	/**
	 * Crea un nuevo rescatador con perfil de temeroso, con valores por defecto.
	 * 
	 * @param nombre nombre del personaje.
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public JumpmanTemeroso(String nombre, int x, int y, int ancho, int alto) {
		super(nombre, x, y, ancho, alto);
	}
	
	@Override
	/**
	 * Inicia el comportamiento del personaje con perfil temeroso.
	 */
	public void juegue() {
		String direccion;
		ArrayList<Elemento> elementosInteractuar;
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (vieneBarril()) {
				this.salte();
			}
			direccion = buscarDireccion();
			elementosInteractuar = this.elementosInteractuar(direccion);
			this.interactue(elementosInteractuar, direccion);
		}
	}

}
