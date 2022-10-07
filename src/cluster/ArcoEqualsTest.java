package cluster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArcoEqualsTest {

	private Grafo g;

	@Before
	public void setUp() {
		g = new Grafo();
		g.cargarGrafo("instancia6.txt");
		g.completarGrafo();
	}

	@Test
	public void mismoArcoTest() {
		Arco arco = g.getArcos().get(0);
		assertTrue(g.getArcos().get(0).equals(arco));
	}

	@Test
	public void arcoNullTest() {
		Arco arco = null;
		assertFalse(g.getArcos().get(0).equals(arco));
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void distintoObjetoTest() {
		Vertice vertice = new Vertice(0, 0);
		assertFalse(g.getArcos().get(0).equals(vertice));
	}

	@Test
	public void arcosDistintosTest() {
		Arco arcoDiferente = new Arco(g.getVertices().get(3), g.getVertices().get(2), 2);
		assertFalse(g.getArcos().get(0).equals(arcoDiferente));
	}

	@Test
	public void arcosIgualesTest() {
		Vertice verticeA = new Vertice(g.getArcos().get(0).getVerticeA().get_x(), g.getArcos().get(0).getVerticeA().get_y());
		Vertice verticeB = new Vertice(g.getArcos().get(0).getVerticeB().get_x(), g.getArcos().get(0).getVerticeB().get_y());
		double distancia = g.getArcos().get(0).getDistancia();
		Arco arco = new Arco(verticeA, verticeB, distancia);
		System.out.println(arco);
		System.out.println(g.getArcos().get(0));
		assertTrue(g.getArcos().get(0).equals(arco));
	}

}
