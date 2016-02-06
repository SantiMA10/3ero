package campos;

public class CampoCodigoProducto extends AbstractCampo{
	
	int longitud = 4;
	int longitudActual;
	
	public CampoCodigoProducto(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public boolean isTipoCorrecto(char ch) {
		longitudActual++;
		return true;
	}

	@Override
	public boolean isValido() {
		boolean check = longitud == longitudActual;
		this.longitudActual = 0;
		return check;
	}

}
