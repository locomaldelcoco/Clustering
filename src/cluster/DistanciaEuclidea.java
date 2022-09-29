package cluster;

import java.awt.Point;

public class DistanciaEuclidea {
	public static double distancia(Point p1, Point p2) {
		if (p1 == null || p2 == null) {
			throw new NullPointerException("los parametros no pueden ser null");
		}
		double diferenciaX = p1.getX() - p2.getX();
		double diferenciaY = p1.getY() - p2.getY();
		
		return Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
	}
}
