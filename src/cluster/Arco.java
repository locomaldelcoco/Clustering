package cluster;

import java.util.Set;
import java.util.HashSet;

public class Arco implements Comparable<T>{
	private Vertice _verticeA;
	private Vertice _verticeB;
	private double _distancia;

	public Arco(Vertice verticeA, Vertice verticeB, double distancia) {
		_verticeA = verticeA;
		_verticeB = verticeB;
		_distancia = distancia;
	}
	
	public Vertice getVerticeA() {
		return _verticeA;
	}

	public Vertice getVerticeB() {
		return _verticeB;
	}
	
	public double getDistancia() {
		return _distancia;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;

		Arco other = (Arco) obj;
		Set<Vertice> verticesThis = new HashSet<Vertice>();
		Set<Vertice> verticesOther = new HashSet<Vertice>();
		verticesThis.add(_verticeA);
		verticesThis.add(_verticeB);
		verticesOther.add(other._verticeA);
		verticesOther.add(other._verticeB);

		if (!verticesThis.contains(other._verticeA) || 
				!verticesThis.contains(other._verticeB) || 
				!verticesOther.contains(_verticeA) || 
				!verticesOther.contains(_verticeB))
			return false;
		
		return true;
	}
}
