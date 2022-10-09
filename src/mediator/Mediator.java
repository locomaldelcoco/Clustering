package mediator;
import cluster.Grafo;
import cluster.Vertice;
import kruskal.AlgoritmoKruskal;
import cluster.Arco;
import cluster.DistanciaEuclidea;
import cluster.GestorArchivos;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Mediator {
    Grafo _g;
    boolean isCompleto;
    
    public Mediator() {
        _g = new Grafo();
        isCompleto = false;
    }

    public void completarGrafo() {
    	if (isCompleto)
    		return;
    	
    	_g.completarGrafo();   
    	isCompleto = true;
    }

    public ArrayList<Coordinate> getCoordenadas() {
        ArrayList<Coordinate> ret = new ArrayList<Coordinate>();
        ArrayList<Vertice> vertices = _g.getVertices();

        for (Vertice v : vertices) {
            Coordinate c = new Coordinate(v.get_x(), v.get_y());
            ret.add(c);
        }

        return ret;
    }

    public ArrayList<Coordinate[]> getCoordArcos() {
        ArrayList<Coordinate[]> ret = new ArrayList<Coordinate[]>();
        ArrayList<Arco> arcos = _g.getArcos();

        for (Arco a : arcos) {
            Coordinate[] c = new Coordinate[2];
            Vertice vA = a.getVerticeA();
            Vertice vB = a.getVerticeB();
            c[0] = new Coordinate(vA.get_x(), vA.get_y());
            c[1] = new Coordinate(vB.get_x(), vB.get_y());
            ret.add(c);
        }
        return ret;
    }
    
    public void eliminarArcoMasPesado() {
    	_g.eliminarArcoMasPesado();
    }

	public static String[] getArchivos() {
		String[] files = GestorArchivos.getArchivos(); 
		return files;
	}

	public boolean isCompleto() {
		return isCompleto;
	}

	public void aplicarKruskal() {
		System.out.println(_g.getVertices().size());
		if (_g.getVertices().size() < 1)
			return;
		_g = AlgoritmoKruskal.kruskal(_g);
		isCompleto = false;
	}

	public boolean agregarVertice(double lat, double lon) {
		return _g.agregarVertice(new Vertice(lat, lon));
	}

	public void eliminarArcos() {
		System.out.println(_g.getArcos());
		_g.getArcos().clear();
		System.out.println(_g.getArcos());
	}

	public void eliminarVertices() {
		System.out.println(_g.getVertices());
		_g.getVertices().clear();
		System.out.println(_g.getVertices());
	}
	
	public boolean guardarGrafo() {
		return GestorArchivos.guardarGrafo(_g);
	}

	public void cargarGrafo(String s) {
		_g = GestorArchivos.cargarGrafo(s);
		_g.inicializarVecinos();
		
		ArrayList<Arco> arcos = (ArrayList<Arco>) _g.getArcos().clone();
		eliminarArcos();
		for(Arco a : arcos) {
			Vertice vA = a.getVerticeA();
			Vertice vB = a.getVerticeB();
			_g.agregarArco(_g.getVertice(vA), _g.getVertice(vB), a.getDistancia());
		}
	}

	public boolean existeCoordenada(Coordinate coord) {	
		Vertice v = new Vertice(coord.getLat(), coord.getLon());
		return _g.getVertice(v) != null;
	}

	public boolean agregarArco(Coordinate[] c) {
		Vertice vA = new Vertice(c[0].getLat(), c[0].getLon());
		Vertice vB = new Vertice(c[1].getLat(), c[1].getLon());
		return _g.agregarArco(vA, vB, DistanciaEuclidea.distancia(vA, vB));
	}

	public void eliminarVertice(Coordinate c) {
		Vertice v = new Vertice(c.getLat(), c.getLon());
		_g.eliminarVertice(_g.getVertice(v));
	}
}
