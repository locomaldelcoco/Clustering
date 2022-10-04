package cluster;

import java.util.ArrayList;

public class Grafo {
	private ArrayList<Vertice> _vertices;
	private ArrayList<Arco> _arcos;

	public Grafo() {
		_vertices = new ArrayList<>();
		_arcos = new ArrayList<>();
	}

	public void crearGrafo(int n) {
		ArrayList<Vertice> coords = Filter.coordenadas(n);
		for (Vertice v : coords)
			agregarVertice(v);
	}

	private void agregarVertice(Vertice v) {
		_vertices.add(v);
	}
	
	public void completarGrafo() {
		for (int i = 0; i < _vertices.size(); i++ ) {
	/*j=i+1*/for (int j=i+1; j < _vertices.size(); j++ ) {
				if (i != j) {
					double distancia = DistanciaEuclidea.distancia(_vertices.get(i), _vertices.get(j));
					System.out.println(distancia);
					agregarArco(i, j, distancia);
				}
			}
		}
	}

	public void agregarArco(int indexA, int indexB, double distancia) {
		if (indexA < 0 || indexB < 0 || indexA >= _vertices.size() || indexB >= _vertices.size())
			throw new IllegalArgumentException("Indice no válido");

		_arcos.add(new Arco(_vertices.get(indexA), _vertices.get(indexB), distancia));
		agregarVecinos(indexA, indexB);
	}
	
	public void eliminarArco(int numArco) {			
		if (numArco < 0 || numArco > _arcos.size()) {
			throw new IndexOutOfBoundsException("el indice es menor o mayor al tamano del arco");
		}
		_arcos.remove(numArco);
	}
	
	private void agregarVecinos(int indexA, int indexB) {
		_vertices.get(indexA).agregarVecino(indexB);
		_vertices.get(indexB).agregarVecino(indexA);
	}	
	
	public ArrayList<Vertice> getVertices() {
		return _vertices;
	}

	public ArrayList<Arco> getArcos() {
		return _arcos;
	}
}
