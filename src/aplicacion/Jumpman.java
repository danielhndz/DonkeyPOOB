package aplicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Personaje principal del juego, puede ser controlado por un usuario.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class Jumpman extends Personaje {
	
	protected HashMap<String, Elemento> elementos;
	public int puntaje;
	public int vidas;
	public boolean tieneHongo;
	public boolean tieneMartillo;
	public boolean abortarSalto;
	public String direccion;
	
	/**
	 * Crea un nuevo rescatador con valores por defecto.
	 * 
	 * @param nombre nombre del personaje.
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public Jumpman(String nombre, int x, int y, int ancho, int alto) {
		super(nombre, x, y, ancho, alto);
		elementos = Escenario.elementos;
		puntaje = 0;
		vidas = 3;
		tieneMartillo = false;
		tieneHongo = false;
		estaVivo = true;
	}
	
	/**
	 * Ejecuta la interacción del personaje con los elementos con los que puede según su posición y dirección de movimiento.
	 * 
	 * @param elementosInteractuar lista de elementos con los que el personaje puede interactuar.
	 * @param direccion dirección en la que el personaje se esta moviendo.
	 */
	public void interactue(ArrayList<Elemento> elementosInteractuar, String direccion) {
		this.direccion = direccion;
		if (tieneHongo) {
			if (direccion.equals("U")) {
				direccion = ("D");
			} else if (direccion.equals("D")) {
				direccion = ("U");
			} else if (direccion.equals("L")) {
				direccion = ("R");
			} else if (direccion.equals("R")) {
				direccion = ("L");
			}
		}
		
		for (Elemento e: elementosInteractuar) {
			int[] nuevaPosicion = e.interactue(cuantificable.getPosx(), cuantificable.getPosy(), cuantificable.getAncho(), 
					   cuantificable.getAlto(), direccion);
			try {
				this.getCuantificable().setPos(nuevaPosicion);
			} catch (DonkeyPOOBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
		this.direccion = null;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Retorna los elementos con los que el personaje puede interactuar según su posición y dirección de movimiento.
	 * 
	 * @param direccion dirección en la que el personaje se esta moviendo.
	 * @return lista de elementos con los que el personaje puede interactuar.
	 */
	public ArrayList<Elemento> elementosInteractuar(String direccion) {
		ArrayList<Elemento> elementosInteractuar = new ArrayList<Elemento>();
		HashMap<String, Elemento> elementosCopia = (HashMap<String, Elemento>) elementos.clone();
		for (Elemento e: elementosCopia.values()) {
			if (!e.destruido && e.esInteractuable(cuantificable.getPosx(), cuantificable.getPosy(), cuantificable.getAncho(), 
					   cuantificable.getAlto(), direccion)) {
				elementosInteractuar.add(e);
			}
		} return elementosInteractuar;
	}
	
	@Override
	/**
	 * Se ejecuta cuando el personaje se cae.
	 */
	public void caigase() {
		while (elementosInteractuar("L").size() == 0 || elementosInteractuar("R").size() == 0) {
			try {
				cuantificable.changePosY(1);
			} catch (DonkeyPOOBException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Se ejecuta cuando el personaje salta.
	 */
	public void salte() {
		saltando = true;
		abortarSalto = false;
		for (int y = 0; y < this.cuantificable.getAlto(); y++ ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (abortarSalto) {
				saltando = false;
				return;
			}
			try {
				this.cuantificable.changePosY(-1);
			} catch (DonkeyPOOBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int y = 0; y < this.cuantificable.getAlto(); y++ ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (abortarSalto) {
				saltando = false;
				return;
			}
			try {
				this.cuantificable.changePosY(1);
			} catch (DonkeyPOOBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		saltando = false;
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * Busca la escalera más cercana al personaje dada una lista de escaleras y su plataforma correspondiente.
	 * 
	 * @param escalerasCompletas lista de escaleras completas.
	 * @param plataforma plataforma a la que pertenecen las escaleras.
	 * @return escalera completa más cercana al personaje.
	 */
	public EscaleraCompleta escaleraMasCercana(ArrayList<EscaleraCompleta> escalerasCompletas, Plataforma plataforma) {
		int distancia = 0;
		EscaleraCompleta escalera = null;
		for (EscaleraCompleta e: (ArrayList<EscaleraCompleta>) escalerasCompletas.clone()) {
			if (escalera == null) {
				escalera = e;
				distancia = Math.abs(this.getCuantificable().getPosx() - e.cuantificable.getPosx());
			} else {
				if (distancia > Math.abs(this.getCuantificable().getPosx() - e.cuantificable.getPosx())) {
					distancia = Math.abs(this.getCuantificable().getPosx() - e.cuantificable.getPosx());
					escalera = e;
				}
			}
		}
		return escalera;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Pregunta si viene un barril hacia la posición actual del personaje.
	 * 
	 * @return true si viene un barril, false en caso contrario.
	 */
	public boolean vieneBarril() {
		Plataforma plataformaRescatador = Escenario.demePlataforma(this);
		if (plataformaRescatador != null) {
			for (Entry<String, Elemento> e: ((HashMap<String, Elemento>) Escenario.elementos.clone()).entrySet()) {
				String[] tipo = e.getKey().split(":");
				if ((tipo[0].equals("BarrilAmarillo") || tipo[0].equals("BarrilAzul")) && !e.getValue().destruido) {
					Plataforma plataformaBarril = Escenario.demePlataforma((Barril) e.getValue());
					if (plataformaRescatador == plataformaBarril) {
						int xRescatador = this.getCuantificable().getPosx();
						int anchoRescatador = this.getCuantificable().getAncho();
						int xBarril = e.getValue().cuantificable.getPosx();
						int anchoBarril = e.getValue().cuantificable.getAncho();
						if (plataformaBarril.getPendiente().equals("+")) {
							int distancia = Math.abs(xRescatador + anchoRescatador - xBarril);
							if (distancia <= 70) {
								return true;
							}
						} else if (plataformaBarril.getPendiente().equals("-")) {
							int distancia = Math.abs(xRescatador - xBarril - anchoBarril);
							if (distancia <= 70) {
								return true;
							}
						}
					}
				}
			}
		} return false;
	}
	
	/**
	 * Busca la dirección en la que esta la escalera más cercana al personaje.
	 * 
	 * @return dirección de la escalera más cercana.
	 */
	public String buscarDireccion() {
		Plataforma plataforma = Escenario.demePlataforma(this);
		if (plataforma != null) {
			ArrayList<EscaleraCompleta> escalerasCompletas = Escenario.busqueEscalerasCompletas(plataforma);
			Escalera escalera = escalerasCompletas.get(0);
			if (escalerasCompletas.size() > 1) {
				escalera = escaleraMasCercana(escalerasCompletas, plataforma);
			}
			if (this.getCuantificable().getPosx() < escalera.cuantificable.getPosx()) {
				if (this.tieneHongo) {
					return "L";
				} else {
					return "R";
				}
			} else if (this.getCuantificable().getPosx() > escalera.cuantificable.getPosx()) {
				if (this.tieneHongo) {
					return "R";
				} else {
					return "L";
				}
			} else {
				if (this.tieneHongo) {
					return "D";
				} else {
					return "U";
				}
			}
		}
		if (this.tieneHongo) {
			return "D";
		} else {
			return "U";	
		}
	}
	
	/**
	 * Inicia el comportamiento de un perfil específico.
	 */
	public  void juegue() {}
	
}