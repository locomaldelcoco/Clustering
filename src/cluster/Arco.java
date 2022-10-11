package cluster;

import com.google.gson.annotations.Expose;

public class Arco implements Comparable<Arco> {
	@Expose
	private Vertice _verticeA;
	@Expose
	private Vertice _verticeB;
	@Expose
	private double _distancia;

	public Arco(Vertice verticeA, Vertice verticeB, double distancia) {
		_verticeA = verticeA;
		_verticeB = verticeB;
		_distancia = distancia;
	}

	public boolean contiene(Vertice v) {
		if (v == null) {
			throw new IllegalArgumentException("Se paso un parametro null: " + v);
		}
		return _verticeA.equals(v) || _verticeB.equals(v);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Arco other = (Arco) obj;

		if (this._distancia != other._distancia
				|| ((!_verticeA.equals(other._verticeA) && !_verticeA.equals(other._verticeB))
						|| (!_verticeB.equals(other._verticeA) && !_verticeB.equals(other._verticeB))))
			return false;

		return true;
	}

	public int compareTo(Arco arco1) {
		return getDistancia() < arco1.getDistancia() ? 1 : getDistancia() > arco1.getDistancia() ? -1 : 0;
	}

	@Override
	public String toString() {
		return "VerticeA = " + _verticeA + ", VerticeB = " + _verticeB + ", Distancia = " + _distancia;
	}
	
	public boolean esHoja() {
		return (this.getVerticeA().get_vecinos().size() == 0 || this.getVerticeA().get_vecinos().size() == 1) 
												||
			(this.getVerticeB().get_vecinos().size() == 0 || this.getVerticeB().get_vecinos().size() == 1); 
	}
	
	public boolean esHojaSuelta() {
		return this.getVerticeA().get_vecinos().contains(this.getVerticeB()) 
											&& 
				this.getVerticeB().get_vecinos().contains(this.getVerticeA());
	}
	
}
