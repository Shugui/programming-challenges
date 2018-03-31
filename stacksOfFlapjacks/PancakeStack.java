package stacksOfFlapjacks;

import java.util.ArrayList;
import java.util.LinkedList;


public class PancakeStack {

	private ArrayList<Integer> lista;
	private int largestPancake;
	
	public void flip(int n) {
		System.out.println("n: " + n) ;
		System.out.println("Lista preflip: " + lista.toString()) ;

		LinkedList<Integer> aux = new LinkedList<Integer>();
		for (int i = n-1; i < lista.size(); i++) {
			aux.add(lista.get(i));
		}
		System.out.println("Lista aux: " + aux.toString()) ;
			
		
		//Eliminamos el substack
		int size = lista.size();
		for (int i = n-1; i < size; i++) {
			lista.remove(n-1);
		}
		System.out.println("Lista postremove: " + lista.toString()) ;

		
		//Volvemos a insertar el substack ya invertido
		size = aux.size();
		for (int i = size - 1; i >= 0; i--) {
			lista.add(aux.get(i));
		}
		System.out.println("Lista postinsert: " + lista.toString()) ;

	}
	
	public boolean isSorted() {
		for (int i = 0; i < lista.size() - 1; i++) {
			if (lista.get(i) < lista.get(i+1)) {
				return false;
			}
		}
		return true;
	}
	

	public ArrayList<Integer> getStack() {
		return lista;
	}

	public void setStack(ArrayList<Integer> stack) {
		this.lista = stack;
	}

	public PancakeStack() {
		lista = new ArrayList<Integer>();
		largestPancake = 0;
	}


	public int getLargestPancake() {
		return largestPancake;
	}


	public void setLargestPancake(int largestPancake) {
		this.largestPancake = largestPancake;
	}
	
	
	
	

}
