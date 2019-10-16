package tad_bdungs;

import java.util.ArrayList;
import java.util.HashMap;

public class BDUNGS {
	
	private ArrayList<Estante> estantes;

//----------------------------------------------------------------------------------------------------------------------------------------	
//--------------------------------CONSTRUCTOR---------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------------

	public BDUNGS (int cantEstantes, double anchoEstantes){

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
		if (!this.existeCategoria(categoria)) {
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
			return false;
		}
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
//-------------------------------------------------------------------------------------------------------------------------------------------------------	
	//reacomodar categoria:
	
	public int reacomodarCategoria (String categoria) {
		int ret = 0;
		double space = 00;
		int indice = 0;
		ArrayList<Libro> libros = new ArrayList<Libro>();
		
		//recorro los estantes y me fijo cual estante de esa categoria es el que tiene mayor espacio libre.
		//cuando lo encuentro guardo el index del estante y el espacio vacio que tiene el mismo en las variables space e indice.
		for (int i = 0; i<this.tamanio();i ++) {
			if (this.estantes.get(i).getCategoria().equals(categoria) && this.estantes.get(i).espacioLibre()>space) {
				space = this.estantes.get(i).espacioLibre();
				indice = i;
			}
		}
		 
		// recorro el estante que tiene mas espacio de esa categoria y guardo cada libro en un nuevo arrayList
		// el estante se ubico previamente con la variable indice.
		for (Libro libro : this.estantes.get(indice).getLibros()) {
			libros.add(libro);
		}
		
		// por ultimo recorro cada libro del nuevo arrayList (estante mas vacio de la categoria solicitada) y me fijo 
		// si ese libro entra en alguno de los otros estantes de esa categoria. Si se puede ubicar lo agrego y luego elimino el libro del estante.
		// no hay conflicto de concurrencia porque estoy recorriendo el nuevo arrayList creado y lo uso como iterador.
		for (Libro lib : libros) {
			for (int j=0; j<this.tamanio();j++) {
				if (this.estantes.get(j).getCategoria().equals(categoria) && j!=indice && this.estantes.get(j).hayEspacio(lib.getAncho())) {
					this.estantes.get(j).agregarLibro(lib);
					this.estantes.get(indice).eleminiarLibro(lib.getISBN());
				}
			}
		}
		
		// por ultimo recorro los estantes de la categoria solicitada y si alguno esta vacio lo acumulo en la variable ret para luego retornar ese valor.
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria) && estante.estaVacio()) {
				ret++;
			}
		}
		return ret;
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------



	// verLibrosCategoria:
	// devuelve un hashmap con los ISBN (KEY) de los libros de la categoria pasada por parametro,
	// muestra ademas la cantidad de copias de ese libro (VALUE).
	// uso un metodo auxiliar librosDeCategoria que crea un arraylist con los libros de la categoria que elija,
	// asi me saco el problema de los estantes a recorrer)
	
	public HashMap <String, Integer> verLibrosCategoria (String categoria) {
		HashMap <String, Integer> ret = new HashMap <String, Integer>();
		ArrayList <Libro> lib = this.librosDeCategoria(categoria);
		if (!this.existeCategoria(categoria)) {
			throw new RuntimeException("No existe la categoria"+ categoria);
		}
		else {
			for ( int i = 0; i<lib.size(); i++) {
				Integer cont = 0;
				String id = "";
				for (int j = 0; j<lib.size(); j++) {
					if (lib.get(i).getISBN().equals(lib.get(j).getISBN())) {
						id = lib.get(i).getISBN();
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
	// si el estante no esta rotulado tira una excepcion
	
	public double espacioLibre (int numEstante) {
		if (!this.estantes.get(numEstante).estaRotulado()) {
			throw new RuntimeException("El estante no esta rotulado por lo tanto no hay libros");
		}
		else {
			return this.estantes.get(numEstante).espacioLibre();
		}
	}	

	
//------------------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------METODOS AUXILIARES USADOS PARA LOS METODOS PRINCIPALES---------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------------	
	

	//devuelve la cantidad de estantes de la biblioteca
	
	public int tamanio() {
		return this.estantes.size();
	}

//--------------------------------------------------------------------------------------------------------	
	// boolean que devuelve si existe una categoria pasada por parametro dentro de la biblioteca
	
	public boolean existeCategoria(String categoria) {
		for (Estante estante : this.estantes) {
			if (estante.getCategoria().equals(categoria)) {
				return true;
			}
		}
		return false;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int cont = 0;
		for (Estante estante : this.estantes) {
			sb.append("ESTANTE N°: ").append(cont).append(", ").append(estante.toString()).append("\n");
			cont++;
		}
		return sb.toString();
	}
	
}	
