package cluster;

public class Arco {
	private Vertice _verticeA;
	private Vertice _verticeB;
	private long _distancia;

	public Arco(Vertice verticeA, Vertice verticeB) {
		_verticeA = verticeA;
		_verticeB = verticeB;
		_distancia = calcularDistancia();
	}

	private long calcularDistancia() {
		return (long) Math.sqrt(
				Math.pow(_verticeB.get_x() - _verticeA.get_x(),2) +
				Math.pow(_verticeB.get_y() - _verticeA.get_y(),2)
				);
	}
	
	public long getDistancia() {
		return _distancia;
	}
}
