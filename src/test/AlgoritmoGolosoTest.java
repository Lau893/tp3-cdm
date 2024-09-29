package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import logica.AlgoritmoGoloso;
import logica.Grafo;
import logica.Vertice;

public class AlgoritmoGolosoTest {
	private static Vertice vertice1,vertice2,vertice3,vertice4;
	private static Grafo grafo;
	private static Set<Vertice> cdm;

	@Before
	public void inicializarGrafo() {
		grafo = new Grafo();
		vertice1 = new Vertice(1);
		vertice2 = new Vertice(2);
		vertice3 = new Vertice(3);
		vertice4 = new Vertice(4);
		
		vertice1.agregarVecino(vertice2);
		vertice1.agregarVecino(vertice3);
		vertice1.agregarVecino(vertice4);
		
		grafo.agregarVertice(vertice1);
		grafo.agregarVertice(vertice2);
		grafo.agregarVertice(vertice3);
		grafo.agregarVertice(vertice4);
		
		cdm = AlgoritmoGoloso.conjuntoDominanteMinimo(grafo);
	}
	
	@Test(expected = NullPointerException.class)
	public void GrafoNulo() {
		AlgoritmoGoloso.comprobarValidezDelGrafo(null);
	}
	@Test(expected = IllegalArgumentException.class)
	public void GrafoTamanioCero() {
		Grafo grafoTamanioCero = new Grafo();	
		AlgoritmoGoloso.comprobarValidezDelGrafo(grafoTamanioCero);
	}
	@Test(expected = IllegalArgumentException.class)
	public void GrafoTamanioUno() {
		Grafo grafoTamanioUno = new Grafo();
		grafoTamanioUno.agregarVertice(new Vertice(3));
		AlgoritmoGoloso.comprobarValidezDelGrafo(grafoTamanioUno);
	}
	
	@Test
	public void existeCDM() {
		 assertNotNull(cdm.size());
	}

	@Test
	public void comprobarTamanioCDM() {
		assertEquals(cdm.size(), 1);
	}
	
	@Test
	public void encontrarVerticeConMasVecinos() {
		assertEquals(3, AlgoritmoGoloso.encontrarVerticeConMasVecinos(cdm).getVecinos().size());
	}
	
	@Test
	
	public void vecinosHojas() {
		assertEquals(3, AlgoritmoGoloso.vecinosHojas(vertice1).size());
	}
	
}
