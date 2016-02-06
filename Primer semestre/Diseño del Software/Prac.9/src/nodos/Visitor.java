package nodos;

public interface Visitor {
	
	Object visit(Asignacion asignacion, Object param);
	Object visit(ConstanteInt constante, Object param);
	Object visit(Division division, Object param);
	Object visit(Print print, Object param);
	Object visit(Producto producto, Object param);
	Object visit(Programa prog, Object param);
	Object visit(Read read, Object param);
	Object visit(Suma suma, Object param);
	Object visit(Variable variable, Object param);

}
