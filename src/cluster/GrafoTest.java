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
		g.completarGrafo();
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
