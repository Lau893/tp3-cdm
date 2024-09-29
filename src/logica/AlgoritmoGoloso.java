package logica;

import java.util.HashSet;
import java.util.Set;

public class AlgoritmoGoloso {

	public static Set<Vertice> conjuntoDominanteMinimo(Grafo grafo) {
		comprobarValidezDelGrafo(grafo);
		Set<Vertice> conjuntoDominante = new HashSet<>();
		Set<Vertice> verticesNoMarcados = new HashSet<>(grafo.getVertices());
		while (!verticesNoMarcados.isEmpty()) {

			Vertice ver = encontrarVerticeConMasVecinos(verticesNoMarcados);

			conjuntoDominante.add(ver);
			if (vecinosHojas(ver).size() != 0) {
				for (Vertice v : vecinosHojas(ver)) {
					verticesNoMarcados.remove(v);
				}
			} else {
				for (Vertice v : ver.getVecinos()) {
					if (!v.tieneHojas()) {
						verticesNoMarcados.remove(v);
					}
				}
			}
			verticesNoMarcados.remove(ver);
		}

		return conjuntoDominante;
	}
	
	public static Set<Vertice> vecinosHojas(Vertice ver) {
		Set<Vertice> vecinosHojas=new HashSet<>();
		for(Vertice v:ver.getVecinos()) {
			if(v.getVecinos().size()==0) {
				vecinosHojas.add(v);
			}
		}
		return vecinosHojas;
	}
		
	public static Vertice encontrarVerticeConMasVecinos(Set<Vertice> vertices) {
	    Vertice verticeConMasVecinos = null;
	    int maximoVecinos = -1;

	    for (Vertice vertice : vertices) {
	        int cantidadVecinos = vertice.getVecinos().size();;
	        if (cantidadVecinos > maximoVecinos) {
	            maximoVecinos = cantidadVecinos;
	            verticeConMasVecinos = vertice;
	        }
	    }
	    return verticeConMasVecinos;
	}	
	
	public static void comprobarValidezDelGrafo(Grafo grafo) {
		if(grafo == null) 
			throw new NullPointerException("El grafo no puede ser null.");
		if (grafo.consultarTamanioDelGrafo() == 0)
			throw new IllegalArgumentException("El grafo no puede ser tamaño cero.");
		if (grafo.consultarTamanioDelGrafo() == 1)
			throw new IllegalArgumentException("El grafo no puede ser tamaño uno.");
	}
	
}
