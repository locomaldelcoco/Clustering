package cluster;

public class Arco implements Comparable<Arco> {
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
		return (other._verticeA.equals(this._verticeA) && other._verticeB.equals(this._verticeB) && other._distancia == this._distancia);
	}

	public int compareTo(Arco arco) {
		return getDistancia() < arco.getDistancia() ? 1 : getDistancia() > arco.getDistancia() ? -1 : 0;
	}
	
	@Override
	public String toString() {
		return "[" + _verticeA + "_" + _verticeB + "] " + _distancia;
	}
}
