package tad_bdungs;

public class Estante {
	private String categoria;
	private double anchoEstante;
	private double anchoDisponible;
	
	
	Estante (double ancho){
		this.categoria = "";
		this.anchoEstante = ancho;
		this.anchoDisponible = ancho;
	}
	public boolean estaVacio() {
		return this.anchoEstante==this.anchoDisponible;
	}
	public void setCategoria(String cat) {
		this.categoria = cat;
	}
	
	public String getCategoria() {
		return this.categoria;
	}
	public double getEspacioDisponible() {
		return this.anchoDisponible; 
	}
	public void setEspacioDisponible(double AnchoLibro) {
		this.anchoDisponible = this.anchoEstante - AnchoLibro;
	}
}
