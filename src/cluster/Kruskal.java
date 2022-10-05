package cluster;

import java.util.ArrayList;

public class Kruskal {
	private Grafo _grafo;
	private ArrayList<Arco> _arcosGrafo;
	public Kruskal(Grafo grafo) {
		_grafo = grafo;
		_arcosGrafo = new ArrayList<>();
		_arcosGrafo = _grafo.getArcos();
	}
}
