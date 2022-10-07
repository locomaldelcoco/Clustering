package cluster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GrafoTest {
	private Grafo g;

	@Before
	public void setUp() {
		g = new Grafo();
	}
	@Test
	public void cargarGrafoTest() {
		g.cargarGrafo("instancia6.txt");
		assertEquals(9, g.tamano());
	}

}
