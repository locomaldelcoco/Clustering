package cluster;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
	private Grafo _grafo;
	private ArrayList<Arco> _arcosGrafo;
	public Kruskal(Grafo grafo) {
		_grafo = grafo;
		_arcosGrafo = new ArrayList<>();
		_arcosGrafo = _grafo.getArcos();
		ordenarArcos();
	}
	private void ordenarArcos() {
		Collections.sort(_arcosGrafo);
		Collections.reverse(_arcosGrafo);
	}
	public Grafo AGM() {
		ArrayList<Arco> _arcosAGM = new ArrayList<>();
		_arcosAGM.add(_arcosGrafo.get(0));
		for (int i = 1; i < _arcosGrafo.size()-1; i++) {
			if (!formanCircuito(_arcosGrafo.get(i), _arcosAGM)) {
				_arcosAGM.add(_arcosAGM.get(i));
			}
		}
		return null;
	}
	private boolean formanCircuito(Arco arco, ArrayList<Arco> _arcosAGM) {
		
		return false;
	}
}
