package nodos;

public interface Nodo {
	Object accept(Visitor v, Object param);
}
