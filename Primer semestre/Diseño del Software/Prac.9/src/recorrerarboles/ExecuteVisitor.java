package recorrerarboles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import nodos.Asignacion;
import nodos.ConstanteInt;
import nodos.Division;
import nodos.Expresion;
import nodos.Print;
import nodos.Producto;
import nodos.Programa;
import nodos.Read;
import nodos.Sentencia;
import nodos.Suma;
import nodos.Variable;
import nodos.Visitor;

public class ExecuteVisitor  implements Visitor{

	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Asignacion asigna, Object param) {
		Map<Variable, Expresion> pila = null;
		if(param == null){
			pila = new HashMap<Variable, Expresion>();
		}
		else{
			pila = (Map<Variable, Expresion>) param;
		}	
		pila.put(asigna.variable, asigna.expr);
		
		return pila;
	}

	@Override
	public Object visit(ConstanteInt cte, Object param) {
		return Integer.parseInt(cte.valor);
	}

	@Override
	public Object visit(Division div, Object param) {
		@SuppressWarnings("unchecked")
		Map<Variable, Expresion> pila = (Map<Variable, Expresion>) param;
		int valor1 = (int) pila.get(div.left).accept(this, param);
		int valor2 = (int) pila.get(div.right).accept(this, param);
		System.out.println();
		return valor1/valor2;
	}

	@Override
	public Object visit(Print print, Object param) {
		System.out.print("print ");
		print.expr.accept(this, param);
		System.out.println(";");
		return param;
	}

	@Override
	public Object visit(Producto prod, Object param) {
		@SuppressWarnings("unchecked")
		Map<Variable, Expresion> pila = (Map<Variable, Expresion>) param;
		int valor1 = (int) pila.get(prod.left).accept(this, param);
		int valor2 = (int) pila.get(prod.right).accept(this, param);
		return valor1*valor2;
	}

	@Override
	public Object visit(Programa prog, Object param) {
		for (Sentencia sent : prog.sentencias)
			param = sent.accept(this, param);
		return param;
	}

	@Override
	public Object visit(Read read, Object param) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(read.var.name + ": ");
		String valor = "";
		try {
			valor = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Asignacion(read.var, new ConstanteInt(valor)).accept(this, param);
	}

	@Override
	public Object visit(Suma suma, Object param) {
		@SuppressWarnings("unchecked")
		Map<Variable, Expresion> pila = (Map<Variable, Expresion>) param;
		int valor1 = (int) pila.get(suma.left).accept(this, param);
		int valor2 = (int) pila.get(suma.right).accept(this, param);
		return valor1+valor2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object visit(Variable var, Object param) {
		System.out.print(var.name);
		if(param != null)
			return ((Map<Variable, Expresion>) param).get(var);
		else
			return null;
	}
}
