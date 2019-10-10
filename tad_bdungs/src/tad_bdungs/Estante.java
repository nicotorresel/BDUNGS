package tad_bdungs;

import java.util.HashSet;

public class Estante {
	private String categoria;
	private double anchoEstante;
	private HashSet <Libro> libros;  // elegi hashset porque no tiene duplicados
	
	
	Estante (double ancho){
		this.categoria = "";
		this.anchoEstante = ancho;
		this.libros = new HashSet <Libro>();
	}
	
	// retorna la suma de los anchos de los libros que hay en el estante
	
	public double espacioOcupado() {
		double ret = 0;
		for (Libro libro : this.libros) {
			ret = ret + libro.getAncho();
		}
		return ret;
	}
	
	//retorna el espacio disponible en el estante
	
	public double espacioDisponible() {
		return this.anchoEstante - espacioOcupado();
	}
	// dice si el estante esta vacio
	//
	public boolean estaVacio() {
		return this.anchoEstante == espacioOcupado();
	}
	
	// retorna la categoria del estante
	
	public String getCategoria () {
		return this.categoria;
	}
	
	public HashSet getLibros() {
		return this.libros;
	}
	
	//permite setear la categoria del estante (luego en el TAD BDUNGS voy a modificar para que solo setee si el estante esta vacio.
	
	public void setCategoria(String cat) {
		this.categoria = cat;
	}
	// recorro Hashset libros y armo un Hashmap
//	public HashMap <String, Integer> librosCategoria () {
//		HashMap <String, Integer> libros = new HashMap <String, Integer>();
//		
//		
//		return libros;
//	}
	public void agregarLibro(Libro libro) {
		libros.add(libro);
	}

}
