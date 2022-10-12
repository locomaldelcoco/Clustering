package clusterTest;

import cluster.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ArcoTest {
	private Vertice v1 = new Vertice(10, 10);
	private Vertice v2 = new Vertice(20, 10);
	private Vertice v3 = new Vertice(30, 10);
	private Arco arco = new Arco(v1, v2, 10);

	@Test
	public void verticeNoContenidoEnArcoTest() {
		assertFalse(arco.contiene(v3));
	}
	
	@Test
	public void verticeContenidoEnArcoTest() {
		assertTrue(arco.contiene(v1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void verticeContenidoNullArcoTest() {
		arco.contiene(null);
	}

}
