package campos;

public class CampoCodigoPostal extends AbstractCampo{
	
	int longitud = 5;
	int longitudActual;
	
	public CampoCodigoPostal(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public boolean isTipoCorrecto(char ch) {
		longitudActual ++;
		
		return Character.isDigit(ch);
	}

	@Override
	public boolean isValido() {
		boolean check = longitud == longitudActual;
		longitudActual = 0;
		
		return check;
	}

}
