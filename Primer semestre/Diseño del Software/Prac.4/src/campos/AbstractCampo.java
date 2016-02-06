package campos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class AbstractCampo implements Campo{
	
	String etiqueta;
	String texto;

	@Override
	public void pideDato() {
		BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));

		do {
			try {
				System.out.print(etiqueta + ": ");
				texto = consola.readLine();

				for (char ch : texto.toCharArray()) {
					if (!isTipoCorrecto(ch)) {
						break;
					}
				}
			} catch (IOException ex) {
				System.out.println(ex);
			}
		} while (!isValido());
		
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return texto;
	}
	
	public abstract boolean isTipoCorrecto(char ch);
	public abstract boolean isValido();

}
