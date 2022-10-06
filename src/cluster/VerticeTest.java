package cluster;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class VerticeTest {
	private Vertice vertice;
	private Vertice verticeNegativo;
	private Vertice verticePositivo;
	private Vertice vertice0;

	@Before
	public void inicializarVertice() {
		vertice = new Vertice(10, 10);
		verticeNegativo = new Vertice(-11, 11);
		verticePositivo = new Vertice(15, 15);
		vertice0 = new Vertice(0,0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarVecinoNegativoTest() {
		vertice.agregarVecino(verticeNegativo);
	}

	@Test
	public void agregarVecinoPositivoTest() {
		vertice.agregarVecino(verticePositivo);
		assertTrue(vertice.get_vecinos().contains(verticePositivo));
	}

	@Test
	public void agregarVecinoCeroTest() {
		vertice.agregarVecino(vertice0);
		assertTrue(vertice.get_vecinos().contains(vertice0));
	}

}
