
public class Store extends AbstractMemoria{

	@Override
	public void ejecutar() {
		
		int valor = new Pop().recuperar();
		int direccion = new Pop().recuperar();
		memoria[direccion] = valor;
				
	}
	
}
