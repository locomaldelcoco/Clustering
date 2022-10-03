package cluster;

import java.util.HashSet;
import java.util.Set;

public class Vertice {
	private int _nombre;
	private double _x, _y;
	private Set<Integer> _vecinos;
	private static int cont=1;
	
	public Vertice(double x, double y) {
		_nombre = cont++;
		_x = x;
		_y = y;
		_vecinos = new HashSet<>();
	}

	public void agregarVecino(int vecino) {
		if (vecino < 0) {
			throw new IllegalArgumentException("No puede existir vecinos negativos");
		}
		_vecinos.add(vecino);
	}

	public void eliminarVecino(int vecino) {
		if (vecino < 0) {
			throw new IllegalArgumentException("No puede existir vecinos negativos");
		}
		_vecinos.remove(vecino);
	}
	
	public boolean sonVecinos(Vertice otroVertice) {
		return _vecinos.contains(otroVertice.get_nombre()) && otroVertice.get_vecinos().contains(_nombre);
	}
	
	public int get_nombre() {
		return _nombre;
	}
	public double get_x() {
		return _x;
	}

	public double get_y() {
		return _y;
	}

	public Set<Integer> get_vecinos() {
		return _vecinos;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Vertice other = (Vertice) obj;
		if (_x != other._x || _y != other._y || _vecinos.containsAll(other.get_vecinos()) || other.get_vecinos().containsAll(_vecinos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + _x + ", " + _y + ")\n";
	}
}
