import java.util.Scanner;


public class Input extends AbstractInstruccion{

	@Override
	public void ejecutar() {
		
		System.out.println("Escriba un entero:");
		new Push(leerValor()).ejecutar();
		
	}
	
	@SuppressWarnings("resource")
	private int leerValor() {
		return new Scanner(System.in).nextInt();
	}

}
