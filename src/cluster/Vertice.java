package cluster;

import java.util.HashSet;
import java.util.Set;

public class Vertice implements Comparable<Vertice> {

	private double _x, _y;
	private Set<Integer> _vecinos;
	private Integer _id;

	public Vertice(double x, double y, int id) {
		_x = x;
		_y = y;
		_id = id;
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

	public Integer getId() {
		return _id;
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
		if (_x != other._x || _y != other._y || _vecinos.containsAll(other.get_vecinos())
				|| other.get_vecinos().containsAll(_vecinos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + _x + ", " + _y + ")\n";
	}

	@Override
	public int compareTo(Vertice o) {
		if (this == null && o != null || this != null && o == null || this._id != o.getId()) {
			return -1;
		}
		return 0;
	}
}
