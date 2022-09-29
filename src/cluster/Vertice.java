package cluster;

import java.util.HashSet;
import java.util.Set;

public class Vertice {
	private long _x, _y;
	private Set<Integer> _vecinos;

	public Vertice(long x, long y) {
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
}
