package tad_bdungs;

public class Main {

	public static void main(String[] args) {
		BDUNGS bd = new BDUNGS(10, 100);
		bd.rotularEstante("Computacion", 1);							// rotulo estantes computacion y matematica
		bd.rotularEstante("​Matematica​", 2);
		bd.rotularEstante("Computacion", 4);
		bd.ingresarLibro("9789684443457​","Computacion", "Estructuras de datos", 70);					// agrego libro a computacion
		bd.ingresarLibro("9788415552222​", "Computacion", "Estructuras de datos en Java", 10);			// agrego libro a computacion
		bd.ingresarLibro("​9389557783457​", "​Matematica​", "​Analisis de Funciones", 4);					// agrego libro a matematica
		bd.ingresarLibro("3492349284488", "Computacion", "blabla", 15);									// agrego libro a computacion
		bd.ingresarLibro("4342423423444", "Computacion", "qadasd", 25);									// agrego libro a computacion
		System.out.println(bd);						// imprimo la biblioteca(los libros de computacion se acomodan en dos estantes por que sino no entran
		bd.eliminarLibro("9789684443457​");			//  elimino un libro de computacion
		System.out.println(bd);						// imprimo y el libro computacion eliminado ya no aparece en la biblioteca
		bd.reacomodarCategoria("Computacion");		// reacomodo la categoria Computacion y se reacomodan todos los libros en el estante con mas espacio.	
		System.out.println(bd);						// imprimo biblioteca reacomodada
		System.out.println("el espacio libre del estante n4 es: "+ bd.espacioLibre(4));
		bd.eliminarLibro("​9389557783457​");
		bd.rotularEstante("​Análisis Matemático​", 2);
		//System.out.print(bd);
		

	}

}
