package cluster;

import java.util.ArrayList;
import java.util.Collections;

public class Grafo {
	private ArrayList<Vertice> _vertices;
	private ArrayList<Arco> _arcos;
	private int _CantidadDeClusters;

	public Grafo() {
		_vertices = new ArrayList<>();
		_arcos = new ArrayList<>();
		_CantidadDeClusters = 1;
	}

	public void cargarGrafo(String s) {
		ArrayList<Vertice> coords = GestorArchivos.getCoordenadas(s);
		for (Vertice v : coords)
			agregarVertice(v);
	}

	public void agregarVertice(Vertice v) {
		if (!_vertices.contains(v))
			_vertices.add(v);
	}
	
	public void completarGrafo() {
		for (int i = 0; i < _vertices.size(); i++ ) {
	/*j=i+1*/for (int j=i+1; j < _vertices.size(); j++ ) {
				if (i != j) {
					double distancia = DistanciaEuclidea.distancia(_vertices.get(i), _vertices.get(j));
					System.out.println(distancia);
					Vertice vA = _vertices.get(i);
					Vertice vB = _vertices.get(j);
					agregarArco(vA, vB, distancia);
				}
			}
		}reiniciarCantidadDeClusters();
	}
	
	
	private void reiniciarCantidadDeClusters() {
		_CantidadDeClusters = 0;
	}

	public void agregarArco(Vertice vA, Vertice vB, double distancia) {
		_arcos.add(new Arco(vA, vB, distancia));
		agregarVecinos(vA, vB);
	}
	
	public void eliminarArco(Arco arco) {			
		_arcos.remove(arco);
	}
	
	public void eliminarArcoMasPesado() {
		if(!_arcos.isEmpty()) {
			_arcos.remove( arcoMasPesado() );
		}else{
			throw new IndexOutOfBoundsException("No hay arcos para eliminar");
		}
	}
	
	private Arco arcoMasPesado() {
		Arco pesado = _arcos.get(0);
		for (Arco arco : _arcos) {
			if(pesado.compareTo(arco) == 1) {
				pesado = arco;
			}
		}return pesado;
	}

	private void agregarVecinos(Vertice vA, Vertice vB) {
		vA.agregarVecino(vB);
		vB.agregarVecino(vA);
	}	
	
	public ArrayList<Vertice> getVertices() {
		return _vertices;
	}

	public ArrayList<Arco> getArcos() {
		return _arcos;
	}

	public int tamano() {
		return _vertices.size();
	}

	public Vertice getVertice(Vertice vA) throws IllegalArgumentException {
		for (Vertice v : _vertices) {
			if (v.equals(vA))
				return v;
		}
		throw new IllegalArgumentException("No existe vértice");
	}

}
