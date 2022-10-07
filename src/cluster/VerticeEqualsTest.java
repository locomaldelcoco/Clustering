package cluster;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerticeEqualsTest {
	Vertice verticeA;
	Vertice verticeB;
	Vertice verticeC;
	Vertice verticeD;

	@Test
	public void mismoVerticetest() {
		verticeA = new Vertice(10, 10);
		assertTrue(verticeA.equals(verticeA));
	}

	@Test
	public void VerticeNulltest() {
		verticeA = new Vertice(10, 10);
		verticeB = null;
		assertFalse(verticeA.equals(verticeB));
	}

	@Test
	public void VerticesDistintostest() {
		verticeA = new Vertice(10, 10);
		verticeB = new Vertice(10, 20);
		assertFalse(verticeA.equals(verticeB));
	}

	@Test
	public void verticesIgualestest() {
		verticeA = new Vertice(10, 10);
		verticeB = new Vertice(10, 10);
		assertTrue(verticeA.equals(verticeB));
	}

}
