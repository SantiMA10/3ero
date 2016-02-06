package campos;

public class CampoEdad extends AbstractCampo{
	
	int mayorQue = 18;
	
	public CampoEdad(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public boolean isTipoCorrecto(char ch) {
		return Character.isDigit(ch);
	}

	@Override
	public boolean isValido() {
		return Integer.parseInt(texto) > mayorQue;
	}

}
