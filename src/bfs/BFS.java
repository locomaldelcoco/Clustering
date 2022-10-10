package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cluster.Grafo;
import cluster.Vertice;

public class BFS {

	private static List<Vertice> pendientes;
	private static Set<Vertice> marcados;
	
	public static boolean esConexo(Grafo g) {
		if (g == null) {
			throw new IllegalArgumentException("El grafo no puede ser null.");
		}
		
		return g.tamano() == 0 || alcanzables(g.getVertices().get(0)).size() == g.tamano();
	}

	public static HashSet<Vertice> alcanzables(Vertice origen) {
		if (origen == null) {
			throw new IllegalArgumentException("No puede ser null el parametro");
		}
		HashSet<Vertice> ret = new HashSet<Vertice>();
		inicializarBusqueda(origen);
		
		while (!pendientes.isEmpty()) {
			Vertice v = seleccionarYMarcarVertice();
			if(!v.equals(origen))
				ret.add(v);

			agregarVecinosNoMarcados(v);
			removerSeleccionado();
		}
		return ret;
	}

	private static void removerSeleccionado() {
		pendientes.remove(0);
	}

	private static void agregarVecinosNoMarcados(Vertice vertice) {		
		//recorrer vecinos del vértice
		for (Vertice vecino : vertice.get_vecinos()){
			if (!marcados.contains(vecino) && !pendientes.contains(vecino))
				pendientes.add(vecino);
		}
	}

	private static Vertice seleccionarYMarcarVertice() {
		Vertice seleccionado = pendientes.get(0);
		marcados.add(seleccionado);
		return seleccionado;
	}

	private static void inicializarBusqueda(Vertice origen) {
		pendientes = new LinkedList<Vertice>();
		marcados = new HashSet<Vertice>();
		pendientes.add(origen);
	}
	

}

