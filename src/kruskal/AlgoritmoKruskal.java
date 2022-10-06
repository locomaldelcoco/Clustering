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
	
	public boolean hayCiclo(Grafo grafo, Arco arco, Vertice vertice) {
		ArrayList<Vertice> aux = (ArrayList<Vertice>)vertice.get_vecinos();
		if(aux.isEmpty())
			return false;
		return true;
		
	}
}