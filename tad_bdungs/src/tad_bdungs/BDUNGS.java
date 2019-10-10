package tad_bdungs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
		// inicio una variable de control en true
		boolean ret = true;
		
		// recorro todos los estantes con un foreach
		//si encuentra la categoria y hay espacio disponible agrega el libro y retorna true;
		// si encuentra la categoria y no hay espacio disponible pone el ret en false y sigue buscando otro estante con esa categoria y espacio
		
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria)) {
				if ((estante.espacioDisponible()>ancho)) {
					Libro libro = new Libro (ISBN, categoria,nombre,ancho);
					estante.agregarLibro(libro);
					return ret;
				}
				else {
					ret = false;
				}
			}
		}
		//si el ret esta en true (quiere decir que recorrio todos los estantes y no encontro la categoria) devuelve excepcion que no hay categoria
		if (ret) {
			throw new RuntimeException("no existe categoria de estante para agregar el libro");
		}
		// si el ret esta en falso es por que se encontro la categoria pero no habia espacio y no hubo otro estante con esa categoria y espacio y retorna falso
		else {
			return ret;
		}
	}
	
	//rotulo el estante pasado por parametro siempre que se encuentre vacio (sin libros)
	public void rotularEstante (String rotulo, int numEstante) {
		if (estantes.get(numEstante).estaVacio()) {
			estantes.get(numEstante).setCategoria(rotulo);
		}
		
	}
	
	//En este metodo tengo que usar iteradores para poder eliminar libro de un estante
	// Recorro cada estante con for each y dentro de cada estante uso un iterador para buscar el libro con el mismo ISBN. 
	//cuando encuentro el libro con el mismo ISBN lo remuevo.
	
	public void eliminarLibro (String ISBN) {
		for (Estante estante : this.estantes) {
			Iterator<Libro> it = estante.getLibros().iterator();
			while (it.hasNext()) {
				String id = it.next().getISBN();
				if (id.equals(ISBN)) {
					it.remove();
				}
			}
		}
		
		
	}
	public void reacomodarCategoria (String categoria) {
		
		
	}
	
	public int cantLibros (String categoria) {
		int cant = 0;
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria)) {
				cant+= estante.cantLibros();
			}
		}
		return cant;
	}
	
	//muestra los ISBN de los libros de una categoria y la cantidad de libros en esa categoria.
	// puede haber mas de un estante con la misma categoria
	// si no hay estante lanza una excepcion.
	
	public HashMap <String, Integer> verLibrosCategoria (String categoria) {
		HashMap<String, Integer> ejemplares = new HashMap<String, Integer>();
		boolean ret = false;
		for (Estante estante : this.estantes) {
			for (Libro libro : estante.getLibros()) {
				if (libro.getCategoria().equals(categoria)) {
					ejemplares.put(libro.getISBN(), this.cantLibros(categoria));
				}
			}
		}	
		return null;
	}
	
	public double espacioLibre (int numEstante) {
		return estantes.get(numEstante).espacioDisponible();
	}
	
}
