package aplicacion;

import java.util.ArrayList;

/**
 * Personaje principal del juego, es controlado por la IA con perfil de protector.
 * 
 * @author Daniel Hern�ndez
 * @author Angi Jim�nez
 * @version 09/12/2019
 */
public class JumpmanProtector extends Jumpman {

	/**
	 * Crea un nuevo rescatador con perfil de protector, con valores por defecto.
	 * 
	 * @param nombre nombre del personaje.
	 * @param x posici�n en el eje horizontal.
	 * @param y posici�n en el eje vertical.
	 * @param ancho longitud en p�xeles del ancho de su imagen.
	 * @param alto longitud en p�xeles del alto de su imagen.
	 */
	public JumpmanProtector(String nombre, int x, int y, int ancho, int alto) {
		super(nombre, x, y, ancho, alto);
	}

	@Override
	/**
	 * Inicia el comportamiento del personaje con perfil protector.
	 */
	public void juegue() {
		String direccion;
		ArrayList<Elemento> elementosInteractuar;
		while (true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (vieneBarril()) {
				int decision = (int) (Math.random() * 5);
				if (decision == 0) {
					this.salte();
				}
			}
			direccion = buscarDireccion();
			elementosInteractuar = this.elementosInteractuar(direccion);
			this.interactue(elementosInteractuar, direccion);
		}
	}
	
}
