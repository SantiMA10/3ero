package decoradores;

import java.io.IOException;

import outputs.Output;

public class EliminarEspaciosRepetidos extends DecoradorAbstract{
	
	private char anteriorChar;
	
	public EliminarEspaciosRepetidos(Output output) {
		super(output);
	}

	@Override
	public void send(char c) throws IOException {
		if((c != ' ') || (anteriorChar != ' ' && c == ' ')){
			output.send(c);
		}
		anteriorChar = c;
	}

}
