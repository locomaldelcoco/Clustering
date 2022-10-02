package cluster;

import java.util.HashSet;
import java.util.Set;

public class Vertice {
	private double _x, _y;
	private Set<Integer> _vecinos;

	public Vertice(double x, double y) {
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
	public String toString() {
		return "(" + _x + ", " + _y + ")\n";
	}
}
