package tad_bdungs;

import java.util.ArrayList;

public class BDUNGS {

	private int cantEstantes;
	private double anchoEstantes;
	private ArrayList<Estante> estantes;
	
	BDUNGS (int cantEstantes, double anchoEstantes){

		this.cantEstantes = cantEstantes;
		this.anchoEstantes = anchoEstantes;
		
		for (int i = 0; i<cantEstantes; i++) {
			estantes.add(new Estante(anchoEstantes));
		}
	}
	
	public boolean ingresarLibro (String ISBN, String categoria, String nombre, double ancho) {
		return true;
	}
	
	//rotulo el estante pasado por parametro siempre que se encuentre vacio (sin libros)
	public void rotularEstante (String rotulo, int numEstante) {
		if (estantes.get(numEstante).estaVacio()) {
			estantes.get(numEstante).setCategoria(rotulo);
		}
		
	}
	public void eliminarLibro (String ISBN) {
		
	}
	public void reacomodarCategoria (String categoria) {
		
	}
	public hashMap <String, Integer> verLibrosCategoria (String categoria) {
		
	}
	
	public double espacioLibre (int numEstante) {
		return estantes.get(numEstante).getEspacioDisponible();
	}
	
}
