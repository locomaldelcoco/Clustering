package cluster;

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
		assertTrue(vertice.get_vecinos().contains(v1));
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
}