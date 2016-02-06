package campos;

public class CampoUbicacion extends AbstractCampo{
	
	private String[] opciones;
	private AbstractCampo campoCodigoPostal = new CampoCodigoPostal(etiqueta);
	private boolean usoTexto, identificado;
	
	public CampoUbicacion(String etiqueta, String... opciones) {
		this.etiqueta = etiqueta;
		this.opciones = opciones;
	}

	@Override
	public boolean isTipoCorrecto(char ch) {
		if(!identificado){
			if(Character.isLetter(ch)){
				usoTexto = true;
			}
			identificado = true;
		}
		if(Character.isLetter(ch)){
			return true;
		}
		return campoCodigoPostal.isTipoCorrecto(ch);
	}

	@Override
	public boolean isValido() {
		if(usoTexto){
			for(String opcion: opciones){
				if(opcion.equals(texto))
					return true;
			}
			return false;
		}
		usoTexto = false;
		return campoCodigoPostal.isValido();
	}

}
