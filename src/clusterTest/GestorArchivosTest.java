package clusterTest;

import cluster.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GestorArchivosTest {

	@Test
	public void cargarGrafoTest() {
		Grafo g = GestorArchivos.cargarGrafo("instancia1.json");
		assertTrue(g.tamano() > 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void guardarGrafoNullTest() {
		GestorArchivos.guardarGrafo(null);
	}

}
