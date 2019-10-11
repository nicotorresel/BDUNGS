package tad_bdungs;

public class Libro {
	private String ISBN;
	private String categoria;
	private String nombre;
	private double anchoLibro;


	Libro(String ISBN, String categ, String name, double width){
		this.ISBN = ISBN;
		this.categoria = categ;
		this.nombre = name;
		this.anchoLibro = width;
		
	}
	
	public String getISBN () {
		return this.ISBN;
	}
		
	public String getCategoria() {
		return this.categoria;
	}
	public String getNombre() {
		return this.nombre;
	}
	public double getAncho() {
		return this.anchoLibro;
	}

}
