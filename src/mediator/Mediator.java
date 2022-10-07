package mediator;
import cluster.Grafo;
import cluster.Vertice;
import kruskal.AlgoritmoKruskal;
import cluster.Arco;
import cluster.GestorArchivos;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Mediator {
    Grafo _g;
    boolean isCompleto;

    public Mediator(String s){
        _g = new Grafo();
        _g.cargarGrafo(s);
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
		_g = AlgoritmoKruskal.kruskal(_g);
		isCompleto = false;
	}

}
