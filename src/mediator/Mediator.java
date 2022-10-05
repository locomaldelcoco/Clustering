package mediator;
import cluster.Grafo;
import cluster.Vertice;
import cluster.Arco;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Mediator {
    Grafo _g;

    public Mediator(int n){
        _g = new Grafo();
        _g.crearGrafo(n);
        _g.completarGrafo();
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

	public String[] getArchivos() {
		//TODO
		ArrayList<String> files = new ArrayList<String>();
		files.add("+");
		return files.toArray(new String[0]);
	}

}
