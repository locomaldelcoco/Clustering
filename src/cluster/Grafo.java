package cluster;

import java.util.ArrayList;
import java.util.Iterator;

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
		if (v == null) {
			throw new IllegalArgumentException("El parametro no puede ser null");
		}
		if (_vertices.contains(v))
			return false;
		_vertices.add(v);
		return true;

	}

	public void completarGrafo() {
		for (int i = 0; i < _vertices.size(); i++) {
			/* j=i+1 */for (int j = i + 1; j < _vertices.size(); j++) {
				if (i != j) {
					double distancia = DistanciaEuclidea.distancia(_vertices.get(i), _vertices.get(j));
					System.out.println(distancia);
					Vertice vA = _vertices.get(i);
					Vertice vB = _vertices.get(j);
					agregarArco(vA, vB, distancia);
				}
			}
		}
	}

	public boolean agregarArco(Vertice vA, Vertice vB, double distancia) {
		if (vA == null || vB == null) {
			throw new IllegalArgumentException("Los vertices no pueden ser null");
		}
		if (distancia < 0) {
			throw new IllegalArgumentException("la distancia entre vertices no puede ser menor a 0: " + distancia);
		}
		Arco a = new Arco(vA, vB, distancia);
		if (_arcos.contains(a)) {
			return false;
		}
		_arcos.add(a);
		agregarVecinos(vA, vB);
		return true;
	}

	public void eliminarArco(Arco arco) {
		if (arco == null) {
			throw new IllegalArgumentException("El parametro no puede ser null");
		}
		_arcos.remove(arco);
	}

	public void eliminarArcoMasPesado() {
		if (!_arcos.isEmpty()) {
			System.out.println("Se eliminó " + arcoMasPesado().getDistancia());
			_arcos.remove(arcoMasPesado());
		} else {
			throw new IndexOutOfBoundsException("No hay arcos para eliminar");
		}

	}

	public void eliminarVertice(Vertice v) {
		if (v == null) {
			throw new IllegalArgumentException("El parametro no puede ser null");
		}
		_vertices.remove(v);
	}

	public void eliminarArcosDeVertice(Vertice v) {
		if (v == null) {
			throw new IllegalArgumentException("El parametro no puede ser null");
		}
		Iterator<Arco> it = _arcos.iterator();
		while (it.hasNext()) {
			Arco a = it.next();
			if (a.contiene(v))
				it.remove();
		}
	}

	public int tamano() {
		return _vertices.size();
	}

	private Arco arcoMasPesado() {
		Arco pesado = _arcos.get(0);
		for (Arco arco : _arcos) {
			if (pesado.compareTo(arco) == 1) {
				pesado = arco;
			}
		}
		return pesado;
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

	public Vertice getVertice(Vertice vA) throws IllegalArgumentException {
		for (Vertice v : _vertices) {
			if (v.equals(vA))
				return v;
		}
		return null;
	}

	@Override
	public String toString() {
		return "Vertices= " + _vertices + ", Arcos=" + _arcos;
	}
}
