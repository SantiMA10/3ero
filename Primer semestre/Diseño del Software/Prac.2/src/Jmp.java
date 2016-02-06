
public class Jmp implements Instruccion{
	
	private int salto;
	
	public Jmp(int salto){
		this.salto = salto;
	}

	@Override
	public int ejecutar(int ip) {
		return salto;
	}

}
