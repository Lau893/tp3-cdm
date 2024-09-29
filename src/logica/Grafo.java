package logica;

import java.util.HashSet;
import java.util.Set;

import recursos.Arista;

public class Grafo {
	private Set<Vertice> vertices;
	private Set<Arista> aristas;

	public Grafo() {
		this.vertices = new HashSet<>();
		this.aristas = new HashSet<>();
	}
	
	public int consultarTamanioDelGrafo() {
		return vertices.size();
	}

	public void agregarVertice(Vertice v) {
		verticeNulo(v);
		vertices.add(v);
	}
	
	public void agregarArista(Arista a) {
		validezArista(a);
		aristas.add(a);
	}
	
	public void hacerVecinos(Vertice ver1, Vertice ver2) {
		ver1.agregarVecino(ver2);
		ver2.agregarVecino(ver1);
	}
	
	public Vertice buscarVertice(int id) {
	    return vertices.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
	}

	public boolean existeVertice(int id) {
	    return vertices.stream().anyMatch(v -> v.getId() == id);
	}

	public Set<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(Set<Vertice> vertices) {
		this.vertices = vertices;
	}

	public Set<Arista> getAristas() {
		return aristas;
	}

	public void setAristas(Set<Arista> aristas) {
		this.aristas = aristas;
	}
	
	private void verticeNulo(Vertice v) {
		if(v == null) 
			throw new NullPointerException("el vertice no puede ser null");
	}
	
	private void validezArista(Arista a) {
		if (a.getOrigen().getId()==a.getDestino().getId()) 
			throw new IllegalArgumentException("El origen y el destino son iguales: "+ a.getOrigen().getId()); 	
		
	}
	@Override
	public String toString() {
	    StringBuilder datosDelGrafo = new StringBuilder();
	    datosDelGrafo.append("Grafo{")
	      .append("vertices=").append(vertices)
	      .append(", aristas=").append(aristas)
	      .append('}');
	    return datosDelGrafo.toString();
	}

	
}
