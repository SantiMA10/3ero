import comprobaciones.impl.*;
import comprobaciones.impl.tipo.*;
import comprobaciones.impl.valido.*;
import campos.*;

public class Main {

	public static void main(String[] args) {
				
		Formulario formulario = new Formulario();
		
		formulario.addCampo(new CampoImpl("Codigo postal", new ComprobacionImpl(new ComprobacionTipoInt(), new ComprobacionValidoLongitud(5))));
		formulario.addCampo(new CampoImpl("Codigo producto", new ComprobacionImpl(new ComprobacionTipoInt(), new ComprobacionValidoLongitud(4))));
		formulario.addCampo(new CampoImpl("Codigo promocio", new ComprobacionOr(new ComprobacionImpl(new ComprobacionTipoString(), new ComprobacionValidoCualquiera()),
																				new ComprobacionImpl(new ComprobacionTipoInt(), new ComprobacionValidoLongitud(3)))));
		formulario.addCampo(new CampoImpl("Edad", new ComprobacionImpl(new ComprobacionTipoInt(), new ComprobacionValidoMayor(18))));
		formulario.addCampo(new CampoImpl("Numero", new ComprobacionImpl(new ComprobacionTipoInt(), new ComprobacionValidoCualquiera())));
		formulario.addCampo(new CampoImpl("Predefinido", new ComprobacionImpl(new ComprobacionTipoString(), new ComprobacionValidoPredefinido("Santander", "Oviedo", "Cadiz"))));
		formulario.addCampo(new CampoImpl("Sueldo", new ComprobacionImpl(new ComprobacionTipoInt(), new ComprobacionValidoEntre(800, 1200))));
		formulario.addCampo(new CampoImpl("Texto", new ComprobacionImpl(new ComprobacionTipoString(), new ComprobacionValidoCualquiera())));
		formulario.addCampo(new CampoImpl("Ubicacion", new ComprobacionOr(new ComprobacionImpl(new ComprobacionTipoInt(), new ComprobacionValidoLongitud(5)),
																		  new ComprobacionImpl(new ComprobacionTipoString(), new ComprobacionValidoPredefinido("Santander", "Oviedo", "Cadiz")))));
		
//		formulario.addCampo(new CampoCodigoPromocion("Codigo Promocion"));
//		formulario.addCampo(new CampoUbicacion("Ubicacion", "Santander", "Oviedo", "Cadiz"));
//		formulario.addCampo(new CampoSueldo("Sueldo"));
//		formulario.addCampo(new CampoCodigoProducto("Codigo de producto"));
//		formulario.addCampo(new CampoCodigoPostal("Codigo postal"));
//		formulario.addCampo(new CampoEdad("Edad"));
//		formulario.addCampo(new CampoTexto("Nombre"));
//		formulario.addCampo(new CampoTexto("Apellido"));
//		formulario.addCampo(new CampoNumero("Telefono"));
//		formulario.addCampo(new CampoPredefinido("Ciudad", "Santander", "Oviedo", "Cadiz"));

		formulario.PideDatos();
	}
	
}
