package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import aplicacion.*;

public class DonkeyPOOBDAO {
	
	public static void salve(Escenario juego, File file) throws DonkeyPOOBException{	
		try{	
			FileOutputStream f = new FileOutputStream(file.getAbsolutePath());
			ObjectOutputStream out = new ObjectOutputStream(f);
      			out.writeObject(juego);	
     			out.close();
		}catch(FileNotFoundException e){
			throw new DonkeyPOOBException(DonkeyPOOBException.NO_EXISTE_ARCHIVO);
		}catch(IOException e){
			throw new DonkeyPOOBException(DonkeyPOOBException.ERROR_AL_ABRIR);
		}
	}

}
