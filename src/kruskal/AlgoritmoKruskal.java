package kruskal;

import java.util.ArrayList;
import java.util.Iterator;

import cluster.Arco;
import cluster.Grafo;
import cluster.Vertice;

public class AlgoritmoKruskal{
	
	public Grafo Kruskal(Grafo grafo) {
		
		Grafo grafoMinimo = new Grafo();
		ArrayList<Vertice> vertices = grafo.getVertices();
		
		for (int i = 0; i < vertices.size(); i++) {
			grafoMinimo.completarGrafo();
		}
		
		ArrayList<Arco> L =(ArrayList<Arco>) grafo.getArcos().clone();
		
		Arco primerArco = L.get(0);
		
		return grafo;
	}
	
	public boolean hayCiclo(Grafo grafo, Arco arcoAVerificar, Vertice verticeTerminal, Vertice verticeAChequear) {
		ArrayList<Arco> aux = grafo.getArcos();
		if( aux.isEmpty())
			return false;
		if( verticeTerminal.sonVecinos( arcoAVerificar.getVerticeA() ) ) {
			return true;
		}
		
		
		for (int i = 0; i < aux.size(); i++) {
			Arco otroArco = aux.get(i);
			if( !otroArco.getVerticeB().equals(verticeAChequear) ) {
				if( hayCiclo (grafo, arcoAVerificar, grafo.getArcos().get(i).getVerticeB(),verticeAChequear)) {
					return true;
				}
			}
		}return false;
	}
	
	
}