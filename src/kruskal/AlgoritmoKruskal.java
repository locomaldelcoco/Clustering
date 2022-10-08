package kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import cluster.Arco;
import cluster.DistanciaEuclidea;
import cluster.Grafo;
import cluster.Vertice;
import bfs.BFS;

public class AlgoritmoKruskal{
	
	public static Grafo kruskal(Grafo g) {
		Grafo agm = new Grafo();
		ArrayList<Arco> nuevoArcos = agm.getArcos();

		ArrayList<Arco> arcos = g.getArcos();
		Collections.sort(arcos, Collections.reverseOrder());
		
		while (nuevoArcos.size() != g.getVertices().size()-1) {
			System.out.println(nuevoArcos.size() + " --- " + (g.getVertices().size()-1));

			Arco aristax = arcos.get(0);
			Arco arista = new Arco(aristax.getVerticeA(), aristax.getVerticeB(), aristax.getDistancia());
			Vertice vX = arista.getVerticeA();
			Vertice vA = new Vertice(vX.get_x(), vX.get_y());
			Vertice vY = arista.getVerticeB();
			Vertice vB = new Vertice(vY.get_x(), vY.get_y());
			if (!agm.getVertices().contains(vA) && !agm.getVertices().contains(vB)) {
				agm.agregarVertice(vB);
				agm.agregarVertice(vA);
				agm.agregarArco(vA, vB, DistanciaEuclidea.distancia(vA, vB));
			}
			else if (agm.getVertices().contains(vA) && agm.getVertices().contains(vB)) {
				Vertice verticeBFS = agm.getVertice(vA);
				HashSet<Vertice> alcanzables = BFS.alcanzables(verticeBFS);
				if (!alcanzables.contains(agm.getVertice(vB))) {
					agm.agregarArco(agm.getVertice(vA), agm.getVertice(vB), DistanciaEuclidea.distancia(vA, vB));
				}
			}
			else if (agm.getVertices().contains(vA)) {
				agm.agregarVertice(vB);
				agm.agregarArco(agm.getVertice(vA), vB, DistanciaEuclidea.distancia(vA, vB));
			}
			else if (agm.getVertices().contains(vB)) {
				agm.agregarVertice(vA);
				agm.agregarArco(vA, agm.getVertice(vB), DistanciaEuclidea.distancia(vA, vB));
			}
			arcos.remove(0);
		}
		return agm;
	}
}