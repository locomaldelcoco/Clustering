package clusterTest;

import cluster.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VerticeVecinosTest {
	private Vertice vertice;

	@Before
	public void inicializarVertice() {
		vertice = new Vertice(10, 10);
	}

	@Test
	public void agregarVecinoTest() {
		Vertice v1 = new Vertice(1, 1);
		vertice.agregarVecino(v1);
		assertTrue(vertice.esVecino(v1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinoNullTest() {
		vertice.agregarVecino(null);
	}

	@Test
	public void eliminarVecinoTest() {
		Vertice v1 = new Vertice(1, 1);
		vertice.agregarVecino(v1);
		vertice.eliminarVecino(v1);
		assertFalse(vertice.esVecino(v1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarVecinoNullTest() {
		vertice.eliminarVecino(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminaVecinoInexistenteTest() {
		Vertice v = new Vertice(10, 2);
		vertice.eliminarVecino(v);
	}

	@Test(expected = IllegalArgumentException.class)
	public void esVecinoNullTest() {
		vertice.esVecino(null);
	}

	@Test
	public void esVecinoTest() {
		Vertice v = new Vertice(100, 200);
		vertice.agregarVecino(v);
		assertTrue(vertice.esVecino(v));
	}

	@Test
	public void noEsVecinoTest() {
		Vertice v = new Vertice(100, 200);
		assertFalse(vertice.esVecino(v));
	}

}