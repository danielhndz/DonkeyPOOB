package presentacion;

import java.awt.*;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;

@SuppressWarnings("serial")
public class MenuGUI extends JFrame{
	
	private JButton btnJugar;
	private Image imagenFondo;
	private URL fondo;
	
	private MenuGUI() {
		this.setTitle("DonkeyPOOB");
		prepareElementos();
		prepareAcciones();
	}
	
	public JPanel panelMenu = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(imagenFondo,0,0,getWidth(), getHeight(), this);
		}
	};
	
	private void prepareElementos() {		
		panelMenu.setLayout(null);
		this.getContentPane().add(panelMenu);
		this.setSize(550, 440);
		this.setLocationRelativeTo(null);
		panelMenu.setBackground(Color.BLACK);
		panelMenu.setSize(550, 440);
		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(135, 250, 130, 30);
		btnJugar.setBackground(Color.BLACK);
		btnJugar.setFont(new Font("arial", Font.BOLD,20));
		btnJugar.setForeground(new Color(245, 182, 72));
		panelMenu.add(btnJugar);
	    fondo = this.getClass().getResource("fondoMenu.png");
	    imagenFondo = new ImageIcon(fondo).getImage();	    
	}
	
	private void prepareAcciones() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JFrame thisFrame = this;
		ActionListener oyenteJugar = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ModoDeJuego modo = new ModoDeJuego(thisFrame);
				modo.setVisible(true);
			}
		};

		btnJugar.addActionListener(oyenteJugar);
	}
	
	public static void main (String args[]) {
		MenuGUI menuGUI = new MenuGUI();
		menuGUI.setVisible(true);
	}
}

