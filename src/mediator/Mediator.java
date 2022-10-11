package mediator;

import cluster.Grafo;
import cluster.Vertice;
import interfaz.Interfaz;
import kruskal.AlgoritmoKruskal;
import cluster.Arco;
import cluster.DistanciaEuclidea;
import cluster.GestorArchivos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.openstreetmap.gui.jmapviewer.Coordinate;


public class Mediator {
	private Interfaz _interfaz;
	private Grafo _g;
	protected boolean _isCompleto;

	public Mediator(Interfaz interfaz) {
		_interfaz = interfaz;
		_g = new Grafo();
		_isCompleto = _g.getIsCompleto();
	}

	public void completarGrafo() {
		if (_isCompleto) {
			_interfaz.cambiarTextoEstado("Grafo ya es completo");
			_interfaz.updateFrame();
			return;
		}
		MediatorCompletarGrafo thread = new MediatorCompletarGrafo(this, _g);
		thread.execute();
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
	
	public void calcularClusters() {
		AlgoritmoKruskal.calcularClusters(_g);
	}
	
	public void eliminarArcoMasPesado() {
		_g.eliminarArcoMasPesado();
		_interfaz.cambiarTextoEstado("Arco más pesado eliminado");
	}

	public static String[] getArchivos() {
		String[] files = GestorArchivos.getArchivos();
		return files;
	}

	public void aplicarKruskal() throws InterruptedException, ExecutionException {
		if (_g.getArcos().size() < _g.tamano()-1) {
			_interfaz.cambiarTextoEstado("No se puede aplicar - Grafo no conexo");
			_interfaz.updateFrame();
			return;
		}
		_isCompleto = false;
		if (_g.getVertices().size() < 1)
			return;
		MediatorAplicarKruskal thread = new MediatorAplicarKruskal(this, _g);
		thread.execute();
		_g = thread.get();
	}

	public boolean agregarVertice(double lat, double lon) {
		_isCompleto = false;
		_interfaz.cambiarTextoEstado("Coordenada (" + lat + ", " + lon + ") - Agregada");
		return _g.agregarVertice(new Vertice(lat, lon));
	}

	public void eliminarArcos() {
		_isCompleto = false;
		_interfaz.cambiarTextoEstado("ARCOS ELIMINADOS!");
		_g.eliminarAllArcos();
	}

	public void eliminarVertices() {
		_isCompleto = false;
		_interfaz.cambiarTextoEstado("VÉRTICES ELIMINADOS!");
		_g.eliminarAllVertices();

	}

	public boolean guardarGrafo() {
		return GestorArchivos.guardarGrafo(_g);
	}

	public void cargarGrafo(String s) {
		_g = GestorArchivos.cargarGrafo(s);
		_g.inicializarVecinos();

		ArrayList<Arco> arcos = (ArrayList<Arco>) _g.getArcos().clone();
		eliminarArcos();
		for (Arco a : arcos) {
			Vertice vA = a.getVerticeA();
			Vertice vB = a.getVerticeB();
			_g.agregarArco(_g.getVertice(vA), _g.getVertice(vB), a.getDistancia());
		}
		_isCompleto = _g.getIsCompleto();
		_interfaz.cambiarTextoEstado("Archivo " + s + " - Cargado");
	}

	public boolean existeCoordenada(Coordinate coord) {
		Vertice v = new Vertice(coord.getLat(), coord.getLon());
		return _g.getVertice(v) != null;
	}

	public boolean agregarArco(Coordinate[] c) {
		Vertice vA = new Vertice(c[0].getLat(), c[0].getLon());
		Vertice vB = new Vertice(c[1].getLat(), c[1].getLon());
		_interfaz.cambiarTextoEstado("Arco [(" + vA + ") , (" + vB + ")] - Agregado");
		return _g.agregarArco(vA, vB, DistanciaEuclidea.distancia(vA, vB));
	}

	public void eliminarVertice(Coordinate c) {
		Vertice v = new Vertice(c.getLat(), c.getLon());
		_g.eliminarArcosDeVertice(_g.getVertice(v));
		_g.eliminarVertice(v);
		_isCompleto = false;
		_interfaz.cambiarTextoEstado("Coordenada " + c + " - Eliminada");
	}

	public void mostrarArcos() {
		_interfaz.updateFrame();
	}
	
	public void cambiarTextoEstado(String s) {
		_interfaz.cambiarTextoEstado(s);
	}
	
	public void setIsCompleto(Boolean value) {
		_isCompleto = value;
	}
}
