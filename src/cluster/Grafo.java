package cluster;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo<Arco> {
	List <Integer> _nombres;//vertices
	List <Arco> _aristas;
	Map<Integer,Vertice> _nodos;
 
 	public Grafo()
 	{
 		_nombres=new ArrayList<Integer>();
 		_aristas=new ArrayList <Arco>();
 		_nodos=new HashMap <Integer,Vertice>();
 	}
 
 	public void ingresarNodo(Integer nombre){
 		_nombres.add(nombre);
 		_nodos.put(nombre,new Vertice(0, 0));
 	}
 	
 	public void adicionarEnlace(String nodoInicial,String nodoTerminal,float peso){
 		Arco nuevo = new Arco(nodoInicial,nodoTerminal,peso);
 		int i = buscarIndice(nuevo.getPeso());
 
 		if(i == -1)
 			_aristas.add(nuevo);
 		else 
 			_aristas.add(i,nuevo);
 
 		_nodos.get(nodoInicial).agregarEnlace(nodoTerminal,peso);
 		_nodos.get(nodoTerminal).agregarEnlace(nodoInicial,peso);
 	}
 	
 	public boolean busarArista(Arco arco){
 		for(int i=0;i<_aristas.size();i++)
 		{
 			Arco otro=_aristas.get(i);
 			if(arco.getInicial().equals(otro.getInicial())&&arco.getTerminal().equals(otro.getTerminal())&&arco.getPeso()==otro.getPeso())
 			{
 				_aristas.remove(otro);
 				return true;
 			}
 		}
 		return false;
 	}
 	
 	public int buscarIndice(float peso){
 		for(int i=0;i<_aristas.size();i++)
 		{
  			if(peso<_aristas.get(i).getPeso())
 				return i;
 		}
 		return -1;
 	}

 	
 	public List<Integer> get_nombres() {
		return _nombres;
	}

	public void set_nombres(List<Integer> _nombres) {
		this._nombres = _nombres;
	}

	public Vertice getNodo(Integer nombre){
 		return (Vertice)_nodos.get(nombre);
 	}

	public List<Arco> get_aristas() {
		return _aristas;
	}

	public void set_aristas(List<Arco> _aristas) {
		this._aristas = _aristas;
	}
 
=======

public class Grafo {
	private ArrayList<Vertice> _vertices;
	private ArrayList<Arco> _arcos;

	public Grafo() {
		_vertices = new ArrayList<>();
		_arcos = new ArrayList<>();
	}

	public void crearGrafo(String n) {
		ArrayList<Vertice> coords = Filter.coordenadas(n);
		for (Vertice v : coords)
			agregarVertice(v);
	}

	private void agregarVertice(Vertice v) {
		_vertices.add(v);
	}
	
	public void completarGrafo() {
		for (int i = 0; i < _vertices.size(); i++ ) {
	/*j=i+1*/for (int j=i+1; j < _vertices.size(); j++ ) {
				if (i != j) {
					double distancia = DistanciaEuclidea.distancia(_vertices.get(i), _vertices.get(j));
					System.out.println(distancia);
					agregarArco(i, j, distancia);
				}
			}
		}
	}

	public void agregarArco(int indexA, int indexB, double distancia) {
		if (indexA < 0 || indexB < 0 || indexA >= _vertices.size() || indexB >= _vertices.size())
			throw new IllegalArgumentException("Indice no válido");

		_arcos.add(new Arco(_vertices.get(indexA), _vertices.get(indexB), distancia));
		agregarVecinos(indexA, indexB);
	}
	
	public void eliminarArco(int numArco) {			
		if (numArco < 0 || numArco > _arcos.size()) {
			throw new IndexOutOfBoundsException("el indice es menor o mayor al tamano del arco");
		}
		_arcos.remove(numArco);
	}
	
	private void agregarVecinos(int indexA, int indexB) {
		_vertices.get(indexA).agregarVecino(indexB);
		_vertices.get(indexB).agregarVecino(indexA);
	}	
	
	public ArrayList<Vertice> getVertices() {
		return _vertices;
	}

	public ArrayList<Arco> getArcos() {
		return _arcos;
	}
>>>>>>> pre-main
}
