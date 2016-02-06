package decoradores;

import java.io.IOException;

import outputs.Output;

public class Encriptar extends DecoradorAbstract{
		
	public Encriptar(Output output) {
		super(output);
	}

	@Override
	public void send(char c) throws IOException {
		if(Character.isDigit(c) || Character.isLetter(c))
			output.send((char)((int)c + 1));
		else
			output.send(c);
	}


}
