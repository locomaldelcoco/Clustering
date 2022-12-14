package cluster;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class Vertice {
	@Expose
	private double _x, _y;

	private Set<Vertice> _vecinos;

	public Vertice(double x, double y) {
		_x = x;
		_y = y;
		inicializarVecinos();
	}

	public boolean agregarVecino(Vertice vecino) {
		if (vecino == null) {
			throw new IllegalArgumentException("El parametro no puede ser null");
		}
		return _vecinos.add(vecino);
	}

	public void eliminarVecino(Vertice vecino) {
		if (vecino == null) {
			throw new IllegalArgumentException("El parametro no puede ser null");
		}
		if (!esVecino(vecino)) {
			throw new IllegalArgumentException("El vertice no contiene este vertice: " + vecino);
		}
		_vecinos.remove(vecino);
	}

	public boolean esVecino(Vertice otroVertice) {
		if (_vecinos == null)
			return false;
		if (otroVertice == null) {
			throw new IllegalArgumentException("El parametro no puede ser null");
		}
		return _vecinos.contains(otroVertice);
	}

	public void inicializarVecinos() {
		_vecinos = new HashSet<>();
	}

	public double get_x() {
		return _x;
	}

	public double get_y() {
		return _y;
	}

	public Set<Vertice> get_vecinos() {
		return _vecinos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		if (_x != other._x || _y != other._y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "X=" + _x + ", Y=" + _y;
	}

}