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
		this.espacio = ancho;
		this.libros = new ArrayList <Libro>();
	}
	
	
	//validacion estante:
	// valida si hay espacio en el estante pasando por parametro el ancho de un libro.
	
	public boolean hayEspacio(double anchoLibro) {
		if (anchoLibro<this.espacio) {
			return true;
		}
		return false;
	}
	
	// retorna el espacio libre del estante, si no esta rotulado lanza una excepcion.
	
	public double espacioLibre() {
		if (this.categoria=="") {
			throw new RuntimeException("El estante no esta rotulado por lo tanto no hay libros");
		}
		return this.espacio;
	}
	
	// setea el espacio libre, agrega mas espacio tomando como parametro el ancho del libro.
	
	public void setMasEspacio(double anchoLibro) {
		this.espacio = this.espacio+anchoLibro; 
	}
	
	// idem metodo anterior pero quita espacio en el estante
	
	public void setMenosEspacio(double anchoLibro) {
		if (this.hayEspacio(anchoLibro)) {
			this.espacio = this.espacio-anchoLibro;
		}
	}
		
	// retorna true si esta vacio el estante y false si esta con libros.
	
	public boolean estaVacio() {
		return libros.isEmpty();
	}
	
	// retorna la categoria del estante
	
	public String getCategoria () {
		return this.categoria;		
	}	
	
	// retorna la cantidad de libros que tiene el estante

	public int cantLibros() {
		return this.libros.size();
	}
	
	// getter de los libros del estante.
	
	public ArrayList <Libro> getLibros() {
		return this.libros;
	}
	
	// permite setear la categoria del estante (luego en el TAD BDUNGS voy a modificar para que solo setee si el estante esta vacio).
	
	public void setCategoria(String cat) {
		this.categoria = cat;
	}

	// agrega un libro al estante (pasado por parametro). Ademas setea el espacio del estante.
	
	public void agregarLibro(Libro libro) {
		if (this.hayEspacio(libro.getAncho())) { 
			libros.add(libro);
			this.setMenosEspacio(libro.getAncho());
		}
		
	}
	
	// retorna true si existe libro con ese ISBN en el estante y false si no ecuentra ese ISBN en el estante.
	
	public boolean existeLibro(String ISBN) {
		for (Libro libro : this.libros) {
			if (libro.getISBN().equals(ISBN)) {
				return true;
			}
		}
		return false;
	}
	
	// paso por parametro un ISBN y me devuelve el objeto Libro que posee ese ISBN dentro del estante.
	
	public Libro libroConISBN (String ISBN) {
		Libro ret =null;
		for (Libro libro : this.libros) {
			if (libro.getISBN().equals(ISBN)) {
				ret = libro;
			}
		}
		return ret;
	}
	
	// recorro los libros con iteradores y elimino el libro si es que existe (ademas agrego espacio al estante si se elimina).
	
	public void eleminiarLibro(String ISBN) {
		if (this.existeLibro(ISBN)) {
			this.setMasEspacio(this.libroConISBN(ISBN).getAncho());
			Iterator<Libro> it = libros.iterator();
			while (it.hasNext()) {
				String id = it.next().getISBN();
				if (id.equals(ISBN)) {
					it.remove();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Categoria: ").append(this.categoria).append(", Libros: ");
		for (Libro libro : this.libros) {
			sb.append(libro.getISBN()).append(" / ");
		}
		return sb.toString();
	}
	
}
