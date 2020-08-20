package aplicacion;

import java.util.ArrayList;

public class JumpmanMimo extends Jumpman {

	public JumpmanMimo(String nombre, int x, int y, int ancho, int alto) {
		super(nombre, x, y, ancho, alto);
	}
	
	@Override
	/**
	 * Inicia el comportamiento del personaje con perfil temeroso.
	 */
	public void juegue() {
		String direccion = null;
		ArrayList<Elemento> elementosInteractuar;
		while (true) {
			if (nombre.equals("Mario")) {
				if (((Jumpman) Escenario.personajes.get("Luigi:1")).saltando && !this.saltando) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					this.salte();
				} else {
					direccion = ((Jumpman) Escenario.personajes.get("Luigi:1")).direccion;
				}
			} else {
				if (((Jumpman) Escenario.personajes.get("Mario:1")).saltando && !this.saltando) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					this.salte();
				} else {
					direccion = ((Jumpman) Escenario.personajes.get("Mario:1")).direccion;
				}
			}
			if (direccion != null) {
				elementosInteractuar = this.elementosInteractuar(direccion);
				this.interactue(elementosInteractuar, direccion);
			}
		}
	}
}