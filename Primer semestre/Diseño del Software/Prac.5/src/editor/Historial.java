package editor;

import java.util.Stack;
import cambio.Cambio;

public class Historial {
	
	public Historial(){
		cambiosUndo = new Stack<Cambio>();
		cambiosRedo = new Stack<Cambio>();
	}
	
	public void redo(Dibujo dibujo){
		if(cambiosRedo.size() == 0){
			System.out.println("No se puede hacer redo");
		}
		else{
			Cambio redo = cambiosRedo.pop();
			dibujo.cambiarFigura(redo.getAntes(), redo.getDespues());
			cambiosUndo.push(redo);
		}
	}
	
	public void undo(Dibujo dibujo){
		if(cambiosUndo.size() == 0){
			System.out.println("No se puede hacer undo");
		}
		else{
			Cambio undo = cambiosUndo.pop();
			dibujo.cambiarFigura(undo.getDespues(), undo.getAntes());
			cambiosRedo.push(undo);
		}
	}
	
	public void registrarCambio(Cambio cambio){
		//System.out.println(cambio);
		cambiosUndo.push(cambio);
		cambiosRedo = new Stack<Cambio>();
	}
	
	private Stack<Cambio> cambiosUndo;
	private Stack<Cambio> cambiosRedo;
	

}
