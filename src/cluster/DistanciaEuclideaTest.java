package cluster;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class DistanciaEuclideaTest {

	@Test(expected = NullPointerException.class)
	public void primerPuntoNullTest() {
		DistanciaEuclidea.distancia(null, new Point(1, 0));
	}

	@Test(expected = NullPointerException.class)
	public void segundoPuntoNullTest() {
		DistanciaEuclidea.distancia(new Point(1, 0), null);
	}

	@Test(expected = NullPointerException.class)
	public void ambosPuntosNullTest() {
		DistanciaEuclidea.distancia(null, null);
	}

	@Test
	// punto 1: x,y ; punto 2: x,y
	public void distanciaEntrePuntosDistintosPositivosTest() {
		double distancia = DistanciaEuclidea.distancia(new Point(10, 10), new Point(20, 20));
		assertEquals(14.142, distancia, 3);
	}

	@Test
	// punto 1: -x,-y ; punto 2: -x,-y
	public void distanciaEntrePuntosNegativosTest() {
		double distancia = DistanciaEuclidea.distancia(new Point(-30, -20), new Point(-10, -50));
		assertEquals(36.056, distancia, 3);
	}

	@Test
	// punto 1: -x,y ; punto 2: x,-y
	public void distanciaEntrePuntosDisparejosTest() {
		double distancia = DistanciaEuclidea.distancia(new Point(-30, 20), new Point(10, -50));
		assertEquals(80.623, distancia, 3);
	}

	@Test
	public void distanciaEntrePuntosIgualesTest() {
		double distancia = DistanciaEuclidea.distancia(new Point(100, 100), new Point(100, 100));
		assertEquals(0, distancia, 0);
	}

	@Test
	public void distanciaEntrePuntosEnCeroTest() {
		double distancia = DistanciaEuclidea.distancia(new Point(0, 0), new Point(0, 0));
		assertEquals(0, distancia, 0);
	}
}
