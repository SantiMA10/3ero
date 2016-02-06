package campos;

public class CampoSueldo extends AbstractCampo{
	
	int mayorQue = 800;
	int menorQue = 1200;
		
	public CampoSueldo(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public boolean isTipoCorrecto(char ch) {
		return Character.isDigit(ch);
	}

	@Override
	public boolean isValido() {
		int sueldo = Integer.parseInt(texto);
		return 800 < sueldo && sueldo < 1200;
	}

}
