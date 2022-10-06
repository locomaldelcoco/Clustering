package cluster;

import static org.junit.Assert.*;

import org.junit.Test;

public class BFSTest {

	@Test(expected = IllegalArgumentException.class)
	public void grafoNullTest() {
		BFS.esConexo(null);
	}

	@Test
	public void grafoVacioTest() {
		assertTrue(BFS.esConexo(new Grafo()));
	}

	@Test
	public void grafoUnVerticeTest() {
		Grafo g = new Grafo();
		g.agregarVertice(new Vertice(0, 0, 0));
		assertTrue(BFS.esConexo(g));
	}

	@Test
	public void grafoDosVerticesAisladosTest() {
		Grafo g = new Grafo();
		g.agregarVertice(new Vertice(0, 0, 0));
		g.agregarVertice(new Vertice(1, 1, 1));
		assertFalse(BFS.esConexo(g));
	}

	@Test
	public void grafoDosVerticesConexoTest() {
		Grafo g = new Grafo();
		g.agregarVertice(new Vertice(0, 1, 0));
		g.agregarVertice(new Vertice(0, 2, 1));
		g.agregarArco(0, 1, 2);
		;
		assertTrue(BFS.esConexo(g));
	}

	@Test
	public void grafoInconexoTest() {
		Grafo g = inicializarGrafoInconexo();

		assertFalse(BFS.esConexo(g));
	}

	@Test
	public void grafoConexoTest() {
		Grafo g = inicializarGrafoInconexo();

		g.agregarArco(4, 6, 2);
		assertTrue(BFS.esConexo(g));
	}

	@Test
	public void alcanzablesInconexoTest() {
		Grafo g = inicializarGrafoInconexo();

		int[] esperado = { 0, 1, 2, 3, 4 };
		Assert.iguales(esperado, BFS.alcanzables(g, 0));
	}

	private Grafo inicializarGrafoInconexo() {
		Grafo g = new Grafo();
		g.agregarVertice(new Vertice(0, 1, 0));
		g.agregarVertice(new Vertice(0, 2, 1));
		g.agregarVertice(new Vertice(0, 3, 2));
		g.agregarVertice(new Vertice(0, 4, 3));
		g.agregarVertice(new Vertice(0, 5, 4));
		g.agregarVertice(new Vertice(0, 6, 5));
		g.agregarVertice(new Vertice(0, 7, 6));
		g.agregarArco(0, 1, 1);
		g.agregarArco(0, 2, 1);
		g.agregarArco(1, 2, 1);
		g.agregarArco(1, 3, 1);
		g.agregarArco(2, 4, 1);
		g.agregarArco(3, 4, 1);
		g.agregarArco(5, 6, 1);
		return g;

	}
}
