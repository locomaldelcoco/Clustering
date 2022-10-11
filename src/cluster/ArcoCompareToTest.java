package cluster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArcoCompareToTest {
	Vertice verticeA;
	Vertice verticeB;
	Vertice verticeC;
	Vertice verticeD;
	Arco arcoA;
	Arco arcoB;

	@Before
	public void setUp() {
		verticeA = new Vertice(10, 10);
		verticeB = new Vertice(10, 20);
		verticeC = new Vertice(10, 30);
		verticeD = new Vertice(10, 40);
	}

	@Test
	public void arcoMasChicoTest() {
		arcoA = new Arco(verticeA, verticeB, 100);
		arcoB = new Arco(verticeA, verticeB, 200);
		assertEquals(1, arcoA.compareTo(arcoB));

	}

	@Test
	public void arcoMasGrandeTest() {
		arcoA = new Arco(verticeA, verticeB, 200);
		arcoB = new Arco(verticeA, verticeB, 100);
		assertEquals(-1, arcoA.compareTo(arcoB));

	}
	@Test
	public void arcosDistanciaIgualesTest() {
		arcoA = new Arco(verticeA, verticeB, 100);
		arcoB = new Arco(verticeA, verticeB, 100);
		assertEquals(0, arcoA.compareTo(arcoB));

	}
}
