package aplicacion;

import java.util.List;
import java.util.Map.Entry;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Plataforma por la cual el rescatador se mueve en el escenario.
 * 
 * @author Daniel Hernández
 * @author Angi Jiménez
 * @version 09/12/2019
 */
public class Plataforma extends ObjetoDeEstructura {

	private List<Integer> puntos;
	private String pendiente;

	/**
	 * Crea una plataforma recta.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 */
	public Plataforma(int x, int y, int ancho, int alto) {
		super(x, y, ancho, alto);
		puntos = new ArrayList<Integer>();
		puntos.add(x);
		puntos.add(y);
		puntos.add(x + 800);
		puntos.add(y);
		pendiente = "";
	}
	
	/**
	 * Crea una plataforma con pendiente.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param pendiente valor que indica si la pendiente es positiva '+' o negativa '-'.
	 */
	public Plataforma(int x, int y, int ancho, int alto, String pendiente) {
		super(x, y, ancho, alto);
		puntos = new ArrayList<Integer>();
		puntos.add(x);
		if (pendiente.equals("-")) {
			puntos.add(y);
			puntos.add(x+800);
			puntos.add(y + alto - 20);  
		}else if (pendiente.equals("+")){
			puntos.add(y + alto - 20);
			puntos.add(x+800);
			puntos.add(y);
		}
		this.pendiente = pendiente;
	}
	
	/**
	 * Retorna la pendiente de la plataforma.
	 * 
	 * @return pendiente.
	 */
	public String getPendiente() {
		return pendiente;
	}

	/**
	 * Retorna la lista de puntos que definen a la plataforma.
	 * 
	 * @return lista de puntos.
	 */
	public List<Integer> getPuntos() {
		return puntos;
	}
	
	@Override
	/**
	 * Pregunta si un objeto puede interactuar con la plataforma.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param direccion dirección en la que se mueve el objeto.
	 */
	public boolean esInteractuable(int x, int y, int ancho, int alto, String direccion) {
		if (direccion != "D" && direccion != "U") {
			return ((pendiente.equals("+") && contains(x + ancho, y + alto) && x + ancho >= cuantificable.getPosx()) ||
				    (pendiente.equals("-") && contains(x, y + alto) && x <= cuantificable.getPosx() + cuantificable.getAncho() ) ||
				    (pendiente.equals("") && contains(x + ancho, y + alto)));
		} return false;
	}
	
	/**
	 * Pregunta si la plataforma contiene un punto específico.
	 * 
	 * @param x coordenada del eje horizontal del punto.
	 * @param y coordenada del eje vertical del punto.
	 * @return true si lo contiene, false en caso contrario.
	 */
	public boolean contains(int x, int y) {
		int[] xPoints = {puntos.get(0), puntos.get(2), puntos.get(2), puntos.get(0)};
		int[] yPoints = {puntos.get(1), puntos.get(3), puntos.get(3) + 20, puntos.get(1) + 20};
		return (new Polygon(xPoints, yPoints, 4)).contains(x, y);
	}
	
	@Override
	/**
	 * Se ejecuta cuando la plataforma entra en contacto con un barril o un rescatador.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param direccion dirección en la que se mueve el barril/rescatador. 
	 */
	public int[] interactue(int x, int y, int ancho, int alto, String direccion) {
		if (direccion.equals("R")) {
			x += 1; 
		} else if (direccion.equals("L")) {
			x -= 1;
		}
		int ny = findY(x, y, ancho, alto, direccion);
		int[] posicionEsperada = {x, ny};
		return posicionEsperada;
	}
	
	/**
	 * Encuentra la coordenada en el eje vertical en la que debería quedar el barril/rescatador despues de interactuar con la plataforma.
	 * 
	 * @param x posición en el eje horizontal.
	 * @param y posición en el eje vertical.
	 * @param ancho longitud en píxeles del ancho de su imagen.
	 * @param alto longitud en píxeles del alto de su imagen.
	 * @param direccion dirección en la que se mueve el barril/rescatador.
	 * @return coordenada del eje vertical.
	 */
	private int findY(int x, int y, int ancho, int alto, String direccion) {
		if (pendiente.equals("+")) {
			x+= ancho;
		}
		for (int ny = 630; ny > 0; ny--) {
			if (contains(x, ny + alto) && !contains(x, ny + alto - 1)) {
				return ny;
			}
		}
		return y;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Busca las escaleras completas que empiezan en la plataforma.
	 * 
	 * @return lista de escaleras completas.
	 */
	public ArrayList<EscaleraCompleta> busqueEscalerasCompletas() {
		int yPlataforma = cuantificable.getPosy();
		int altoPlataforma = cuantificable.getAlto();
		ArrayList<EscaleraCompleta> escaleras = new ArrayList<EscaleraCompleta>();
		for (Entry<String, Elemento> e: ((HashMap<String, Elemento>) Escenario.elementos.clone()).entrySet()) {
			String[] tipo = e.getKey().split(":");
			if (tipo[0].equals("EscaleraCompleta")) {
				int yEscalera = e.getValue().cuantificable.getPosy();
				int altoEscalera = e.getValue().cuantificable.getAlto();
				if (yEscalera + altoEscalera >= yPlataforma && yEscalera + altoEscalera <= yPlataforma + altoPlataforma) {
					escaleras.add((EscaleraCompleta) e.getValue());
				}
			}
		}
		return escaleras;
	}
}