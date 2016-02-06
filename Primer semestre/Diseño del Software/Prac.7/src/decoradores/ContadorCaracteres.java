package decoradores;

import java.io.IOException;

import outputs.Output;

public class ContadorCaracteres extends DecoradorAbstract{
	
	private int numCaracteres;
	private String mensaje;
	
	public ContadorCaracteres(String mensaje, Output output) {
		super(output);
		numCaracteres = 0;
		this.mensaje = mensaje;
	}

	@Override
	public void send(char c) throws IOException {
		numCaracteres++;
		this.output.send(c);
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		System.out.println(mensaje + ": " + numCaracteres);
	}

}
