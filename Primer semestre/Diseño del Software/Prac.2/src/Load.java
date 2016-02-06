
public class Load extends AbstractMemoria{

	@Override
	public void ejecutar() {
		
		int direccion = new Pop().recuperar();
		new Push(memoria[direccion]).ejecutar();

	}

}
