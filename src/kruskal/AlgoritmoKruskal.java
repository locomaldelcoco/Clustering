package kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import cluster.Arco;
import cluster.Grafo;
import cluster.Vertice;
import bfs.BFS;

public class AlgoritmoKruskal{
	
	public static Grafo Kruskal(Grafo g) {
		Grafo agm = new Grafo();
		ArrayList<Vertice> nuevoVertices = agm.getVertices();
		ArrayList<Arco> nuevoArcos = agm.getArcos();

		ArrayList<Arco> arcos = g.getArcos();
		Collections.sort(arcos, Collections.reverseOrder());
		
		while (nuevoArcos.size() != g.getVertices().size()-1) {
			Arco arista = arcos.get(0);
			Vertice vA = arista.getVerticeA();
			Vertice vB = arista.getVerticeB();
			
			if (nuevoVertices.contains(vA) && nuevoVertices.contains(vB)) {
				//BFS
				HashSet<Vertice> alcanzables = (HashSet<Vertice>) BFS.alcanzables(vA);
				if (!alcanzables.contains(vB)) {
					arcos.add(arista);
				}
			}
			else if (nuevoVertices.contains(vA)) {
				nuevoVertices.add(vB);
				nuevoArcos.add(arista);
			}
			else if (nuevoVertices.contains(vB)) {
				nuevoVertices.add(vA);
				nuevoArcos.add(arista);
			}
			else {
				nuevoVertices.add(vA);
				nuevoVertices.add(vB);
				nuevoArcos.add(arista);
			}
			nuevoArcos.remove(0);
		}
		
		
		return agm;
	}
	
}