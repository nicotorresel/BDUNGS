package tad_bdungs;

import java.util.ArrayList;
import java.util.HashMap;

public class BDUNGS {
	
	private ArrayList<Estante> estantes;

//--------------------------------CONSTRUCTOR---------------------------------------------------------------------------------------------	

	BDUNGS (int cantEstantes, double anchoEstantes){

		this.estantes = new ArrayList<Estante>();
		
		for (int i = 0; i<cantEstantes; i++) {
			estantes.add(new Estante(anchoEstantes));
		}
	}
	
	
//------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------METODOS OBLIGATORIOS PEDIDOS PARA EL TP------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//ingresar libro: ingresa un libro y si no existe categoria para ingresarlo retorna un excepcion.
	// ademas retorna true si lo ingresó y si no hay espacio para ingresarlo retorna false.
	
	public boolean ingresarLibro (String ISBN, String categoria, String nombre, double ancho) {
		
		Libro libro = new Libro (ISBN, categoria,nombre,ancho);
		
		if (!this.exiteCategoria(categoria)) {
			throw new RuntimeException("no existe categoria para agregar el libro");
		}
		else {
			for (Estante estante : this.estantes) {
				if ((estante.getCategoria().equals(categoria))) {
					if (estante.hayEspacio(ancho)) {
						estante.agregarLibro(libro);
						return true;
					}
				}
			}
		}
		return false;
	}	

	//rotular estante:
	//rotulo el estante pasado por parametro siempre que se encuentre vacio (sin libros).
	
	public void rotularEstante (String rotulo, int numEstante) {
		if (!this.estantes.get(numEstante).estaVacio()) {
			throw new RuntimeException ("El estante que quiere rotular contiene libros y no se puede rotular");
		}
		else {
			this.estantes.get(numEstante).setCategoria(rotulo);
		}
	}
	
	

	//eliminar libro:
	//recorro cada estante con un for each y luego elimino libro con un metodo de estantes.
	// el metodo estante usa iteradores para poder eliminar el libro. ademas agrega espacio en el estante si se elimina.
	
	public void eliminarLibro (String ISBN) {
		for (Estante estante : this.estantes) {
			estante.eleminiarLibro(ISBN);
		}
	}
	
	//reacomodar categoria:
	
	public void reacomodarCategoria (String categoria) {
		
	}
	
	// verLibrosCategoria:
	// devuelve un hashmap con los ISBN (KEY) de los libros de la categoria pasada por parametro,
	// muestra ademas la cantidad de copias de ese libro (VALUE).
	// uso un metodo auxiliar librosDeCategoria que crea un arraylist con los libros de la categoria que elija,
	// asi me saco el problema de los estantes a recorrer)
	
	public HashMap <String, Integer> verLibrosCategoria (String categoria) {
		if (!this.exiteCategoria(categoria)) {
			throw new RuntimeException("No existe la categoria"+ categoria);
		}
		else {
			HashMap <String, Integer> ret = new HashMap <String, Integer>();
			ArrayList <Libro> lib = this.librosDeCategoria(categoria);
			for ( int i = 0; i<lib.size(); i++) {
				int cont = 0;
				String id = "";
				for (int j = 0; j<lib.size(); j++) {
					if (lib.get(0).getISBN().equals(lib.get(j).getISBN())) {
						cont++;
					}
				}
				ret.put(id, cont);
			}
			return ret;
		}
	}
	
	// espacioLibre:
	// muestra el espacio libre de un estante, pasando el numero de estante por parametro.
	
	public double espacioLibre (int numEstante) {
		return estantes.get(numEstante).espacioLibre();
	}	

	
//------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------METODOS AUXILIARES USADOS PARA LOS METODOS PRINCIPALES---------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	//getter que devuelve el arraylist de estantes.
	
	public ArrayList<Estante> getEstantes(){
		return this.estantes;
	}
//--------------------------------------------------------------------------------------------------------	

	//devuelve la cantidad de estantes de la biblioteca
	
	public int tamanio() {
		return this.estantes.size();
	}

//--------------------------------------------------------------------------------------------------------	
	// boolean que devuelve si existe una categoria pasada por parametro dentro de la biblioteca
	
	public boolean exiteCategoria(String categoria) {
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria)) {
				return true;
			}
		}
		return false;
	}
			
//--------------------------------------------------------------------------------------------------------	
	// dice la cantidad de libros que tiene una categoria pasada por parametro.
	
	public int cantLibros (String categoria) {
		int cant = 0;
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria)) {
				cant+= estante.cantLibros();
			}
		}
		return cant;
	}
	
//--------------------------------------------------------------------------------------------------------
	// creo un arrayList con todos los libros que existen de una categoria pasada por parametro
	
	public ArrayList<Libro> librosDeCategoria (String categoria){
		ArrayList <Libro> ret = new ArrayList <Libro>();
		for (Estante estantes : this.estantes) {
			for (Libro libro : estantes.getLibros()) {
				if (libro.getCategoria().equals(categoria)) {
					ret.add(libro);
				}
			}
		}
		return ret;
	}
}	
