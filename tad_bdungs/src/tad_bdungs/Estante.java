package tad_bdungs;

import java.util.ArrayList;
import java.util.Iterator;

public class Estante {
	private String categoria;
	private double anchoEstante;
	private double espacio;
	private ArrayList <Libro> libros;  
	
	
	Estante (double ancho){
		this.categoria = "";
		this.anchoEstante = ancho;
		this.espacio = espacio;
		this.libros = new ArrayList <Libro>();
	}
	
	
	//validacion estante:
	
	public boolean hayEspacio(double anchoLibro) {
		if (anchoLibro<this.espacio) {
			return true;
		}
		return false;
	}
	
	public double getEspacio() {
		if (this.categoria=="") {
			throw new RuntimeException("El estante no esta rotulado por lo tanto no hay libros");
		}
		return this.espacio;
	}
	
	public void setMasEspacio(double anchoLibro) {
		this.espacio = this.espacio+anchoLibro; 
	}
	
	public void setMenosEspacio(double anchoLibro) {
		if (this.hayEspacio(anchoLibro)) {
			this.espacio = this.espacio-anchoLibro;
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
		if (!this.estaVacio()) {
			throw new RuntimeException("El estante no se puede rotular porque continene libros");
		}
		else {
			this.categoria = cat;
		}
	}

	public void agregarLibro(Libro libro) {
		if (this.hayEspacio(libro.getAncho())) { 
			libros.add(libro);
			
		}
		
	}
	
	public void eleminiarLibro(String ISBN) {
		Iterator<Libro> it = libros.iterator();
		while (it.hasNext()) {
			String id = it.next().getISBN();
			if (id.equals(ISBN)) {
				it.remove();
			}
		}
		
	}
	
}
