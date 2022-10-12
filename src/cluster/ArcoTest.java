package cluster;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArcoTest {
	private Vertice v1 = new Vertice(10, 10);
	private Vertice v2 = new Vertice(20, 10);
	private Vertice v3 = new Vertice(30, 10);
	private Arco arco = new Arco(v1, v2, 10);

	@Test
	public void verticeNoContenidoEnArcoTest() {
		assertFalse(arco.contiene(v3));
	}
	
	@Test
	public void verticeContenidoEnArcoTest() {
		assertTrue(arco.contiene(v1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void verticeContenidoNullArcoTest() {
		arco.contiene(null);
	}

	@Test
	public void esHojaTest(){
	    Grafo g = new Grafo();
	    g.cargarGrafo("instancia6.json");
	    Vertice vA = new Vertice(10, 20);
	    Vertice vB = new Vertice(20, 10);
	    g.agregarVertice(vA);
	    g.agregarVertice(vB);
	    g.agregarArco(vA,vB,10);
	    assertTrue(g.esHoja(g.getArcos().get(0)));
	}
	@Test
	public void noEsHojaTest(){
	    Grafo g = new Grafo();
	    g.cargarGrafo("instancia6.json");
	    Vertice vA = new Vertice(10, 10);
	    Vertice vB = new Vertice(10, 20);
	    Vertice vC = new Vertice(20, 10);
	    Vertice vD = new Vertice(20, 20);
	    g.agregarVertice(vA);
	    g.agregarVertice(vB);
	    g.agregarVertice(vC);
	    g.agregarVertice(vD);
	    //cuadrado
	    g.agregarArco(vA,vB,10);
	    g.agregarArco(vB,vC,10);
	    g.agregarArco(vC,vD,10);
	    g.agregarArco(vA,vD,10);
	    assertFalse(g.esHoja(g.getArcos().get(0)));
	}
}
