package tad_bdungs;

import java.util.ArrayList;

public class Estante {
	private String categoria;
	private double anchoEstante;
	private ArrayList <Libro> libros;  
	
	
	Estante (double ancho){
		this.categoria = "";
		this.anchoEstante = ancho;
		this.libros = new ArrayList <Libro>();
	}
	
	// retorna la suma de los anchos de los libros que hay en el estante
	
	public double espacioOcupado() {
		double ret = 0;
		if (this.libros.isEmpty()) {
			return ret;
		}
		else {
			for (Libro libro : this.libros) {
				ret = ret + libro.getAncho();
			}
			return ret;
		}
	}
	//retorna el espacio disponible en el estante
	
	public double espacioDisponible() {
		if (this.categoria.equals("")) {
			throw new RuntimeException ("El estante todavia no esta rotulado");
		}
		else {
			return this.anchoEstante - espacioOcupado();			
		}
	}
	
	public boolean estaVacio() {
		return libros.isEmpty();
	}
	
// retorna la categoria del estante
	
	public String getCategoria () {
		return this.categoria;
	}

	public int cantLibros() {
		return this.libros.size();
	}
	
	public ArrayList getLibros() {
		return this.libros;
	}
	
//permite setear la categoria del estante (luego en el TAD BDUNGS voy a modificar para que solo setee si el estante esta vacio.
	
	public void setCategoria(String cat) {
		this.categoria = cat;
	}

	public void agregarLibro(Libro libro) {
		libros.add(libro);
	}
	
}
