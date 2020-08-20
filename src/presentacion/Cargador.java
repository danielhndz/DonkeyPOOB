package presentacion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.ImageIcon;

public class Cargador {
	
	public static HashMap<String, ImageIcon> properties;
	
	public Cargador(String path) {
		loadProperties(path);
	}
	
	private  ImageIcon imageLoader(String path) {
		try {
			ImageIcon img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage());
			return img;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private  void loadProperties(String path) {
		try {
			Properties prop = new Properties();
			InputStream file = new FileInputStream(path);
			prop.load(file);
			properties = new HashMap<String, ImageIcon>();
			for (String key: prop.stringPropertyNames()) {
				String pathImage = prop.getProperty(key);
			    ImageIcon currentImage = imageLoader(pathImage);
				properties.put(key, currentImage);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}	
}