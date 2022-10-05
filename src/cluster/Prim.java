package cluster;

import java.util.ArrayList;

public class Prim {
	private Grafo _grafo;
	private ArrayList<Arco> _arcosGrafo;
	public Prim(Grafo grafo) {
		_grafo = grafo;
		_arcosGrafo = new ArrayList<>();
		_arcosGrafo = _grafo.getArcos();
	}
}
