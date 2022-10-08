package bfs;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import cluster.Arco;
import cluster.Grafo;
import cluster.Vertice;

public class BFSTest {
	private Grafo g;
	private Vertice v1;
	private Vertice v2;
	private Vertice v3;

	@Before
	public void inicializarGrafo() {
		g = new Grafo();
		v1 = new Vertice(0, 0);
		v2 = new Vertice(0, 5);
		v3 = new Vertice(10, 0);
		g.agregarVertice(v1);
		g.agregarVertice(v2);
		g.agregarVertice(v3);
	}
	
	@Test
	public void agregarArcoTest() {
		g.agregarArco(v1, v2, 5);
		assertEquals(new Arco(v1, v2, 5), g.getArcos().get(0));
	}
	
	@Test
	public void verticeAlcanzableTest() {
		g.agregarArco(v1, v2, 5);
		HashSet<Vertice> vAlcanzables = new HashSet<Vertice>();
		vAlcanzables.add(v2);
		assertEquals(vAlcanzables,BFS.alcanzables(g.getVertices().get(0)));
	}

	@Test
	public void verticeNoAlcanzableTest() {
		HashSet<Vertice> vAlcanzables = new HashSet<Vertice>();
		vAlcanzables.add(v2);
		assertNotEquals(vAlcanzables,BFS.alcanzables(g.getVertices().get(0)));
	}

}
