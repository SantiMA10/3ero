
public class Output extends AbstractInstruccion{

	@Override
	public void ejecutar() {
		
		System.out.println(new Pop().recuperar());
		
	}

}
