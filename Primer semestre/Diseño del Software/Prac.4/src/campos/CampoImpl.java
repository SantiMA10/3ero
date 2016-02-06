package campos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import comprobaciones.Comprobacion;

public class CampoImpl implements Campo{
	
	String etiqueta;
	String texto;
	Comprobacion comprobacion;
	
	public CampoImpl(String etiqueta, Comprobacion comprobacion) {
		this.etiqueta = etiqueta;
		this.comprobacion = comprobacion;
	}

	@Override
	public void pideDato() {
		BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
		
		do {
			try {
				System.out.print(etiqueta + ": ");
				texto = consola.readLine();
				
			} catch (IOException ex) {
				System.out.println(ex);
			}
		} while (!comprobacion.comprobar(texto));
		
	}

	@Override
	public String getString() {
		return texto;
	}

}

