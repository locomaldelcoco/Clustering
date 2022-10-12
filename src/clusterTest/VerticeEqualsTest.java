package clusterTest;
import cluster.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class VerticeEqualsTest {
	Vertice verticeA;
	Vertice verticeB;

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

	@SuppressWarnings("unlikely-arg-type")
	public void distintoObjetoTest() {
		verticeA = new Vertice(0, 0);
		assertFalse(verticeA.equals("OBJECT"));
	}

}
