package nodos;

import java.util.*;

public class Programa implements Nodo {
    public List<Sentencia> sentencias;
    
    public Programa(List<Sentencia>  sentencias) {
        this.sentencias = sentencias;
    }

	@Override
	public Object accept(Visitor v, Object param) {
		return v.visit(this, param);
	}
}
