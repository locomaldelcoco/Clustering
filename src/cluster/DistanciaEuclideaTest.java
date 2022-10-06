package cluster;

import static org.junit.Assert.*;

import org.junit.Test;

public class DistanciaEuclideaTest {

	@Test(expected = NullPointerException.class)
	public void primerPuntoNullTest() {
		DistanciaEuclidea.distancia(null, new Vertice(1, 0, 0));
	}

	@Test(expected = NullPointerException.class)
	public void segundoPuntoNullTest() {
		DistanciaEuclidea.distancia(new Vertice(1, 0, 0), null);
	}

	@Test(expected = NullPointerException.class)
	public void ambosPuntosNullTest() {
		DistanciaEuclidea.distancia(null, null);
	}

	@Test
	// punto 1: x,y ; punto 2: x,y
	public void distanciaEntrePuntosDistintosPositivosTest() {
		double distancia = DistanciaEuclidea.distancia(new Vertice(10, 10, 0), new Vertice(20, 20, 1));
		assertEquals(14.142 * 112, distancia, 3);
	}

	@Test
	// punto 1: -x,-y ; punto 2: -x,-y
	public void distanciaEntrePuntosNegativosTest() {
		double distancia = DistanciaEuclidea.distancia(new Vertice(-30, -20, 0), new Vertice(-10, -50, 1));
		assertEquals(36.056 * 112, distancia, 3);
	}

	@Test
	// punto 1: -x,y ; punto 2: x,-y
	public void distanciaEntrePuntosDisparejosTest() {
		double distancia = DistanciaEuclidea.distancia(new Vertice(-30, 20, 0), new Vertice(10, -50, 1));
		assertEquals(80.623 * 112, distancia, 3);
	}

	@Test
	public void distanciaEntrePuntosIgualesTest() {
		double distancia = DistanciaEuclidea.distancia(new Vertice(100, 100, 0), new Vertice(100, 100, 1));
		assertEquals(0, distancia, 0);
	}

	@Test
	public void distanciaEntrePuntosEnCeroTest() {
		double distancia = DistanciaEuclidea.distancia(new Vertice(0, 0, 0), new Vertice(0, 0, 1));
		assertEquals(0, distancia, 0);
	}
}
