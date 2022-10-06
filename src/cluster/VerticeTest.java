package cluster;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VerticeTest {
	private Vertice vertice;

	@Before
	public void inicializarVertice() {
		vertice = new Vertice(10, 10, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinoNegativoTest() {
		vertice.agregarVecino(-1);
	}

	@Test
	public void agregarVecinoPositivoTest() {
		vertice.agregarVecino(1);
		assertTrue(vertice.get_vecinos().contains(1));
	}

	@Test
	public void agregarVecinoCeroTest() {
		vertice.agregarVecino(0);
		assertTrue(vertice.get_vecinos().contains(0));
	}

}
