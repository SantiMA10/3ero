
public class Sub extends AbstractInstruccion{

	@Override
	public void ejecutar() {
		
		Pop pop = new Pop();
		new Push(pop.recuperar() - pop.recuperar()).ejecutar();
		
	}

}
