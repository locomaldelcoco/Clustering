package cluster;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArcoTest {
	private Grafo g;
	
	@Before
	public void setUp() {
		g = new Grafo(); 
		g.crearGrafo("6");
		g.completarGrafo();
	}
	
	@Test
	public void testEqualsObject() {
		              // arco(0,1)          arco(1,0)
		assertEquals(g.getArcos().get(0), g.getArcos().get(1));
	}

}
