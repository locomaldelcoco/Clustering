package cluster;

import java.util.ArrayList;


public class AGM {
	protected static Grafo AGM;

	public static Grafo generarAGM(Grafo g) {
		AGM = new Grafo();
		AGM.agregarVertice(new Vertice(g.getVertices().get(0).get_x(),g.getVertices().get(0).get_x(), g.getVertices().get(0).getId()));// Se inicaliza el agm con un solo vertice

		while (AGM.tamano() < g.tamano()) {
			// Busco la proxima arista para extender el agm seg�n Prim
			Arco aristaNueva = aristaMenorParaAgregar(g, AGM);

			// Si el agm tiene al extremo 1 pero no al extremo 2, agrego al 2 y extiendo el
			// agm hacia ese vertice
			if (AGM.contieneAlVertice(aristaNueva.getVerticeA()) && !AGM.contieneAlVertice(aristaNueva.getVerticeB())) {

				AGM.agregarVertice(new Vertice(aristaNueva.getVerticeB().get_x(),aristaNueva.getVerticeB().get_y(), aristaNueva.getVerticeB().getId()));
				AGM.agregarArco(aristaNueva.getVerticeA().getId(), aristaNueva.getVerticeB().getId(),aristaNueva.getDistancia());
			}
			// Si el agm tiene al extremo 2 pero no al extremo 1, agrego al 1 y extiendo el
			// agm hacia ese vertice
			if (AGM.contieneAlVertice(aristaNueva.getVerticeB()) && !AGM.contieneAlVertice(aristaNueva.getVerticeA())) {
				AGM.agregarVertice(new Vertice(aristaNueva.getVerticeA().get_x(),aristaNueva.getVerticeA().get_y(), aristaNueva.getVerticeA().getId()));
				AGM.agregarArco(aristaNueva.getVerticeA().getId(), aristaNueva.getVerticeB().getId(),
						aristaNueva.getDistancia());
			}

		}
		return AGM;

	}

	protected static Arco aristaMenorParaAgregar(Grafo g, Grafo agm) {
		ArrayList<Arco> aristasAgregables = aristasAgregables(g, agm);
		double pesoMenor = aristasAgregables.get(0).getDistancia();
		int indiceMenor = 0;
		// Recorro las aristas agregables de g y guardo la de menor peso
		for (int i = 0; i < aristasAgregables.size(); i++) {
			if (aristasAgregables.get(i).getDistancia() <= pesoMenor) {
				pesoMenor = aristasAgregables.get(i).getDistancia();
				indiceMenor = i;
			}
		}
		return aristasAgregables.get(indiceMenor);
	}

	protected static ArrayList<Arco> aristasAgregables(Grafo g, Grafo agm) {
		ArrayList<Arco> aristasAgregables = new ArrayList<>();
		// Recorro las aristas del grafo original
		for (int i = 0; i < g.getArcos().size(); i++) {
			Vertice extremo1 = g.getArcos().get(i).getVerticeA();
			Vertice extremo2 = g.getArcos().get(i).getVerticeB();
			// Si el AGM contiene a uno de los extremos de la arista pero no al otro, lo
			// agrego a la lista

			if (agm.contieneAlVertice(extremo1) && !agm.contieneAlVertice(extremo2)
					|| agm.contieneAlVertice(extremo2) && !agm.contieneAlVertice(extremo1)) {
				aristasAgregables.add(g.getArcos().get(i));
			}

		}
		return aristasAgregables;
	}
}
