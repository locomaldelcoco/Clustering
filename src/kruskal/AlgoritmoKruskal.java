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
		ArrayList<Vertice> nuevoVertices = agm.getVertices();
		ArrayList<Arco> nuevoArcos = agm.getArcos();

		ArrayList<Arco> arcos = g.getArcos();
		Collections.sort(arcos, Collections.reverseOrder());
		
		while (nuevoArcos.size() != g.getVertices().size()-1) {
			System.out.println(nuevoArcos.size() + " --- " + (g.getVertices().size()-1));
		
			Arco arista = new Arco(arcos.get(0).getVerticeA(), arcos.get(0).getVerticeB(), arcos.get(0).getDistancia());
			Vertice verticeInicial = arista.getVerticeA();
			Vertice vA = new Vertice(verticeInicial.get_x(), verticeInicial.get_y());

			Vertice verticeTerminal = arista.getVerticeB();
			Vertice vB = new Vertice(verticeTerminal.get_x(), verticeTerminal.get_y());
			
			System.out.println("VERTICES : " + nuevoVertices);
			System.out.println("ARCOS : " + nuevoArcos);

			System.out.println(vA + "<--- Vertice A");
			System.out.println(vB + "<--- Vertice B");
		
			if (nuevoVertices.contains(vA) && nuevoVertices.contains(vB)) {
				//BFS
				Vertice verticeBFS = agm.getVertice(vA);
				HashSet<Vertice> alcanzables = (HashSet<Vertice>) BFS.alcanzables(verticeBFS);
				System.out.println("Alcanzables de origen: " + alcanzables);
				if (!alcanzables.contains(vB)) {
					agm.agregarArco(vA, vB, DistanciaEuclidea.distancia(vA, vB));
				}
			}
			else if (nuevoVertices.contains(vA) && !nuevoVertices.contains(vB)) {
				System.out.println("vA existe en nuevoVertice");
				nuevoVertices.add(vB);
				agm.agregarArco(vA, vB, DistanciaEuclidea.distancia(vA, vB));
			}
			else if (nuevoVertices.contains(vB) && !nuevoVertices.contains(vA)) {
				System.out.println("vB existe en nuevoVertice");
				nuevoVertices.add(vA);
				agm.agregarArco(vA, vB, DistanciaEuclidea.distancia(vA, vB));
			}
			else if (!nuevoVertices.contains(vA) && !nuevoVertices.contains(vB)) {
				System.out.println("no existen en nuevoVertice");
				nuevoVertices.add(vA);
				nuevoVertices.add(vB);
				agm.agregarArco(vA, vB, DistanciaEuclidea.distancia(vA, vB));
			}
			arcos.remove(0);
		}
		
			System.out.println(nuevoVertices);
			System.out.println(nuevoArcos);
		
		return agm;
	}
}