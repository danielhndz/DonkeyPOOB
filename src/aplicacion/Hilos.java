package aplicacion;

import java.io.Serializable;

/**
 * Hilos necesarios para la ejecución del juego.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class Hilos implements Runnable, Serializable {
	
	Escenario juego;
	Jumpman personaje;
	Barril barril;
	String nombre;
	ObjetoSorpresa objeto;
	int limY;
	
	/**
	 * Crea un hilo para un personaje con un nombre específico.
	 * 
	 * @param personaje personaje necesario para la ejecución del hilo.
	 * @param nombre identificador del hilo.
	 */
	public Hilos(Jumpman personaje, String nombre) {
		this.personaje = personaje;
		this.nombre = nombre;
	}
	
	/**
	 * Crea un hilo para un barril con un nombre específico.
	 * 
	 * @param barril barril necesario para la ejecución del hilo.
	 * @param nombre identificador del hilo.
	 */
	public Hilos(Barril barril, String nombre) {
		this.barril = barril;
		this.nombre = nombre;
		this.limY = 630;
	}
	
	/**
	 * Crea un hilo para un escenario con un nombre específico.
	 * 
	 * @param juego escenario en el que se desea ejecutar el hilo.
	 * @param nombre identificador del hilo.
	 */
	public Hilos(Escenario juego, String nombre) {
		this.juego = juego;
		this.nombre = nombre;
	}
	
	/**
	 * Crea un hilo para un objeto sorpresa con un nombre específico.
	 * 
	 * @param objeto
	 * @param nombre
	 */
	public Hilos(ObjetoSorpresa objeto, String nombre) {
		this.objeto = objeto;
		this.nombre = nombre;
	}

	@Override
	/**
	 * Ejecuta un hilo correspondiente de acuerdo a su identificador.
	 */
	public void run() {
		if (nombre.equals("movimientoSalto")) {
			personaje.salte();
		} else if (nombre.equals("movimientoBarril")) {
			barril.muevase(limY);
		} else if (nombre.equals("revisarElementos")) {
			juego.revisarElementos();
		} else if (nombre.equals("movimientoCaida")) {
			personaje.caigase();
		} else if (nombre.equals("agregarObjetosSorpresa")) {
			juego.agregarObjetosSorpresa();
		} else if (nombre.equals("temporizadorObjetoSorpresa")) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			objeto.destruido = true;
		} else if (nombre.equals("temporizadorMartillo")) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			personaje.tieneMartillo = false;
		} else if (nombre.equals("temporizadorHongo")) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			personaje.tieneHongo = false;
		} else if (nombre.equals("inciarJumpmanProtector")) {
			personaje.juegue();
		} else if (nombre.equals("inciarJumpmanTemeroso")) {
			personaje.juegue();
		} else if (nombre.equals("inciarJumpmanMimo")) {
			personaje.juegue();
		}
	}
}