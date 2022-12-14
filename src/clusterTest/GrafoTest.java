package clusterTest;

import static org.junit.Assert.*;
import cluster.*;
import org.junit.Before;
import org.junit.Test;


public class GrafoTest {
	private Grafo g;

	@Before
	public void setUp() {
		g = new Grafo();
		g.cargarGrafo("instancia6.json");
	}

	@Test
	public void cargarGrafoTest() {
		// el grafo cargado debe tener tamano 9
		assertEquals(9, g.tamano());
	}

	@Test
	public void completarGrafoTest() {
		g.completarGrafo();
		// n*(n-1)/2 cantidad de aristas grafo completo
		assertEquals(g.tamano() * (g.tamano() - 1) / 2, g.getArcos().size());
	}

	@Test
	public void agregarVerticeTest() {
		Vertice vertice = new Vertice(10, 20);
		int tamanoAnterior = g.tamano();
		g.agregarVertice(vertice);
		assertEquals(tamanoAnterior + 1, g.tamano());
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarVerticeNullTest() {
		g.agregarVertice(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarArcoTestNull() {
		g.agregarArco(null, null, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarArcoDistanciaNegativaTest() {
		Vertice vA = new Vertice(1, 0);
		Vertice vB = new Vertice(1, 2);
		g.agregarArco(vA, vB, -20);

	}

	@Test
	public void agregarArcoTest() {
		Vertice vA = new Vertice(1, 0);
		Vertice vB = new Vertice(1, 0);
		g.agregarArco(vA, vB, 0);
		assertEquals(1, g.getArcos().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarArcoNullTest() {
		g.eliminarArco(null);
	}

	@Test
	public void eliminarArcoTest() {
		g.completarGrafo();
		g.eliminarArco(g.getArcos().get(0));
		assertEquals((g.tamano() * (g.tamano() - 1) / 2) - 1, g.getArcos().size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void eliminarArcoMasPesadoVacioTest() {
		g.eliminarArcoMasPesado();
	}

	@Test
	public void eliminarArcoMasPesadoTest() {
		Arco arcoMasPesado = null;
		double distanciaMasGrande = Double.MIN_VALUE;
		g.completarGrafo();
		for (int i = 0; i < g.getArcos().size(); i++) {
			if (g.getArcos().get(i).getDistancia() > distanciaMasGrande) {
				distanciaMasGrande = g.getArcos().get(i).getDistancia();
				arcoMasPesado = g.getArcos().get(i);
			}
		}
		g.eliminarArcoMasPesado();
		assertFalse(g.getArcos().contains(arcoMasPesado));
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarVerticeNullTest() {
		g.eliminarVertice(null);
	}

	@Test
	public void eliminarVerticeTest() {
		g.completarGrafo();
		int tamanoGrafo = g.tamano();
		g.eliminarVertice(g.getVertices().get(0));
		assertEquals(tamanoGrafo - 1, g.tamano());
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarArcoDeVerticeNullTest() {
		g.eliminarArcosDeVertice(null);
	}

	@Test
	public void eliminarArcoDeVerticeTest() {
		Vertice v1 = new Vertice(10, 10);
		Vertice v2 = new Vertice(20, 20);
		Vertice v3 = new Vertice(30, 30);
		g.agregarVertice(v1);
		g.agregarVertice(v2);
		g.agregarVertice(v3);
		g.agregarArco(v1,v2,10);
		g.agregarArco(v2,v3,10);
		g.eliminarArcosDeVertice(v2);
		assertTrue(g.getArcos().size() == 0);
	}

	@Test
	public void eliminarArcoDeVerticeSinArcoTest() {
		g.completarGrafo();
		int cantidadArcos = g.getArcos().size();
		Vertice v = new Vertice(1, 2);
		g.agregarVertice(v);
		g.eliminarArcosDeVertice(v);
		assertEquals(cantidadArcos, g.getArcos().size());
	}
}