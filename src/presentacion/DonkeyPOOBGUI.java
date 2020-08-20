package presentacion;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import aplicacion.*;

@SuppressWarnings("serial")
public class DonkeyPOOBGUI extends JDialog implements Runnable, KeyListener {
	
	private JPanel panelJuego;
	private Escenario juego;
	private String pathProperties = "src/presentacion/Properties.properties";
	private HashMap<String, ImageIcon> imagenes;
	private HashMap<JLabel, Elemento> lblsElementos;
	private HashMap<JLabel, Personaje> lblsPersonajes;
	private JLabel lblPuntajeMario;
	private JLabel lblVidasMario;
	private JLabel lblMartilloMario;
	private JLabel lblHongoMario;
	private JLabel lblPuntajeLuigi;
	private JLabel lblVidasLuigi;
	private JLabel lblMartilloLuigi;
	private JLabel lblHongoLuigi;
	
	private JButton btnGuardar;
	
	private static final String NOMBRE = "DonkeyPOOB";
	private static final int ANCHO = 800;
	public static final int ALTO = 680;
	private String modoDeJuego;
	private String jugador1;
	private String jugador2;
	
	private boolean enFuncionamiento = false;
	private static Thread threadMain;
	
	private static int aps = 0;
	private static int fps = 0;
	
	private ArrayList<Integer> controles1;
	private ArrayList<Integer> controles2;
	
	private static int posicionFinal;
	
	public DonkeyPOOBGUI(JFrame principal, String modoDeJuego, String j1, String j2){
		super(principal,true);
		setTitle(NOMBRE);
		setSize(ANCHO, ALTO);
		try {
			juego = new Escenario(1, modoDeJuego, j1, j2);
		} catch (DonkeyPOOBException e) {
			e.printStackTrace();
		}
		new Cargador(pathProperties);
		imagenes = Cargador.properties;
		this.modoDeJuego = modoDeJuego;
		if (modoDeJuego.equals("Maquina contra maquina")) {
			String[] jug1 = j1.split("-");
			String[] jug2 = j2.split("-");
			jugador1 = jug1[0]+":1"; jugador2 = jug2[0]+":1";
		}else if(modoDeJuego.equals("Jugador contra maquina")) {
			jugador1 = j1+":1";
			String[] jug2 = j2.split("-");
			jugador2 = jug2[0]+":1";
		}else if (modoDeJuego.equals("Dos jugadores")){
			jugador1 = j1+":1"; jugador2 = j2+":1";
		}else {
			jugador1 = j1+":1";
		}
		prepareElementos();
		dibujePersonajes();
		prepareAcciones();
		controles1 = new ArrayList<Integer>();
		controles2 = new ArrayList<Integer>();
		posicionFinal = 70;
		iniciar();
	}
	
	private void prepareElementos() {
		setLocationRelativeTo(null);	    
		panelJuego = new JPanel();
		panelJuego.setLayout(null);
		panelJuego.setBackground(Color.BLACK);
		lblPuntajeMario = new JLabel("");
		lblPuntajeMario.setBounds(0, 0, 100, 20);
		lblPuntajeMario.setForeground(Color.WHITE);
		lblVidasMario = new JLabel("");
		lblVidasMario.setBounds(150, 0, 100 ,20);
		lblVidasMario.setForeground(Color.WHITE);
		lblMartilloMario = new JLabel("");
		lblMartilloMario.setBounds(0, 20, 100, 20);
		lblMartilloMario.setForeground(Color.WHITE);
		lblHongoMario = new JLabel("");
		lblHongoMario.setBounds(150, 20, 100, 20);
		lblHongoMario.setForeground(Color.WHITE);
		panelJuego.add(lblPuntajeMario);
		panelJuego.add(lblVidasMario);
		panelJuego.add(lblMartilloMario);
		panelJuego.add(lblHongoMario);
		lblPuntajeLuigi = new JLabel("");
		lblPuntajeLuigi.setBounds(0, 40, 100, 20);
		lblPuntajeLuigi.setForeground(Color.WHITE);
		lblVidasLuigi = new JLabel("");
		lblVidasLuigi.setBounds(150, 40, 100, 20);
		lblVidasLuigi.setForeground(Color.WHITE);
		lblMartilloLuigi = new JLabel("");
		lblMartilloLuigi.setBounds(0, 60, 100, 20);
		lblMartilloLuigi.setForeground(Color.WHITE);
		lblHongoLuigi = new JLabel("");
		lblHongoLuigi.setBounds(150, 60, 100, 20);
		lblHongoLuigi.setForeground(Color.WHITE);
		panelJuego.add(lblPuntajeLuigi);
		panelJuego.add(lblVidasLuigi);
		panelJuego.add(lblMartilloLuigi);
		panelJuego.add(lblHongoLuigi);
		if (modoDeJuego.equals("Un jugador")) {
			lblPuntajeLuigi.setVisible(false);
			lblVidasLuigi.setVisible(false);
			lblMartilloLuigi.setVisible(false);
			lblHongoLuigi.setVisible(false);
		}
		btnGuardar = new JButton("Guardar partida");
		btnGuardar.setVisible(true);
		btnGuardar.setBounds(650, 0, 150, 30);
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setBackground(Color.BLACK);
		panelJuego.add(btnGuardar);
		setFocusable(true);
		getContentPane().add(panelJuego);	
		dibujeElementos();
		mostrar();
	}
	
	private void prepareAcciones() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		WindowAdapter oyenteCerrar = new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				detener();
			}
		};
		this.addWindowListener(oyenteCerrar);
		
		addKeyListener(this);
		
		ActionListener oyenteGuardar = new ActionListener() {
		    public void actionPerformed(ActionEvent ae) {
			JFileChooser fileChooser = new JFileChooser();
			int opcion = fileChooser.showSaveDialog(fileChooser);
			if (opcion == 0) {
			        try {
						juego.save(fileChooser.getSelectedFile());
					} catch (DonkeyPOOBException e) {
						e.printStackTrace();
					} 
			}
		    }
		};
		btnGuardar.addActionListener(oyenteGuardar);
	}

	private void dibujeElementos() {
		lblPuntajeMario.setText("Puntos Mario: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Mario:1")).puntaje));
		lblVidasMario.setText("Vidas Mario: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Mario:1")).vidas));
		if (((Jumpman) juego.getPersonajes().get("Mario:1")).tieneMartillo){
			lblMartilloMario.setText("Mario con martillo");
		} else {
			lblMartilloMario.setText("Mario sin martillo");
		}
		if (((Jumpman) juego.getPersonajes().get("Mario:1")).tieneHongo){
			lblHongoMario.setText("Mario con hongo");
		} else {
			lblHongoMario.setText("Mario sin hongo");
		}
		if (modoDeJuego != "Un jugador") {
			lblPuntajeLuigi.setText("Puntos Luigi: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Luigi:1")).puntaje));
			lblVidasLuigi.setText("Vidas Luigi: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Luigi:1")).vidas));
			if (((Jumpman) juego.getPersonajes().get("Luigi:1")).tieneMartillo){
				lblMartilloLuigi.setText("Luigi con martillo");
			} else {
				lblMartilloLuigi.setText("Luigi sin martillo");
			}
			if (((Jumpman) juego.getPersonajes().get("Luigi:1")).tieneHongo){
				lblHongoLuigi.setText("Luigi con hongo");
			} else {
				lblHongoLuigi.setText("Luigi sin hongo");
			}
		}
		lblsElementos = new HashMap<JLabel, Elemento>();
		@SuppressWarnings("unchecked")
		HashMap<String, Elemento> elementosCopia = (HashMap<String, Elemento>) juego.getElementos().clone();
		for (HashMap.Entry<String, Elemento> e: elementosCopia.entrySet()) {
			pintarElemento(e.getKey(), e.getValue());
		}
	}
	
	private void dibujePersonajes(){
		lblsPersonajes = new HashMap<JLabel, Personaje>();
		for (HashMap.Entry<String, Personaje> e: juego.getPersonajes().entrySet()) {
			String[] v = e.getKey().split(":");
			int posx = e.getValue().getCuantificable().getPosx();
			int posy = e.getValue().getCuantificable().getPosy();
			int ancho = e.getValue().getCuantificable().getAncho();
			int alto = e.getValue().getCuantificable().getAlto();
			JLabel lbl = new JLabel("");
			lbl.setBounds(posx, posy, ancho, alto);
			ImageIcon image = imagenes.get("Personaje-"+v[0]);
			ImageIcon icono = new ImageIcon(image.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), 
					                        Image.SCALE_SMOOTH));
			lbl.setIcon(icono);
			panelJuego.add(lbl);
			lblsPersonajes.put(lbl, e.getValue());
		}
	}

	@Override
	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();
		double tiempoTranscurrido;
		double delta = 0;

		requestFocus();
		
		new Thread(juego).start();
		
		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();
			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;
			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				actualizar();
				
				delta--;
			}

			mostrar();
			
			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				this.setTitle(NOMBRE + " | APS: " + aps + " | FPS: " + fps);
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}

		}
		
	}
	
	public synchronized void iniciar() {
		enFuncionamiento = true;

		threadMain = new Thread(this, "hiloPrincipal");
		threadMain.start();
	}

	public synchronized void detener() {
		enFuncionamiento = false;

		try {
			threadMain.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void actualizar() {
		aps++;
		ejecuteControles();
		repinteElementos();
		repintePersonajes();
		verificarEstado();
	}
	
	@SuppressWarnings("unchecked")
	public void repinteElementos() {
		lblPuntajeMario.setText("Puntos Mario: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Mario:1")).puntaje));
		lblVidasMario.setText("Vidas Mario: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Mario:1")).vidas));
		if (((Jumpman) juego.getPersonajes().get("Mario:1")).tieneMartillo){
			lblMartilloMario.setText("Mario con martillo");
		} else {
			lblMartilloMario.setText("Mario sin martillo");
		}
		if (((Jumpman) juego.getPersonajes().get("Mario:1")).tieneHongo){
			lblHongoMario.setText("Mario con hongo");
		} else {
			lblHongoMario.setText("Mario sin hongo");
		}
		if (modoDeJuego != "Un jugador") {
			lblPuntajeLuigi.setText("Puntos Luigi: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Luigi:1")).puntaje));
			lblVidasLuigi.setText("Vidas Luigi: " + String.valueOf(((Jumpman) juego.getPersonajes().get("Luigi:1")).vidas));
			if (((Jumpman) juego.getPersonajes().get("Luigi:1")).tieneMartillo){
				lblMartilloLuigi.setText("Luigi con martillo");
			} else {
				lblMartilloLuigi.setText("Luigi sin martillo");
			}
			if (((Jumpman) juego.getPersonajes().get("Luigi:1")).tieneHongo){
				lblHongoLuigi.setText("Luigi con hongo");
			} else {
				lblHongoLuigi.setText("Luigi sin hongo");
			}
		}
		for (HashMap.Entry<JLabel, Elemento> e: lblsElementos.entrySet()) {
			if ((e.getValue() != null) && (!e.getValue().destruido) && (e.getKey().getX() != e.getValue().cuantificable.getPosx() 
				 || e.getKey().getY() != e.getValue().cuantificable.getPosy())) {
					e.getKey().setLocation(e.getValue().cuantificable.getPosx(), e.getValue().cuantificable.getPosy());
			}
			if (e.getValue().destruido) {
				panelJuego.remove(e.getKey());
			}
		}
		
		if (lblsElementos.size() != juego.getElementos().size()) {
			HashMap<String, Elemento> currentElem = (HashMap<String, Elemento>) juego.getElementos().clone();
 			for (HashMap.Entry<String, Elemento> e: currentElem.entrySet()) {
 				if (!lblsElementos.containsValue(e.getValue())) {
					pintarElemento(e.getKey(), e.getValue());
 				}
			}
		}
	}
	
	private void pintarElemento(String key, Elemento e) {
		String[] v = key.split(":");
		int posx = e.cuantificable.getPosx();
		int posy = e.cuantificable.getPosy();
		int ancho = e.cuantificable.getAncho();			
		int alto = e.cuantificable.getAlto();
		JLabel lbl = new JLabel("");
		lbl.setBounds(posx, posy, ancho, alto);
		ImageIcon image = imagenes.get("Elemento-"+v[0]);
		ImageIcon icono = new ImageIcon(image.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_SMOOTH));
		lbl.setIcon(icono);
		panelJuego.add(lbl);
		lblsElementos.put(lbl, e);
	}
	
	public void repintePersonajes() {
		for (HashMap.Entry<JLabel, Personaje> e: lblsPersonajes.entrySet()) {
			if ((e.getValue() != null) && 
				(e.getKey().getX() != e.getValue().getCuantificable().getPosx() || 
				 e.getKey().getY() != e.getValue().getCuantificable().getPosy())) {
				e.getKey().setLocation(e.getValue().getCuantificable().getPosx(), 
									   e.getValue().getCuantificable().getPosy());
			}
		}
	}
	
	public void mostrar() {
		fps++;
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getExtendedKeyCode() == KeyEvent.VK_P) {
			try {
				threadMain.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getExtendedKeyCode() == KeyEvent.VK_R) {
			threadMain.start();
		}
		
		if (!controles1.contains(e.getExtendedKeyCode()) && !juego.getPersonajes().get(jugador1).saltando && juego.getPersonajes().get(jugador1).estaVivo) {
			if (e.getExtendedKeyCode() == KeyEvent.VK_UP && !controles1.contains(KeyEvent.VK_K)) {
				controles1.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN && !controles1.contains(KeyEvent.VK_K)) {
				controles1.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT && !controles1.contains(KeyEvent.VK_K)) {
				controles1.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT && !controles1.contains(KeyEvent.VK_K)) {
				controles1.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_K) {
				controles1.clear();
				controles1.add(e.getExtendedKeyCode());
			}
		}
			
		if (!controles2.contains(e.getExtendedKeyCode()) && jugador2 != null && !juego.getPersonajes().get(jugador2).saltando && juego.getPersonajes().get(jugador2).estaVivo) {
			if (e.getExtendedKeyCode() == KeyEvent.VK_W && !controles2.contains(KeyEvent.VK_Q)) {
				controles2.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_S && !controles2.contains(KeyEvent.VK_Q)) {
				controles2.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_A && !controles2.contains(KeyEvent.VK_Q)) {
				controles2.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_D && !controles2.contains(KeyEvent.VK_Q)) {
				controles2.add(e.getExtendedKeyCode());
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_Q) {
				controles2.clear();
				controles2.add(e.getExtendedKeyCode());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (controles1.contains(e.getExtendedKeyCode())) {
			if (e.getExtendedKeyCode() == KeyEvent.VK_UP) {
				controles1.remove(controles1.indexOf(e.getExtendedKeyCode()));
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
				controles1.remove(controles1.indexOf(e.getExtendedKeyCode()));
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
				controles1.remove(controles1.indexOf(e.getExtendedKeyCode()));
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
				controles1.remove(controles1.indexOf(e.getExtendedKeyCode()));
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_K) {
				if (controles1.contains(e.getExtendedKeyCode())) {
					controles1.remove(controles1.indexOf(e.getExtendedKeyCode()));
				}
			}
		}
		
		if (controles2.contains(e.getExtendedKeyCode())) {
			if (e.getExtendedKeyCode() == KeyEvent.VK_W) {
				controles2.remove(controles2.indexOf(e.getExtendedKeyCode()));
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_S) {
				controles2.remove(controles2.indexOf(e.getExtendedKeyCode()));
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_A) {
				controles2.remove(controles2.indexOf(e.getExtendedKeyCode()));
			} else if (e.getExtendedKeyCode() == KeyEvent.VK_D) {
				controles2.remove(controles2.indexOf(e.getExtendedKeyCode()));
			}
		}
	}
	
	private void ejecuteControles() {
		ArrayList<Elemento> elementosInteractuar1 = new ArrayList<Elemento>();
		ArrayList<Elemento> elementosInteractuar2 = new ArrayList<Elemento>();
		@SuppressWarnings("unchecked")
		ArrayList<Integer> controlesCopia1 = (ArrayList<Integer>) controles1.clone();
		@SuppressWarnings("unchecked")
		ArrayList<Integer> controlesCopia2 = (ArrayList<Integer>) controles2.clone();
		for (Integer e: controlesCopia1) {
			if (e == KeyEvent.VK_UP) {
				elementosInteractuar1 = juego.getPersonajes().get(jugador1).elementosInteractuar("U");
				juego.getPersonajes().get(jugador1).interactue(elementosInteractuar1, "U");
			} else if (e == KeyEvent.VK_DOWN) {
				elementosInteractuar1 = juego.getPersonajes().get(jugador1).elementosInteractuar("D");
				juego.getPersonajes().get(jugador1).interactue(elementosInteractuar1, "D");
			} else if (e == KeyEvent.VK_LEFT) {
				elementosInteractuar1 = juego.getPersonajes().get(jugador1).elementosInteractuar("L");
				juego.getPersonajes().get(jugador1).interactue(elementosInteractuar1, "L");
			} else if (e == KeyEvent.VK_RIGHT) {
				elementosInteractuar1 = juego.getPersonajes().get(jugador1).elementosInteractuar("R");
				juego.getPersonajes().get(jugador1).interactue(elementosInteractuar1, "R");
			} else if (e == KeyEvent.VK_K) {
				Hilos animacionSalto = new Hilos((Jumpman) juego.getPersonajes().get(jugador1), "movimientoSalto");
				new Thread(animacionSalto).start();
				controles1.remove(controles1.indexOf(e));
			} if (elementosInteractuar1.size() == 0 && jugador1 != null) {
				Hilos animacionCaida = new Hilos((Jumpman) juego.getPersonajes().get(jugador1), "movimientoCaida");
				new Thread(animacionCaida).start();
			} 
		}
		for (Integer e: controlesCopia2) {
			if (e == KeyEvent.VK_W) {
				elementosInteractuar2 = juego.getPersonajes().get(jugador2).elementosInteractuar("U");
				juego.getPersonajes().get(jugador2).interactue(elementosInteractuar2, "U");
			} else if (e == KeyEvent.VK_S) {
				elementosInteractuar2 = juego.getPersonajes().get(jugador2).elementosInteractuar("D");
				juego.getPersonajes().get(jugador2).interactue(elementosInteractuar2, "D");
			} else if (e == KeyEvent.VK_A) {
				elementosInteractuar2 = juego.getPersonajes().get(jugador2).elementosInteractuar("L");
				juego.getPersonajes().get(jugador2).interactue(elementosInteractuar2, "L");
			} else if (e == KeyEvent.VK_D) {
				elementosInteractuar2 = juego.getPersonajes().get(jugador2).elementosInteractuar("R");
				juego.getPersonajes().get(jugador2).interactue(elementosInteractuar2, "R");
			} else if (e == KeyEvent.VK_Q) {
				Hilos animacionSalto = new Hilos((Jumpman) juego.getPersonajes().get(jugador2), "movimientoSalto");
				new Thread(animacionSalto).start();
				controles2.remove(controles2.indexOf(e));
			} if (elementosInteractuar2.size() == 0 && jugador2 != null) {
				Hilos animacionCaida = new Hilos((Jumpman) juego.getPersonajes().get(jugador2), "movimientoCaida");
				new Thread(animacionCaida).start();
			}
		}
	}
	
	private void verificarEstado() {
		if (modoDeJuego.equals("Un jugador")) {
			Jumpman jugador = ((Jumpman) juego.getPersonajes().get(jugador1));
			int posY = juego.getPersonajes().get(jugador1).getCuantificable().getPosy();
			int alto = juego.getPersonajes().get(jugador1).getCuantificable().getAlto();
			if (posY + alto <= posicionFinal) {
				detener();
				JOptionPane.showMessageDialog(null, "El jugador 1 ha ganado el juego.");
			} else if (jugador.vidas == 0) {
				detener();
				JOptionPane.showMessageDialog(null, "GAME OVER");
			}
		}
	}
	
}