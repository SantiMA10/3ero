package decoradores;

import java.io.IOException;
import outputs.Output;

public class Normalizar extends DecoradorAbstract{

	public Normalizar(Output output) {
		super(output);
	}

	@Override
	public void send(char c) throws IOException {
		if(c != '\r'){
			output.send(c);
		}
	}

}
