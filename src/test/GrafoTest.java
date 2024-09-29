package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import logica.Grafo;
import logica.Vertice;
import recursos.Arista;

public class GrafoTest {
	private static Grafo grafo;
	private static Vertice vertice1,vertice2,vertice3,vertice4,vertice5;
	private static Arista arista1,arista2,arista3;
	
	@Before
	public void inicializarGrafo() {
		grafo = new Grafo();
		
		vertice1 = new Vertice(1);
		vertice2 = new Vertice(2);
		vertice3 = new Vertice(3);
		vertice4 = new Vertice(4);
		vertice5 = new Vertice(5);
		
		vertice1.agregarVecino(vertice2);
		vertice1.agregarVecino(vertice3);
		vertice1.agregarVecino(vertice4);
		
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
		grafo.agregarVertice(vertice4);

		arista1 = new Arista(vertice1,vertice2);
		arista2 = new Arista(vertice1,vertice3);
		arista3 = new Arista(vertice1,vertice4);
		
		grafo.agregarArista(arista1);
		grafo.agregarArista(arista2);
		grafo.agregarArista(arista3);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validezArista() {
		Grafo grafoTamanioUno = new Grafo();
		Vertice prueba1 = new Vertice(1);
		Vertice prueba2 = new Vertice(1);
		grafoTamanioUno.agregarArista(new Arista(prueba1,prueba2));	
	}
	
	@Test(expected = NullPointerException.class)
	public void verticeNoValido() {
		grafo.agregarVertice(null);
	}
	
	
	
	@Test
	public void comprobarTamanio() {
		assertEquals(4, grafo.getVertices().size());
	}
	
	@Test
	public void comprobarAristas() {
		assertEquals(3,grafo.getAristas().size());
	}
	
	@Test
	public void buscarVerticeFunciona() {
		assertEquals(grafo.buscarVertice(1), vertice1);
	}
	
	@Test
	public void agregarVerticeFunciona() {
		grafo.agregarVertice(vertice5);
		assertEquals(grafo.getVertices().size(), 5);
	}
	
	@Test
	public void agregarAristaFunciona() {
		Arista ar1 = new Arista(vertice1,vertice5);
		grafo.agregarArista(ar1);
		assertEquals(grafo.getAristas().size(), 4);
	}
	
	@Test
	public void hacerVecinosFunciona() {
		grafo.hacerVecinos(vertice1, vertice5);
		assertEquals(vertice1.getVecinos().size(), 4);
	}
	
	@Test
	public void existeVerticeFunciona() {
		assertEquals(grafo.existeVertice(1), true);
	}
	
}