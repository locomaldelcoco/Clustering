package cluster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GrafoTest {
	private Grafo g;

	@Before
	public void setUp() {
		g = new Grafo();
		g.cargarGrafo("instancia6.txt");
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

}
