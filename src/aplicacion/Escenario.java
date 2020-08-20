package aplicacion;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

import persistencia.DonkeyPOOBDAO;

/**
 * Escenario en el que se desarrolla el juego DonkeyPOOB.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019 
 */
public class Escenario implements Runnable, Serializable {
	protected static HashMap<String, Elemento> elementos;
	protected static HashMap<String, Personaje> personajes;
	protected int nivel;
	private String modoDeJuego;

	/**
	 * Crea un nuevo escenario.
	 * 
	 * @param nivel nivel en el que el juego se esta desarrollando.
	 * @param modoDeJuego modo de juego escogido por el usuario para el escenario actual.
	 * @param jugador1 nombre y perfil del personaje que tomara el rol de jugador 1.
	 * @param jugador2 nombre y perfil del personaje que tomara el rol de jugador 2. 
	 * @throws DonkeyPOOBException 
	 */
	public Escenario(int nivel, String modoDeJuego, String jugador1, String jugador2) throws DonkeyPOOBException {
		elementos = new HashMap<String, Elemento>();
		personajes = new HashMap<String, Personaje>();
		this.nivel = nivel;
		this.modoDeJuego = modoDeJuego;
		agregarPlataformas();
		agregarEscaleras();
		agregarPersonajes(jugador1, jugador2);
	}

	/**
	 * Agrega escaleras al escenario.
	 */
	private void agregarEscaleras() {
		elementos.put("EscaleraCompleta:1", new EscaleraCompleta(210, 511, 40, 105));
		elementos.put("EscaleraCompleta:2", new EscaleraCompleta(580, 400, 40, 107));
		elementos.put("EscaleraCompleta:3", new EscaleraCompleta(150, 282, 40, 115));
		elementos.put("EscaleraCompleta:4", new EscaleraCompleta(650, 171, 40, 105));
		elementos.put("EscaleraCompleta:5", new EscaleraCompleta(450, 68, 40, 101));
		elementos.put("EscaleraCompleta:6", new EscaleraCompleta(400, -26, 40, 103));
	}

	/**
	 * Agrega plataformas al escenario.
	 */
	private void agregarPlataformas() {
		elementos.put("PlataformaRectaLarga:1", new Plataforma(0, 610, 800, 20));
		elementos.put("PlataformaPositiva:1", new Plataforma(72, 500, 698, 33, "+"));
		elementos.put("PlataformaNegativa:2", new Plataforma(15, 390, 698, 33, "-"));
		elementos.put("PlataformaPositiva:2", new Plataforma(72, 270, 698, 33, "+"));
		elementos.put("PlataformaNegativa:1", new Plataforma(15, 160, 698, 33, "-"));
		elementos.put("PlataformaRectaCorta:1", new Plataforma(400, 70, 245, 20));
	}

	/**
	 * Agrega personajes al escenario.
	 * 
	 * @param jugador1 nombre y perfil del personaje que tomara el rol de jugador 1.
	 * @param jugador2 nombre y perfil del personaje que tomara el rol de jugador 2.
	 * @throws DonkeyPOOBException 
	 */
	private void agregarPersonajes(String jugador1, String jugador2) throws DonkeyPOOBException {
		if (modoDeJuego.equals("Un jugador") && jugador2 != null || !modoDeJuego.equals("Un jugador") && jugador2 == null) {
			throw new DonkeyPOOBException(DonkeyPOOBException.PERSONAJES_NO_CORRESPONDEN_A_MODO_DE_JUEGO);
		}
		personajes.put("DonkeyKong:1", new DonkeyPOOB(150, 100, 90, 63));
		personajes.put("Princesa:1", new Princesa("Angi", 480, 30, 30, 50));
		if(modoDeJuego.equals("Maquina contra maquina")) {
			String[] j1 = jugador1.split("-");
			String[] j2 = jugador2.split("-");
			if(j1[0].equals("Luigi")) {
				if(j1[1].equals("Mimo")) {
					personajes.put("Luigi:1", new JumpmanMimo("Luigi", 730, 570, 30, 40));
					Hilos jumpmanMimo = new Hilos((Jumpman) personajes.get("Luigi:1"), "iniciarJumpmanMimo");
					new Thread(jumpmanMimo).start();
				}else if(j1[1].equals("Protector")) {
					personajes.put("Luigi:1", new JumpmanProtector("Luigi", 730, 570, 30, 40));
					Hilos jumpmanProtector = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanProtector");
					new Thread(jumpmanProtector).start();
				}else if(j1[1].equals("Temeroso")) {
					personajes.put("Luigi:1", new JumpmanTemeroso("Luigi", 730, 570, 30, 40));
					Hilos jumpmanTemeroso = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanTemeroso");
					new Thread(jumpmanTemeroso).start();
				}
			} else if(j1[0].equals("Mario")) {
				if(j1[1].equals("Mimo")) {
					personajes.put("Mario:1", new JumpmanMimo("Mario", 730, 570, 30, 40));
					Hilos jumpmanMimo = new Hilos((Jumpman) personajes.get("Mario:1"), "iniciarJumpmanMimo");
					new Thread(jumpmanMimo).start();
				}else if(j1[1].equals("Protector")) {
					personajes.put("Mario:1", new JumpmanProtector("Mario", 730, 570, 30, 40));
					Hilos jumpmanProtector = new Hilos((Jumpman) personajes.get("Mario:1"), "inciarJumpmanProtector");
					new Thread(jumpmanProtector).start();
				}else if(j1[1].equals("Temeroso")) {
					personajes.put("Mario:1", new JumpmanTemeroso("Mario", 730, 570, 30, 40));
					Hilos jumpmanTemeroso = new Hilos((Jumpman) personajes.get("Mario:1"), "inciarJumpmanTemeroso");
					new Thread(jumpmanTemeroso).start();
				}
			}
			if(j2[0].equals("Luigi")) {
				if(j2[1].equals("Mimo")) {
					personajes.put("Luigi:1", new JumpmanMimo("Luigi", 25, 570, 30, 40));
					Hilos jumpmanMimo = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanMimo");
					new Thread(jumpmanMimo).start();
				}else if(j2[1].equals("Protector")) {
					personajes.put("Luigi:1", new JumpmanProtector("Luigi", 25, 570, 30, 40));
					Hilos jumpmanProtector = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanProtector");
					new Thread(jumpmanProtector).start();
				}else if(j2[1].equals("Temeroso")) {
					personajes.put("Luigi:1", new JumpmanTemeroso("Luigi", 25, 570, 30, 40));
					Hilos jumpmanTemeroso = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanTemeroso");
					new Thread(jumpmanTemeroso).start();
				}
			} else if(j2[0].equals("Mario")) {
				if(j1[1].equals("Mimo")) {
					personajes.put("Mario:1", new JumpmanMimo("Mario", 25, 570, 30, 40));
					Hilos jumpmanMimo = new Hilos((Jumpman) personajes.get("Mario:1"), "inciarJumpmanMimo");
					new Thread(jumpmanMimo).start();
				}else if(j2[1].equals("Protector")) {
					personajes.put("Mario:1", new JumpmanProtector("Mario", 25, 570, 30, 40));
					Hilos jumpmanProtector = new Hilos((Jumpman) personajes.get("Mario:1"), "inciarJumpmanProtector");
					new Thread(jumpmanProtector).start();
				}else if(j2[1].equals("Temeroso")) {
					personajes.put("Mario:1", new JumpmanTemeroso("Mario", 25, 570, 30, 40));
					Hilos jumpmanTemeroso = new Hilos((Jumpman) personajes.get("Mario:1"), "inciarJumpmanTemeroso");
					new Thread(jumpmanTemeroso).start();
				}
			}
		} else if (modoDeJuego.equals("Un jugador")) {
			if (jugador1.equals("Mario")) {
				personajes.put("Mario:1", new Jumpman("Mario", 730, 570, 30, 40));
			}else {
				personajes.put("Luigi:1", new Jumpman("Luigi", 730, 570, 30, 40));
			}
		} else if (modoDeJuego.equals("Jugador contra maquina")) {
			System.out.println(jugador2);
			if (jugador1.equals("Mario")) {
				personajes.put("Mario:1", new Jumpman("Mario", 730, 570, 30, 40));
			}else {
				personajes.put("Luigi:1", new Jumpman("Luigi", 730, 570, 30, 40));
			}
			String[] j2 = jugador2.split("-");
			if(j2[0].equals("Luigi")) {
				if(j2[1].equals("Mimo")) {
					personajes.put("Luigi:1", new JumpmanMimo("Luigi", 25, 570, 30, 40));
					Hilos jumpmanMimo = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanMimo");
					new Thread(jumpmanMimo).start();
				}else if(j2[1].equals("Protector")) {
					personajes.put("Luigi:1", new JumpmanProtector("Luigi", 25, 570, 30, 40));
					Hilos jumpmanProtector = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanProtector");
					new Thread(jumpmanProtector).start();
				}else if(j2[1].equals("Temeroso")) {
					personajes.put("Luigi:1", new JumpmanTemeroso("Luigi", 25, 570, 30, 40));
					Hilos jumpmanTemeroso = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanTemeroso");
					new Thread(jumpmanTemeroso).start();
				}
			} else if(j2[0].equals("Mario")) {
				if(j2[1].equals("Mimo")) {
					personajes.put("Mario:1", new JumpmanMimo("Mario", 25, 570, 30, 40));
					Hilos jumpmanMimo = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanMimo");
					new Thread(jumpmanMimo).start();
				}else if(j2[1].equals("Protector")) {
					personajes.put("Mario:1", new JumpmanProtector("Mario", 25, 570, 30, 40));
					Hilos jumpmanProtector = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanProtector");
					new Thread(jumpmanProtector).start();
				}else if(j2[1].equals("Temeroso")) {
					personajes.put("Mario:1", new JumpmanTemeroso("Mario", 25, 570, 30, 40));
					Hilos jumpmanTemeroso = new Hilos((Jumpman) personajes.get("Luigi:1"), "inciarJumpmanTemeroso");
					new Thread(jumpmanTemeroso).start();
				}
			}
		}else if (modoDeJuego.equals("Dos jugadores")) {
			if (jugador1.equals("Mario")) {
				personajes.put("Mario:1", new Jumpman("Mario", 730, 570, 30, 40));
			}else {
				personajes.put("Luigi:1", new Jumpman("Luigi", 730, 570, 30, 40));
			}
			if (jugador2.equals("Mario")) {
				personajes.put("Mario:1", new Jumpman("Mario", 25, 570, 30, 40));
			}else {
				personajes.put("Luigi:1", new Jumpman("Luigi", 25, 570, 30, 40));
			}
		}
	}

	/**
	 * Retorna un mapa con los elementos del juego, la llave es un identificador y el valor es la referencia a un
	 * objeto de la clase Elemento.
	 * 
	 * @return mapa de los elementos del juego.
	 */
	public HashMap<String, Elemento> getElementos() {
		return elementos;
	}

	/**
	 * Retorna un mapa con los personajes del juego, la llave es un identificador y el valor es la referencia a un
	 * objeto de la clase Personaje.
	 * 
	 * @return mapa de los personajes del juego.
	 */
	public HashMap<String, Personaje> getPersonajes() {
		return personajes;
	}
	
	/**
	 * Agrega un barril lanzado por el DonkeyKong al escenario, este método se duerme durante 5 segundos.
	 */
	public void agregarBarril() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String[] tiposBarriles = {"BarrilAmarillo", "BarrilAzul", "BarrilRojo", "BarrilVerde"};
		int numRandom = (int) (Math.random() * 4);
		int cont = 1;
		for (HashMap.Entry<String, Elemento> b: elementos.entrySet()) {
			String[] tipo = b.getKey().split(":");
			if (tipo[0].equals(tiposBarriles[numRandom])) {
				cont ++;
			}
		}
		if (tiposBarriles[numRandom].equals("BarrilAmarillo")) {
			elementos.put(tiposBarriles[numRandom] + ":" + String.valueOf(cont),
					      new BarrilAmarillo(20, 100, 25, 25));
		} else if (tiposBarriles[numRandom].equals("BarrilAzul")) {
			elementos.put(tiposBarriles[numRandom] + ":" + String.valueOf(cont),
				          new BarrilAzul(20, 100, 25, 25));
		} else if (tiposBarriles[numRandom].equals("BarrilRojo")) {
			elementos.put(tiposBarriles[numRandom] + ":" + String.valueOf(cont),
				          new BarrilRojo(20, 100, 25, 25));
		} else if (tiposBarriles[numRandom].equals("BarrilVerde")) {
			elementos.put(tiposBarriles[numRandom] + ":" + String.valueOf(cont),
				          new BarrilVerde(20, 100, 25, 25));
		}
		Elemento elemento = elementos.get(tiposBarriles[numRandom] + ":" + String.valueOf(cont));
		Barril barril = (Barril) elemento;
		Hilos movimientoBarril = new Hilos(barril, "movimientoBarril");
		new Thread(movimientoBarril).start();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Elimina los elementos que han sido destruidos del mapa de elementos.
	 */
	public void revisarElementos() {
		HashMap<String, Elemento> currentElem = (HashMap<String, Elemento>) elementos.clone();
		for (HashMap.Entry<String, Elemento> e: currentElem.entrySet()) {
			if (e.getValue().destruido) {
				elementos.remove(e.getKey());
			}
		}
	}
	
	/**
	 * Agrega al escenario un objeto sorpresa de manera aleatoria, este método se duerme durante 15 segundos.
	 */
	public void agregarObjetosSorpresa() {	
		while (true) {
			String[] tiposSorpresas = {"Manzana", "Cereza", "Corazon", "Soga", "Hongo", "Martillo"};
			int numRandom = (int) (Math.random() * 6);
			int cont = 1;
			@SuppressWarnings("unchecked")
			HashMap<String, Elemento> elementosCopia = (HashMap<String, Elemento>) elementos.clone();
			for (HashMap.Entry<String, Elemento> b: elementosCopia.entrySet()) {
				String[] tipo = b.getKey().split(":");
				if (tipo[0].equals(tiposSorpresas[numRandom])) {
					cont ++;
				}
			}
			if (tiposSorpresas[numRandom].equals("Soga")) {
				int[] posicion = posicionSoga(); 
				elementos.put(tiposSorpresas[numRandom] + ":" + String.valueOf(cont),
						      new Soga(posicion[0], posicion[1], 5, 80));
			} else if (tiposSorpresas[numRandom].equals("Manzana")) {
				int[] posicion = posicionBonus();
				elementos.put(tiposSorpresas[numRandom] + ":" + String.valueOf(cont),
					          new Bonus(posicion[0], posicion[1], 30, 30, 0, 5));
			} else if (tiposSorpresas[numRandom].equals("Cereza")) {
				int[] posicion = posicionBonus();
				elementos.put(tiposSorpresas[numRandom] + ":" + String.valueOf(cont),
					         new Bonus(posicion[0], posicion[1], 30, 30, 0, 10));
			} else if (tiposSorpresas[numRandom].equals("Corazon")) {
				int[] posicion = posicionBonus();
				elementos.put(tiposSorpresas[numRandom] + ":" + String.valueOf(cont),
					         new Bonus(posicion[0], posicion[1], 30, 30, 1, 0));
			} else if (tiposSorpresas[numRandom].equals("Hongo")) {
				int[] posicion = posicionBonus();
				elementos.put(tiposSorpresas[numRandom] + ":" + String.valueOf(cont),
					          new Hongo(posicion[0], posicion[1], 30, 30));
			} else if (tiposSorpresas[numRandom].equals("Martillo")) {
				int[] posicion = posicionBonus();
				elementos.put(tiposSorpresas[numRandom] + ":" + String.valueOf(cont),
					          new Martillo(posicion[0], posicion[1], 30, 30));
			}
			Elemento elemento = elementos.get(tiposSorpresas[numRandom] + ":" + String.valueOf(cont));
			ObjetoSorpresa objetoSorpresa = (ObjetoSorpresa) elemento;
			Hilos temporizadorObjetoSorpresa = new Hilos(objetoSorpresa, "temporizadorObjetoSorpresa");
			new Thread(temporizadorObjetoSorpresa).start();
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Busca una posición para la soga de manera aleatoria.
	 * 
	 * @return posición de la soga.
	 */
	@SuppressWarnings("unchecked")
	private int[] posicionSoga() {
		ArrayList<Escalera> escaleras = new ArrayList<Escalera>();
		ArrayList<Plataforma> plataformas = new ArrayList<Plataforma>();
		int[] posicion = new int[2];
		HashMap<String, Elemento> elementosCopia = (HashMap<String, Elemento>) elementos.clone();
		for (HashMap.Entry<String, Elemento> p: elementosCopia.entrySet()) {
			String[] tipo = p.getKey().split(":");
			if (tipo[0].equals("PlataformaNegativa") || tipo[0].equals("PlataformaPositiva")) {
				plataformas.add((Plataforma) p.getValue());
			} else if(tipo[0].equals("EscaleraCompleta")) {
				escaleras.add((Escalera) p.getValue());
			}
		}
		boolean posValida = false;
		while (!posValida) {
			int numRandom =ThreadLocalRandom.current().nextInt(1,plataformas.size());
			int minX = plataformas.get(numRandom-1).cuantificable.getPosx();
			if (plataformas.get(numRandom).cuantificable.getPosx() > plataformas.get(numRandom-1).cuantificable.getPosx()) {
				minX = plataformas.get(numRandom).cuantificable.getPosx();
			}
			int maxX = plataformas.get(numRandom-1).cuantificable.getPosx() + plataformas.get(numRandom-1).cuantificable.getAncho() - 30;
			if (plataformas.get(numRandom-1).cuantificable.getPosx() + plataformas.get(numRandom-1).cuantificable.getAncho() - 30 > 
				plataformas.get(numRandom).cuantificable.getPosx() + plataformas.get(numRandom-1).cuantificable.getAncho() - 30) {
				maxX = plataformas.get(numRandom).cuantificable.getPosx() + plataformas.get(numRandom-1).cuantificable.getAncho() - 30;
			}
			int x = ThreadLocalRandom.current().nextInt(minX, maxX + 1);
			int y = 0; Plataforma p = plataformas.get(numRandom);
			for (int ny = p.cuantificable.getPosy(); ny < p.cuantificable.getPosy() + p.cuantificable.getAlto(); ny++) {
				if (p.contains(x, ny) && !(p.contains(x, ny-1))) {
					y = ny+1;
				}
			}
			posValida = true;
			for (Escalera e: escaleras) {
				if (y >= e.cuantificable.getPosy() - 10 && y + 80 <= e.cuantificable.getPosy() + e.cuantificable.getAlto() + 10) {
					if (x > e.cuantificable.getPosx() - 50 && x < e.cuantificable.getPosx() + e.cuantificable.getAncho() + 100) {
						posValida = false;
					}
				}
			}
			posicion[0] = x; posicion[1] = y;
		}
		return posicion;
	}
	
	/**
	 * Busca una posición para los objetos bonus (corazón, manzana, cereza) de manera aleatoria.
	 * 
	 * @return posición del objeto bonus.
	 */
	private int [] posicionBonus() {
		int [] posicion = posicionSoga();
		posicion[1] = posicion[1] - 50;
		return posicion;
	}
	
	/**
	 * Retorna la plaforma en la que se escuentra un rescatador actualmente, si no esta en una retorna null.
	 * 
	 * @param rescatador rescatador en cuestión.
	 * @return plataforma.
	 */
	@SuppressWarnings("unchecked")
	public static Plataforma demePlataforma(Jumpman rescatador) {
		int x = rescatador.getCuantificable().getPosx();
		int y = rescatador.getCuantificable().getPosy();
		int ancho  = rescatador.getCuantificable().getAncho();
		int alto = rescatador.getCuantificable().getAlto();
		String direccion = rescatador.direccion;
		HashMap<String, Elemento> elementosClonados = (HashMap<String, Elemento>) elementos.clone();
		for (Entry<String, Elemento> e: elementosClonados.entrySet()) {
			String[] tipo = e.getKey().split(":");
			if (tipo[0].equals("PlataformaNegativa") || tipo[0].equals("PlataformaPositiva") || tipo[0].equals("PlataformaRectaLarga")) {
				if (e.getValue().esInteractuable(x, y, ancho, alto, direccion)) {
					return (Plataforma) e.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * Retorna la plataforma en la que se encuentra un barril.
	 * 
	 * @param barril barril en cuestión.
	 * @return plataforma.
	 */
	@SuppressWarnings("unchecked")
	public static Plataforma demePlataforma(Barril barril) {
		int x = barril.cuantificable.getPosx();
		int y = barril.cuantificable.getPosy();
		int ancho  = barril.cuantificable.getAncho();
		int alto = barril.cuantificable.getAlto();
		for (Entry<String, Elemento> e: ((HashMap<String, Elemento>) elementos.clone()).entrySet()) {
			String[] tipo = e.getKey().split(":");
			if (tipo[0].equals("PlataformaNegativa")) {
				if (e.getValue().esInteractuable(x, y, ancho, alto, "R")) {
					return (Plataforma) e.getValue();
				}
			} if (tipo[0].equals("PlataformaPositiva")) {
				if (e.getValue().esInteractuable(x, y, ancho, alto, "L")) {
					return (Plataforma) e.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * Busca las escaleras completas que pertenecen a un plataforma específica.
	 * 
	 * @param plataforma plataforma en la que se quieren buscar las escaleras completas.
	 * @return lista de escaleras completas.
	 */
	public static ArrayList<EscaleraCompleta> busqueEscalerasCompletas(Plataforma plataforma) {
		return plataforma.busqueEscalerasCompletas();
	}

	@Override
	/**
	 * Inicia el hilo para agregar objetos sorpresa y agrega barriles continuamente.
	 */
	public void run() {
		Hilos agregarObjetosSorpresa = new Hilos(this, "agregarObjetosSorpresa");
		new Thread(agregarObjetosSorpresa).start();
		while (true) {
			agregarBarril();
		}
	}
	
	public void save (File file) throws DonkeyPOOBException {
		DonkeyPOOBDAO.salve(this, file);
	}
}