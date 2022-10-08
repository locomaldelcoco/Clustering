package cluster;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Vertice {
	@Expose
	private double _x, _y;
	
	private Set<Vertice> _vecinos;
	
	public Vertice(double x, double y) {
		_x = x;
		_y = y;
		_vecinos = new HashSet<>();
	}

	public boolean agregarVecino(Vertice vecino) {
		if (vecino == null) {
			throw new IllegalArgumentException("No existe vecino");
		}
		return _vecinos.add(vecino);
	}

	public void eliminarVecino(Vertice vecino) {
		if (vecino == null) {
			throw new IllegalArgumentException("No existe vecino");
		}
		_vecinos.remove(vecino);
	}
	
	public boolean esVecino(Vertice otroVertice) {
		return _vecinos.contains(otroVertice);
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
		if (_x != other._x || _y != other._y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "X=" + _x +  ", Y=" + _y ;
	}
	
	public static void main(String[] args) {
		Vertice v = new Vertice(1,2);
		Vertice v2 = new Vertice(2,3);
		v.agregarVecino(v2);
		v2.agregarVecino(v);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
		String vJSON = gson.toJson(v);
		System.out.println(vJSON);
		
	}
}
