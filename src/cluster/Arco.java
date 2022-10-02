package cluster;

public class Arco {
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

}
