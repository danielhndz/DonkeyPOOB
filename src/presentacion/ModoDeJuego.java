package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ModoDeJuego extends JDialog{
	
	private JPanel panel;
	private JPanel panelModoJuego;
	private JPanel panelJugador1;
	private JPanel panelJugador2;
	private JButton unJugador;
	private JButton dosJugadores;
	private JButton jugadorContraMaquina;
	private JButton maquinaContraMaquina;
	private JPanel opcionesMario;
	private JPanel opcionesLuigi;
	private String modoJuego;
	private String jugador1;
	private String jugador2;
	private JFrame anterior;

	public ModoDeJuego(JFrame anterior){
		super(anterior,true);
		setTitle("Modo de juego");
		setSize(845,507); 
		setLocationRelativeTo(null);
		prepareElementos();
		setResizable(false);
		this.anterior = anterior;
	}
	
	private void prepareElementos() {	
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(null);
		getContentPane().add(panel);
		panelModoJuego = new JPanel();
		panelModoJuego.setBackground(Color.BLACK);
		panelModoJuego.setLayout(null);
		panelModoJuego.setBounds(10, 10, 810, 450);
		unJugador = new JButton();
		unJugador.setBounds(60, 120, 150, 160);
		ImageIcon imgUnJugador = new ImageIcon(getClass().getResource("UnJugador.png"));
		unJugador.setIcon(new ImageIcon(imgUnJugador.getImage().getScaledInstance(unJugador.getWidth(), unJugador.getHeight(), Image.SCALE_SMOOTH)));
		dosJugadores = new JButton();
		dosJugadores.setBounds(240, 120, 150, 160);
		ImageIcon imgDosJugadores = new ImageIcon(getClass().getResource("DosJugadores.png"));
		dosJugadores.setIcon(new ImageIcon(imgDosJugadores.getImage().getScaledInstance(dosJugadores.getWidth(), dosJugadores.getHeight(), Image.SCALE_SMOOTH)));
		jugadorContraMaquina = new JButton();
		jugadorContraMaquina.setBounds(420, 120, 150, 160);
		ImageIcon imgContraMaq = new ImageIcon(getClass().getResource("ContraMaquina.png"));
		jugadorContraMaquina.setIcon(new ImageIcon(imgContraMaq.getImage().getScaledInstance(jugadorContraMaquina.getWidth(), jugadorContraMaquina.getHeight(), Image.SCALE_SMOOTH)));
		maquinaContraMaquina = new JButton();
		maquinaContraMaquina.setBounds(600, 120, 150, 160);
		ImageIcon imgMaqContraMaq = new ImageIcon(getClass().getResource("MaquinaContraMaquina.png"));
		maquinaContraMaquina.setIcon(new ImageIcon(imgMaqContraMaq.getImage().getScaledInstance(maquinaContraMaquina.getWidth(), maquinaContraMaquina.getHeight(), Image.SCALE_SMOOTH)));
		panelModoJuego.add(unJugador); panelModoJuego.add(dosJugadores); 
		panelModoJuego.add(jugadorContraMaquina); panelModoJuego.add(maquinaContraMaquina);
		panelModoJuego.setVisible(true);
		panel.add(panelModoJuego);
		prepareAcciones();
	}
	
	private void prepareAcciones() {
		ActionListener oyenteUnJugador = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				modoJuego = "Un jugador";
				seleccionarPersonajes();
			}
		};
		unJugador.addActionListener(oyenteUnJugador);
		
		ActionListener oyenteDosJugadores = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				modoJuego = "Dos jugadores";
				seleccionarPersonajes();
			}
		};
		dosJugadores.addActionListener(oyenteDosJugadores);
		
		ActionListener oyenteJugadorContraMaquina = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				modoJuego = "Jugador contra maquina";
				seleccionarPersonajes();
			}
		};
		jugadorContraMaquina.addActionListener(oyenteJugadorContraMaquina);
		
		ActionListener oyenteMaquinaContraMaquina = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				modoJuego = "Maquina contra maquina";
				seleccionarPersonajes();
			}
		};
		maquinaContraMaquina.addActionListener(oyenteMaquinaContraMaquina);
	}
	
	private void seleccionarPersonajes() {
		panelModoJuego.setVisible(false);
		panel.remove(panelModoJuego);
		JButton mario1 = new JButton();
		JButton luigi1 = new JButton();
		mario1.setBounds(30,100,150,190);
		ImageIcon imgMario = new ImageIcon(getClass().getResource("PersonajeMario.png"));
		mario1.setBackground(Color.BLACK);
		mario1.setIcon(new ImageIcon(imgMario.getImage().getScaledInstance(mario1.getWidth(), mario1.getHeight(), Image.SCALE_SMOOTH)));
		luigi1.setBounds(225,100,150,190);
		ImageIcon imgLuigi = new ImageIcon(getClass().getResource("PersonajeLuigi.png"));
		luigi1.setBackground(Color.BLACK);
		luigi1.setIcon(new ImageIcon(imgLuigi.getImage().getScaledInstance(luigi1.getWidth(), luigi1.getHeight(), Image.SCALE_SMOOTH)));
		panelJugador1 = new JPanel();
		panelJugador1.setLayout(null);
		panelJugador1.setBounds(10, 60, 400, 400);
		panelJugador1.setVisible(true);
		panelJugador1.setBackground(Color.BLACK);
		panelJugador1.add(mario1);
		panelJugador1.add(luigi1);
		JButton mario2 = new JButton();
		JButton luigi2 = new JButton();
		mario2.setBounds(30,100,150,190);
		mario2.setBackground(Color.BLACK);
		mario2.setIcon(new ImageIcon(imgMario.getImage().getScaledInstance(mario2.getWidth(), mario2.getHeight(), Image.SCALE_SMOOTH)));
		luigi2.setBounds(225,100,150,190);
		luigi2.setBackground(Color.BLACK);
		luigi2.setIcon(new ImageIcon(imgLuigi.getImage().getScaledInstance(luigi2.getWidth(), luigi2.getHeight(), Image.SCALE_SMOOTH)));
		panelJugador2 = new JPanel();
		panelJugador2.setLayout(null);
		panelJugador2.setBounds(420, 60, 400, 400);
		panelJugador2.setVisible(true);
		panelJugador2.setBackground(Color.BLACK);
		panelJugador2.add(mario2);
		panelJugador2.add(luigi2);
		panel.add(panelJugador1);
		panel.add(panelJugador2);
		@SuppressWarnings("unused")
		JDialog thisdialog = this;
		if (modoJuego.equals("Un jugador")) {
			mario2.setEnabled(false);
			luigi2.setEnabled(false);
		}
		
		ActionListener oyenteMario1 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mario1.setVisible(false);
				luigi1.setVisible(false);
				personalizarJugador1("Mario");
				mario2.setEnabled(false);
			}
		};
		mario1.addActionListener(oyenteMario1);
		
		ActionListener oyenteMario2 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				personalizarJugador2("Mario");
				mario1.setEnabled(false);
			}
		};
		mario2.addActionListener(oyenteMario2);
		
		ActionListener oyenteLuigi1 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				personalizarJugador1("Luigi");
				luigi2.setEnabled(false);
			}
		};
		luigi1.addActionListener(oyenteLuigi1);
		
		ActionListener oyenteLuigi2 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				personalizarJugador2("Luigi");
				luigi1.setEnabled(false);
			}
		};
		luigi2.addActionListener(oyenteLuigi2);
	}
	
	private void personalizarJugador2(String nombre) {
		panelJugador2.setVisible(false);
		panel.remove(panelJugador2);
		if (nombre.equals("Mario")) {
			elegirMario(420, 2);
		}else if (nombre.equals("Luigi")) {
			elegirLuigi(420, 2);
		}
	}
	
	private void personalizarJugador1(String nombre) {
		panelJugador1.setVisible(false);
		panel.remove(panelJugador1);
		if (nombre.equals("Mario")) {
			elegirMario(10, 1);
		}else if (nombre.equals("Luigi")) {
			elegirLuigi(10, 1);
		}
	}
	
	private void elegirMario(int x, int jugadorNo) {
		opcionesMario = new JPanel();
		opcionesMario.setBounds(x, 60, 400, 400);
		opcionesMario.setBackground(Color.BLACK);
		opcionesMario.setLayout(null);
		JButton opcion1 = new JButton("Mimo"); 
		ImageIcon imgOpc1 = new ImageIcon(getClass().getResource("MarioBlanco.png"));
		opcion1.setBackground(Color.BLACK);
		opcion1.setIcon(new ImageIcon(imgOpc1.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH)));
		JButton opcion2 = new JButton(); 
		ImageIcon imgOpc2 = new ImageIcon(getClass().getResource("MarioCafe.png"));
		opcion2.setBackground(Color.BLACK);
		opcion2.setIcon(new ImageIcon(imgOpc2.getImage().getScaledInstance(100,120, Image.SCALE_SMOOTH)));
		JButton opcion3 = new JButton("Protector"); 
		ImageIcon imgOpc3 = new ImageIcon(getClass().getResource("MarioRojo.png"));
		opcion3.setBackground(Color.BLACK);
		opcion3.setIcon(new ImageIcon(imgOpc3.getImage().getScaledInstance(100, 120, Image.SCALE_SMOOTH)));
		if(modoJuego.equals("Maquina contra maquina") || (jugadorNo == 2 && modoJuego.equals("Jugador contra maquina"))) {
			opcion1.setText("Mimo");
			opcion1.setBounds(100,0,200,120);
			opcion1.setForeground(Color.WHITE);
			opcion2.setText("Temeroso");
			opcion2.setBounds(100,130,200,120);
			opcion2.setForeground(Color.WHITE);
			opcion3.setText("Protector");
			opcion3.setBounds(100,260,200,120);
			opcion3.setForeground(Color.WHITE);
		}else {
			opcion1.setBounds(50,60,100,120);
			opcion2.setBounds(250,60,100,120);
			opcion3.setBounds(150,220,100,120);
		}
		if(jugadorNo == 1 && jugador2 != null && jugador2.equals("Luigi-Mimo")) {
			opcion1.setEnabled(false);
		}else if(jugadorNo == 2 && jugador1 != null &&jugador1.equals("Luigi-Mimo")) {
			opcion1.setEnabled(false);
		}
		opcionesMario.add(opcion1);
		opcionesMario.add(opcion2);
		opcionesMario.add(opcion3);
		opcionesMario.setVisible(true);
		panel.add(opcionesMario);
		
		ActionListener oyenteMario1 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(jugadorNo == 1) {
					if (! modoJuego.equals("Maquina contra maquina")) {
						jugador1 = "Mario";
					}else {
						jugador1 = "Mario-Mimo";
					}
				}else{
					if (modoJuego.equals("Dos jugadores")) {
						jugador2 = "Mario";
					}else {
						jugador2 = "Mario-Mimo";
					}
				} 
				confirmePersonajeElegido(x, "mario");
				empezarjuego();
			}
		};
		opcion1.addActionListener(oyenteMario1);
		
		ActionListener oyenteMario2 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(jugadorNo == 1) {
					if (! modoJuego.equals("Maquina contra maquina")) {
						jugador1 = "Mario";
					}else {
						jugador1 = "Mario-Temeroso";
					}
				}else {
					if (modoJuego.equals("Dos jugadores")) {
						jugador2 = "Mario";
					}else {
						jugador2 = "Mario-Temeroso";
					}
				} 
				confirmePersonajeElegido(x, "mario");
				empezarjuego();
			}
		};
		opcion2.addActionListener(oyenteMario2);
		
		ActionListener oyenteMario3 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(jugadorNo == 1) {
					if (! modoJuego.equals("Maquina contra maquina")) {
						jugador1 = "Mario";
					}else {
						jugador1 = "Mario-Protector";
					}
				}else {
					if (modoJuego.equals("Dos jugadores")) {
						jugador2 = "Mario";
					}else {
						jugador2 = "Mario-Protector";
					}
				} 
				confirmePersonajeElegido(x, "mario");
				empezarjuego();
			}
		};
		opcion3.addActionListener(oyenteMario3);
	}
	
	private void elegirLuigi(int x, int jugadorNo) {
		opcionesLuigi = new JPanel();
		opcionesLuigi.setBounds(x, 60, 400, 400);
		opcionesLuigi.setBackground(Color.BLACK);
		opcionesLuigi.setLayout(null);
		JButton opcion1 = new JButton(); 
		opcion1.setBounds(50,60,100,120);
		ImageIcon imgOpc1 = new ImageIcon(getClass().getResource("LuigiMorado.png"));
		opcion1.setBackground(Color.BLACK);
		opcion1.setIcon(new ImageIcon(imgOpc1.getImage().getScaledInstance(opcion1.getWidth(), opcion1.getHeight(), Image.SCALE_SMOOTH)));
		JButton opcion2 = new JButton(); 
		opcion2.setBounds(250,60,100,120);
		ImageIcon imgOpc2 = new ImageIcon(getClass().getResource("LuigiNaranja.png"));
		opcion2.setBackground(Color.BLACK);
		opcion2.setIcon(new ImageIcon(imgOpc2.getImage().getScaledInstance(opcion2.getWidth(), opcion2.getHeight(), Image.SCALE_SMOOTH)));
		JButton opcion3 = new JButton(); 
		opcion3.setBounds(150,220,100,120);
		ImageIcon imgOpc3 = new ImageIcon(getClass().getResource("LuigiAzul.png"));
		opcion3.setBackground(Color.BLACK);
		opcion3.setIcon(new ImageIcon(imgOpc3.getImage().getScaledInstance(opcion3.getWidth(), opcion3.getHeight(), Image.SCALE_SMOOTH)));
		if(jugadorNo == 1 && jugador2 != null && jugador2.equals("Mario-Mimo")) {
			opcion1.setEnabled(false);
		}else if(jugadorNo == 2 && jugador1 != null &&jugador1.equals("Mario-Mimo")) {
			opcion1.setEnabled(false);
		}
		if(modoJuego.equals("Maquina contra maquina") || (jugadorNo == 2 && modoJuego.equals("Jugador contra maquina"))) {
			opcion1.setText("Mimo");
			opcion1.setBounds(100,0,200,120);
			opcion1.setForeground(Color.WHITE);
			opcion2.setText("Temeroso");
			opcion2.setBounds(100,130,200,120);
			opcion2.setForeground(Color.WHITE);
			opcion3.setText("Protector");
			opcion3.setBounds(100,260,200,120);
			opcion3.setForeground(Color.WHITE);
		}else {
			opcion1.setBounds(50,60,100,120);
			opcion2.setBounds(250,60,100,120);
			opcion3.setBounds(150,220,100,120);
		}
		opcionesLuigi.add(opcion1);
		opcionesLuigi.add(opcion2);
		opcionesLuigi.add(opcion3);
		opcionesLuigi.setVisible(true);
		panel.add(opcionesLuigi);
		
		ActionListener oyenteLuigi1 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(jugadorNo == 1) {
					if (! modoJuego.equals("Maquina contra maquina")) {
						jugador1 = "Luigi";
					}else {
						jugador1 = "Luigi-Mimo";
					}
				}else {
					if (modoJuego.equals("Dos jugadores")) {
						jugador2 = "Luigi";
					}else {
						jugador2 = "Luigi-Mimo";
					}
				} 
				confirmePersonajeElegido(x, "luigi");
				empezarjuego();
			}
		};
		opcion1.addActionListener(oyenteLuigi1);
		
		ActionListener oyenteLuigi2 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(jugadorNo == 1) {
					if (! modoJuego.equals("Maquina contra maquina")) {
						jugador1 = "Luigi";
					}else {
						jugador1 = "Luigi-Temeroso";
					}
				}else {
					if (modoJuego.equals("Dos jugadores")) {
						jugador2 = "Luigi";
					}else {
						jugador2 = "Luigi-Temeroso";
					}
				}
				confirmePersonajeElegido(x, "luigi");
				empezarjuego();
			}
		};
		opcion2.addActionListener(oyenteLuigi2);
		
		ActionListener oyenteLuigi3 = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(jugadorNo == 1) {
					if (! modoJuego.equals("Maquina contra maquina")) {
						jugador1 = "Luigi";
					}else {
						jugador1 = "Luigi-Protector";
					}
				}else {
					if (modoJuego.equals("Dos jugadores")) {
						jugador2 = "Luigi";
					}else {
						jugador2 = "Luigi-Protector";
					}
				}
				confirmePersonajeElegido(x, "luigi");
				empezarjuego();
			}
		};
		opcion3.addActionListener(oyenteLuigi3);
	}
	
	public void confirmePersonajeElegido(int x, String jugador) {
		if (jugador.equals("mario")) {
			opcionesMario.setVisible(false);
		}else {
			opcionesLuigi.setVisible(false);
		}
			JLabel listo = new JLabel("Listo...");
			listo.setBounds(x + 100, 200, 200, 40);
			listo.setFont(new Font("Agency FB", Font.BOLD, 40));
			listo.setForeground(Color.WHITE);
			panel.add(listo);
	}
	
	
	public void empezarjuego() {
		if((jugador1 != null && jugador2 != null) || (jugador1 != null && modoJuego.equals("Un jugador"))) {
			DonkeyPOOBGUI juego = new DonkeyPOOBGUI(anterior, modoJuego, jugador1, jugador2);
			juego.setVisible(true);
		}
	}
}