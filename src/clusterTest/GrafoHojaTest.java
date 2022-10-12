package clusterTest;

import cluster.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GrafoHojaTest {
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
	
	@Test
	public void hojaAisladaTest() {
	    Grafo g = new Grafo();
	    Vertice vA = new Vertice(10, 10);
	    Vertice vB = new Vertice(10, 20);
	    Vertice vC = new Vertice(20, 10);
	    Vertice vD = new Vertice(20, 20);
	    g.agregarVertice(vA);
	    g.agregarVertice(vB);
	    g.agregarVertice(vC);
	    g.agregarVertice(vD);
	    
	    g.agregarArco(vA, vB, 10);
	    g.agregarArco(vC, vD, 10);
	    assertTrue(g.esHojaAislada(g.getArcos().get(0)) && g.esHojaAislada(g.getArcos().get(1)));
	}

	@Test
	public void hojaNoAisladaTest() {
	    Grafo g = new Grafo();
	    Vertice vA = new Vertice(10, 10);
	    Vertice vB = new Vertice(10, 20);
	    Vertice vC = new Vertice(20, 10);
	    g.agregarVertice(vA);
	    g.agregarVertice(vB);
	    g.agregarVertice(vC);
	    
	    g.agregarArco(vA, vB, 10);
	    g.agregarArco(vB, vC, 10);
	    assertFalse(g.esHojaAislada(g.getArcos().get(0)));
	}

}
