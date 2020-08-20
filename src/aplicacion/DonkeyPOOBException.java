package aplicacion;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DonkeyPOOBException extends Exception implements Serializable{
	
	public static final String PERSONAJES_NO_CORRESPONDEN_A_MODO_DE_JUEGO = "Los personajes no corresponden al modo de juego";
	public static final String POSICION_FUERA_DE_LIMITES = "Posicion fuera de limites";
	public static final String NO_EXISTE_ARCHIVO = "No existe el archivo";
	public static final String ERROR_AL_ABRIR = "Error al abrir archivo";
	
	public DonkeyPOOBException (String mensaje) {
		super(mensaje);
	}

}
