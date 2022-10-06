package cluster;

import java.util.HashSet;
import java.util.Set;

public class Vertice {
	private int _nombre;
	private double _x, _y;
	private Set<Vertice> _vecinos;
	private static int cont=1;
	
	public Vertice(double x, double y) {
		_nombre = cont++;
		_x = x;
		_y = y;
		_vecinos = new HashSet<>();
	}

	public void agregarVecino(Vertice vecino) {
		_vecinos.add(vecino);
	}

	public void eliminarVecino(Vertice vecino) {
		_vecinos.remove(vecino);
	}
	
	public boolean sonVecinos(Vertice otroVertice) {
		return _vecinos.contains(otroVertice);
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

	public Set<Vertice> get_vecinos() {
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
