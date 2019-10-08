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
}
