package recorrerarboles;


/*
 * No se puede hacer: no hay multiple-dispatch en Java
 */

/*
public class VersionIdeal {

	public void visit(Programa programa) {
		for (Sentencia sentencia:programa.sentencias)
			visit(sentencia);
	}

	public void visit(Print print) {
		System.out.print("print ");
		visit(print.expr);
		System.out.println(";");
	}

	public void visit(Asignacion asigna) {
		visit(asigna.variable);
		System.out.print(" = ");
		visit(asigna.expr);
		System.out.println(";");
	}

	public void visit(Read read) {
		System.out.print("read ");
		visit(read.var);
		System.out.println(";");
	}

	public void visit(Suma suma) {
		visit(suma.left);
		System.out.print(" + ");
		visit(suma.right);
	}

	public void visit(Division div) {
		visit(div.left);
		System.out.print(" / ");
		visit(div.right);
	}

	public void visit(Producto prod) {
		visit(prod.left);
		System.out.print(" * ");
		visit(prod.right);
	}

	public void visit(Variable var) {
		System.out.print(var.name);
	}

	public void visit(ConstanteInt cte) {
		System.out.print(cte.valor);
	}

}
*/
