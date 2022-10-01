package cluster;

public class Arco {
	private Vertice _verticeA;
	private Vertice _verticeB;
	private long _distancia;

	public Arco(Vertice verticeA, Vertice verticeB) {
		_verticeA = verticeA;
		_verticeB = verticeB;
		_distancia = DistanciaEuclidea.distancia(verticeA, verticeB);
	}
	
	public Vertice getVerticeA() {
		return _verticeA;
	}

	public Vertice getVerticeB() {
		return _verticeB;
	}
	
	public long getDistancia() {
		return _distancia;
	}
}
