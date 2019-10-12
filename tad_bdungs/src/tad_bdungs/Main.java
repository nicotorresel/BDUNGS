package tad_bdungs;


import java.util.HashSet;
import java.util.Set;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BDUNGS bd = new BDUNGS (10,100);
		bd.rotularEstante("Computacion", 1);
		bd.rotularEstante("Matematica", 2);
		bd.ingresarLibro("9789684443457", "Computacion", "Estructuras de datos", 5);
		bd.ingresarLibro("9788415552222", "Computacion", "Estructuras de datos en Java", 7);
		bd.ingresarLibro("9389557783457", "Matematica", "Analisis de Funciones", 4);	
		System.out.println(bd);
		bd.eliminarLibro("9389557783457");
		bd.rotularEstante("Analisis Matematico", 2);
		System.out.println(bd);
		System.out.print(bd.espacioLibre(2));

		
//		Libro lib1 = new Libro("1234","computaciaon","holwdfsfa", 53);
//		Libro lib2 = new Libro("1234","computacisdfson","hodla", 33);
//		
//		Set <Libro> libros = new HashSet <Libro>();
//		
//		libros.add(lib1);
//		libros.add(lib2);
//		
//		for (Libro libro : libros) {
//			System.out.println(libro.getISBN()+" "+ libro.getCategoria());
//		}
//		System.out.println(lib1.equals(lib2));
	}
}
