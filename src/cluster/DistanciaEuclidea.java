package cluster;

public class DistanciaEuclidea {
	public static double distancia(Vertice vA, Vertice vB) {
		if (vA == null || vB == null) {
			throw new NullPointerException("los parametros no pueden ser null");
		}
		double diferenciaX = vB.get_x() - vA.get_x();
		double diferenciaY = vB.get_y() - vA.get_y();
		
		return Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2)) * 112;
		// Multiplicado por 112 ya que 1° de diferencia en mapa es equivalente a 1/112km
	}
}