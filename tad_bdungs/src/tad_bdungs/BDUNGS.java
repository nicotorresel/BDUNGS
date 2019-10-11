package tad_bdungs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BDUNGS {
	
	private ArrayList<Estante> estantes;

//-----------CONSTRUCTOR---------------------------------------------------------------------------------------------	

	BDUNGS (int cantEstantes, double anchoEstantes){

		this.estantes = new ArrayList<Estante>();
		
		for (int i = 0; i<cantEstantes; i++) {
			estantes.add(new Estante(anchoEstantes));
		}
	}

//--------------------------------------------------------------------------------------------------------

	public ArrayList<Estante> getEstantes(){
		return this.estantes;
	}
//--------------------------------------------------------------------------------------------------------	

	//devuelve la cantidad de estantes de la biblioteca
	public int tamanio() {
		return estantes.size();
	}

//--------------------------------------------------------------------------------------------------------	
	public boolean exiteCategoria(String categoria) {
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria)) {
				return true;
			}
		}
		return false;
	}
	
//---------------------------------------------------------------------------------------------------------	
	

	public boolean ingresarLibro (String ISBN, String categoria, String nombre, double ancho) {
		// inicio una variable de control en true
		Libro libro = new Libro (ISBN, categoria,nombre,ancho);
		boolean ret = true;		
		
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
					else {
						ret = false;
					}
				}
			}
		}
		return ret;
	}	

//--------------------------------------------------------------------------------------------------------	

	//rotulo el estante pasado por parametro siempre que se encuentre vacio (sin libros)
	
	public void rotularEstante (String rotulo, int numEstante) {
		if (!this.estantes.get(numEstante).estaVacio()) {
			throw new RuntimeException ("El estante que quiere rotular contiene libros y no se puede rotular");
		}
		else {
			this.estantes.get(numEstante).setCategoria(rotulo);
		}
	}
//--------------------------------------------------------------------------------------------------------	
	//En este metodo tengo que usar iteradores para poder eliminar libro de un estante
	// Recorro cada estante con for each y dentro de cada estante uso un iterador para buscar el libro con el mismo ISBN. 
	//cuando encuentro el libro con el mismo ISBN lo remuevo.
	
	public void eliminarLibro (String ISBN) {
		for (Estante estante : this.estantes) {
			estante.eleminiarLibro(ISBN);
			}
	}
		
//--------------------------------------------------------------------------------------------------------	

	public void reacomodarCategoria (String categoria) {
			
	}
//--------------------------------------------------------------------------------------------------------	
	public int cantLibros (String categoria) {
		int cant = 0;
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria)) {
				cant+= estante.cantLibros();
			}
		}
		return cant;
	}
	
//----------------------------------------------------------------------------------------------------------
	
	public double espacioLibre (int numEstante) {
		return estantes.get(numEstante).espacioLibre();
	}	
	
//---------------------------creo un arraylist con los libros de categoria que le paso------------------------------------------------------------------------------
	
	public ArrayList<String> isbnlibros (String categoria){
		ArrayList <String> ret = new ArrayList <String>();
		ArrayList <Libro> lib = new ArrayList <Libro>();
		for (Estante estantes : this.estantes) {
			if (estantes.getCategoria().equals(categoria)) {
				ret.addAll(estantes.getLibros());
			}
		}
		for (Libro libro : lib) {
			ret.add(libro.getISBN());
		}
		return ret;
	}
	
//-----------------------------uso el metodo anterior con la lista de libros de la categoria que le paso---------------------------------------------------------------------------	
	public HashMap <String, Integer> verLibrosCategoria (String categoria) {
		HashMap <String, Integer> ret = new HashMap <String, Integer>();
		ArrayList<String> libros = isbnlibros(categoria);
		for (int i = 0; i<libros.size(); i++) {
			int cont = 0;
			String isbn =libros.get(i);
			for (int j = 0; j<libros.size(); j++) {
				if (libros.get(i).equals(libros.get(j))) {
					cont++;
				}
			}
			ret.put(isbn, cont);
		}
		return ret;
		
		
		
	}
	
	
}
