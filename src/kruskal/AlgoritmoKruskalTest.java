package kruskal;

import static org.junit.Assert.*;

import org.junit.Test;

import cluster.Grafo;
import cluster.Vertice;
import cluster.Arco;

public class AlgoritmoKruskalTest {

	@Test
	public void kruskalTest() {
		Grafo g = new Grafo();
		Vertice v1 = new Vertice(0, 0);
		Vertice v2 = new Vertice(1, 5);
		Vertice v3 = new Vertice(2, 6);
		Vertice v4 = new Vertice(10, 60);
		g.agregarVertice(v1);
		g.agregarVertice(v2);
		g.agregarVertice(v3);
		g.agregarVertice(v4);
		g.completarGrafo();
		Grafo agm = AlgoritmoKruskal.kruskal(g);
		assertEquals(3, agm.getArcos().size());
	}

}
