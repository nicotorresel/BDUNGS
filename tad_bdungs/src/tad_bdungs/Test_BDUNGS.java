package tad_bdungs;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class Test_BDUNGS {

	BDUNGS bd1, bd2;
	
	@Before
	public void setUp() throws Exception {
		// constructor BDUNGS
		// parametros: int, cantidad de estantes (se numeran desde 0 hasta n-1)
		//             double, ancho de cada estante en cm
		bd1 = new BDUNGS(8, 50);
		bd2 = new BDUNGS(10,80);

		// void rotularEstante
		// parametros: String, rotulo
		//             int, numero de estante
		bd1.rotularEstante("Computacion", 1);

		// boolean ingresarLibro (retorna True si fue agregado, False si no hay lugar)
		//                       (lanza excepcion si no existe un estante con la categoria del libro)
		// parametros: String, ISBN
		//             String, categoria
		//             String, nombre
		//             double, ancho en cm
		bd1.ingresarLibro("9789684443457", "Computacion", "Estructuras de datos y algoritmos", 5);
		bd1.ingresarLibro("9788415552222", "Computacion", "Estructuras de datos en Java", 7);

		bd2.rotularEstante("Historia", 2);
		bd2.ingresarLibro("9789875809475", "Historia", "Mitos de la historia argentina 1", 10);
	}

	@Test
	public void testIngresarLibro() {
		// Los libros se deben ingresar en el estante 2
		bd2.ingresarLibro("9789875809482", "Historia", "Mitos de la historia argentina 2", 12);
		bd2.ingresarLibro("9789875803053", "Historia", "Mitos de la historia argentina 3", 14);

		// El espacio libre del estante 2 debe ser 44
		// double espacioLibre (lanza excepcion si el estante no esta rotulado)
		// parametros: int, numero de estante
		assertTrue(bd2.espacioLibre(2)==44.0);
	}

	@Test (expected = RuntimeException.class)
	public void testRotularEstanteNoVacio() {
		// Lanza excepcion porque el estante 1 en la bd1 tiene libros
		bd1.rotularEstante("Historia", 1);
	}

	@Test
	public void testVerLibrosCategoria() {
		// HashMap<String,Integer> verLibrosCategoriaISBN
		//      Retorna un diccionario con clave=ISBN, y valor=cantidad de ejemplares
		//      Lanza excepcion si no hay ningun estante con la categoria
		// parametros: String, categoria
		HashMap<String,Integer> libComp = bd1.verLibrosCategoria("Computacion");
		assertEquals(libComp.size(),2);
		assertTrue(libComp.containsKey("9789684443457"));
		assertTrue(libComp.containsKey("9788415552222"));
	}

	@Test (expected = RuntimeException.class)
	public void testVerLibrosCategoriaSinEstante() {
		// Lanza excepcion porque la bd1 no tiene estantes para Historia
		HashMap<String,Integer> libComp = bd1.verLibrosCategoria("Historia");
	}

	@Test
	public void testEspacioLibre() {
		// El espacio libre del estante 1 en la bd1 debe ser 38
		assertTrue(bd1.espacioLibre(1)==38.0);
		// El espacio libre del estante 2 en la bd2 debe ser 70
		assertTrue(bd2.espacioLibre(2)==70.0);
	}

	@Test(expected = RuntimeException.class)
	public void testEspacioLibre_EstanteSinRotular() {
		// El estante 2 en la bd1 no esta rotulado,
		// por lo que debe lanzar una excepcion
		System.out.println(bd1.espacioLibre(2));
	}

	@Test
	public void testEliminarLibro() {
		double esp = bd1.espacioLibre(1);
		bd1.eliminarLibro("9789684443457");

		// Al eliminar el libro, hay 5 cm mas de espacio libre en el estante
		assertTrue(bd1.espacioLibre(1)==esp+5);

		// Se chequea que el libro no este en la categoria
		HashMap<String,Integer> libComp = bd1.verLibrosCategoria("Computacion");
		assertFalse(libComp.containsKey("9789684443457"));
	}

	@Test
	public void testReacomodarCategoria() {
		// int reacomodarCategoria
		//      Retorna la cantidad de estantes liberados
		//      Lanza excepcion si no hay ningun estante con la categoria
		// parametros: String, categoria
		bd1.ingresarLibro("9789684441234", "Computacion", "Base de datos I", 15);
		bd1.ingresarLibro("9789684449876", "Computacion", "Base de datos II", 10);
		bd1.rotularEstante("Computacion", 3);
		bd1.ingresarLibro("9788415555678", "Computacion", "Programacion C++", 20);
		bd1.eliminarLibro("9789684441234");
		// Se debe liberar un estante
		assertEquals(bd1.reacomodarCategoria("Computacion"),1);
	}
	
	@Test
	public void testToStringBDUNGS() {
		assertNotEquals(null, bd1.toString());
	}

}
