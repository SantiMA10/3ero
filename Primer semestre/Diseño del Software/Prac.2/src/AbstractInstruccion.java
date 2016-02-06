
public abstract class AbstractInstruccion implements Instruccion{

	@Override
	public int ejecutar(int ip) {
		ejecutar();
		return ++ip;
	}
	
	public abstract void ejecutar();

}
