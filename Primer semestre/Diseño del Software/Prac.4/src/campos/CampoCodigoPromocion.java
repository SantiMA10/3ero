package campos;

public class CampoCodigoPromocion extends AbstractCampo{
	
	boolean usoTexto, identificado;
	int longitud = 3, longitudActual;
	
	public CampoCodigoPromocion(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	@Override
	public boolean isTipoCorrecto(char ch) {
		if(!identificado && Character.isLetter(ch)){
			usoTexto = true;
		}
		if(!usoTexto && Character.isDigit(ch)){
			longitudActual++;
			return true;
		}
		if(usoTexto && Character.isLetter(ch)){
			return true;
		}
		return false;
	}

	@Override
	public boolean isValido() {
		boolean check = false;
		if(!usoTexto && longitud == longitudActual){
			check = true;
		}
		else if(usoTexto){
			check = true;
		}
		return check;
	}

}
