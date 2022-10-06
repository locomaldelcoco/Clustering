package cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class Kruskal {
	private Grafo _grafo;
	private ArrayList<Arco> _arcosGrafo;
	private ArrayList<HashSet<Integer>> _grafoAuxiliar;
	public Kruskal(Grafo grafo) {
		_grafo = grafo;
		_arcosGrafo = new ArrayList<>();
		_arcosGrafo = _grafo.getArcos();
		ordenarArcos();
		_grafoAuxiliar = new ArrayList<>();
		for (int i = 0; i < _grafo.tamano(); i++) {
			_grafoAuxiliar.add(new HashSet<Integer>());
		}
	}
	private void ordenarArcos() {
		Collections.sort(_arcosGrafo);
		Collections.reverse(_arcosGrafo);
	}
	public Grafo AGM() {
		ArrayList<Arco> arcosAGM = new ArrayList<Arco>();
		for (int i = 0; i < _arcosGrafo.size(); i++) {
			Arco a = _arcosGrafo.get(i);
			// agarrar arco màs chiquito
			if (arcosAGM.isEmpty()) {
				arcosAGM.add(new Arco(a.getVerticeA(),a.getVerticeB(), a.getDistancia()));
				_grafoAuxiliar.get(a.getVerticeA().getId()).add(a.getVerticeB().getId());
				_grafoAuxiliar.get(a.getVerticeB().getId()).add(a.getVerticeA().getId());
			}
			else if (_grafoAuxiliar) {
				
			}// estàn los dos vèrtices en el grafo aux?
			
			
			// agrego depediendo el caso, BFS
		}
		return null;
	}
}
