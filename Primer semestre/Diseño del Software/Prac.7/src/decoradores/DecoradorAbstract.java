package decoradores;

import java.io.IOException;

import outputs.Output;

public abstract class DecoradorAbstract implements Output{
	
	protected Output output;
	
	public DecoradorAbstract(Output output) {
		this.output = output;
	}
	
	@Override
	public abstract void send(char c) throws IOException;

	@Override
	public void close() throws IOException {
		output.close();
	}

}
