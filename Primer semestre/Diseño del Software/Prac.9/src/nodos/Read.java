package nodos;

public class Read implements Sentencia {
	public Variable var;

	public Read(Variable var) {
		this.var = var;
	}

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
