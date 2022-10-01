package cluster;

public class DistanciaEuclidea {
	public static long distancia(Vertice vA, Vertice vB) {
		if (vA == null || vB == null) {
			throw new NullPointerException("los parametros no pueden ser null");
		}
		long diferenciaX = vB.get_x() - vA.get_x();
		long diferenciaY = vB.get_y() - vA.get_y();
		
		return (long) Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
	}
}
