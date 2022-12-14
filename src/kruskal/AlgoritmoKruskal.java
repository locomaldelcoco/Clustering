package kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.JOptionPane;

import cluster.Arco;
import cluster.DistanciaEuclidea;
import cluster.Grafo;
import cluster.Vertice;
import bfs.BFS;

public class AlgoritmoKruskal{
	
	public static Grafo kruskal(Grafo g) {
		if (g == null) {
			throw new IllegalArgumentException("El grafo no puede ser null");
		}
		Grafo agm = new Grafo();
		ArrayList<Arco> arcos = g.getArcos();
		Collections.sort(arcos, Collections.reverseOrder());
		if (arcos.size() == 0) {
			return g;
		}
		
		while (agm.getArcos().size() != g.getVertices().size()-1) {
			Arco arista = new Arco(arcos.get(0).getVerticeA(), arcos.get(0).getVerticeB(), arcos.get(0).getDistancia());
			Vertice vA = new Vertice(arista.getVerticeA().get_x(), arista.getVerticeA().get_y());
			Vertice vB = new Vertice(arista.getVerticeB().get_x(), arista.getVerticeB().get_y());
			
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
	
	
	public static boolean puedoEliminarArista(Grafo agm) {
		boolean puedoEliminarArista = agm.getPuedoEliminarArista();
		Arco pesado = agm.arcoMasPesado();
		boolean esHoja = true;
		System.out.println("VECINOS B"+ pesado.getVerticeB().get_vecinos().toString() + "   "+ pesado.getVerticeA());
		System.out.println("VECINOS A"+ pesado.getVerticeA().get_vecinos().toString() + "   "+ pesado.getVerticeB());
		
		if( agm.esHoja(pesado)) {
			esHoja = true;
			
			if(esHoja && puedoEliminarArista){
				puedoEliminarArista=false;
			}
			System.out.println("ES HOJA Y Hay: "+ agm.getCantidadDeClusters() + " Clusters");
		}
		else if( !agm.esHoja(pesado)) {
			esHoja = false;
			puedoEliminarArista = true;
			agm.sumarCluster();
			System.out.println("NO ES HOJA Y Hay: "+ agm.getCantidadDeClusters() + " Clusters");
		}
		else if( agm.esHoja(pesado) && agm.esHojaAislada(pesado)) {
			agm.restarCluster();
		}
		return puedoEliminarArista;
		
	}
	
}