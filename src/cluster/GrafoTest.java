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
		//el grafo cargado debe tener tamano 9
		assertEquals(9, g.tamano());
	}
	@Test
	public void completarGrafoTest() {
		g.completarGrafo();
		//n*(n-1)/2 cantidad de aristas grafo completo
		assertEquals(g.tamano()*(g.tamano()-1)/2, g.getArcos().size());
	}
}
