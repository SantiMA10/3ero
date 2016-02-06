package nodos;

public class ConstanteInt implements Expresion {
	public String valor;

	public ConstanteInt(String valor) {
		this.valor = valor;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
