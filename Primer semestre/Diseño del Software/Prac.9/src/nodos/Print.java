package nodos;

public class Print implements Sentencia {
	public Expresion expr;

	public Print(Expresion expr) {
		this.expr = expr;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}

}
