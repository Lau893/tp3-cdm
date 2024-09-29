package test;

import logica.Vertice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VerticeTest {
	private static Vertice vertice1, vertice2, vertice3;
	@Before
	public void inicializarVertices() {
		vertice1 = new Vertice(1);
		vertice2 = new Vertice(2);
		vertice3 = new Vertice(3);
		
		vertice1.agregarVecino(vertice2);
		vertice1.agregarVecino(vertice3);
		vertice3.agregarVecino(new Vertice(4));
	}
	
	@Test (expected = IllegalArgumentException.class)
	
	public void idInvalido() {
		new Vertice(-1);
	}
	@Test
	public void agregarVecino() {
		vertice1.agregarVecino(new Vertice(4));
		assertEquals(vertice1.getVecinos().size(),3);
	}
	
	@Test
	public void quitarVecino() {
		vertice1.quitarVecino(vertice2);
		assertEquals(1, vertice1.getVecinos().size());
	}
	
	@Test
	public void existeVecino() {
		assertTrue(vertice1.existeVecino(vertice2));
	}
	
	@Test
	public void tieneHojas() {
		assertTrue(vertice1.tieneHojas());
	}
	
	@Test
	public void noTieneHojas() {
		assertFalse(vertice2.tieneHojas());
	}	
}
