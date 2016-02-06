
public class Jmpg implements Instruccion{

	private int salto;
	
	public Jmpg(int salto){
		this.salto = salto;
	}
	
	@Override
	public int ejecutar(int ip) {
		Pop pop = new Pop();
		
		int b = pop.recuperar();
		int a = pop.recuperar();
		if (a > b)
			ip = salto;
		else
			ip++;
		
		return ip;
	}

}
