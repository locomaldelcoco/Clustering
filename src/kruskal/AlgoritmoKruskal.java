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
	
	static boolean numeroOptimo;
	public static void calcularClusters(Grafo agm) {
		Arco pesado = agm.arcoMasPesado();
		boolean esHoja = true;
		System.out.println("VECINOS DE B: "+pesado.getVerticeB().get_vecinos().toString());
		System.out.println("VECINOS DE A: "+pesado.getVerticeA().get_vecinos().toString());
		if( (pesado.getVerticeA().get_vecinos().size() == 0 || pesado.getVerticeA().get_vecinos().size() == 1 ) 
								||
			(pesado.getVerticeB().get_vecinos().size() == 0 || pesado.getVerticeB().get_vecinos().size() == 1) ) {
			
			esHoja = true;
			
			if(esHoja && numeroOptimo){
				System.out.println("----------NUMERO OPTIMO DE CLUSTERS----------"+ agm.getCantidadDeClusters());
				System.out.println("Hay: "+ agm.getCantidadDeClusters() + " Clusters");
				JOptionPane.showMessageDialog(null, "NUMERO OPTIMO DE CLUSTERS\n"+ agm.getCantidadDeClusters());
				numeroOptimo=false;
			}
			System.out.println("ES HOJA Y Hay: "+ agm.getCantidadDeClusters() + " Clusters");
		}
		if(pesado.getVerticeA().get_vecinos().size() > 1 && pesado.getVerticeB().get_vecinos().size() > 1 ) {
			esHoja = false;
			numeroOptimo = true;
			agm.sumarCluster(!esHoja);
			System.out.println("NO ES HOJA Y Hay: "+ agm.getCantidadDeClusters() + " Clusters");
		}
		
	}
	
}