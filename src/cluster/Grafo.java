package cluster;

import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.annotations.Expose;

public class Grafo {
	@Expose
	private ArrayList<Vertice> _vertices;
	@Expose
	private ArrayList<Arco> _arcos;

	public Grafo() {
		_vertices = new ArrayList<>();
		_arcos = new ArrayList<>();
	}

	public void cargarGrafo(String s) {
		Grafo g = GestorArchivos.cargarGrafo(s);
		_vertices = g.getVertices();
		_arcos = g._arcos;
		inicializarVecinos();
	}

	public void inicializarVecinos() {
		for (Vertice v : _vertices)
			v.inicializarVecinos();
	}

	public boolean agregarVertice(Vertice v) {
		if (_vertices.contains(v))
			return false;
		_vertices.add(v);
		return true;
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
		}
		ordenarArcos();
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
			System.out.println("Se eliminó " + arcoMasPesado().getDistancia());
			_arcos.remove(arcoMasPesado());
		}else{
			throw new IndexOutOfBoundsException("No hay arcos para eliminar");
		}
		
	}
	
	private Arco arcoMasPesado() {
		return _arcos.get(0);
	}
	
	private void ordenarArcos() {
		Collections.sort(_arcos, Collections.reverseOrder());
		if (_arcos.isEmpty()) {
			return;
		}	
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
	
	@Override
	public String toString() {
		return "Vertices= " + _vertices + ", Arcos=" + _arcos;
	}
}
