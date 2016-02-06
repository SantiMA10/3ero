
public class Push extends AbstractPila implements Instruccion{
	
	private int valor;
	
	public Push(int valor){
		
		this.valor = valor;
		
	}
	
	@Override
	public int ejecutar(int ip) {
		
		ejecutar();
		return ip + 1;
		
	}
	
	public void ejecutar(){
		
		pila[sp] = valor;
		sp++;
		
	}

}
