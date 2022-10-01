package cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo<Arco> {
	List <Integer> _nombres;//vertices
	List <Arco> _aristas;
	Map<Integer,Object> _nodos;
 
 	public Grafo()
 	{
 		_nombres=new ArrayList<Integer>();
 		_aristas=new ArrayList <Arco>();
 		_nodos=new HashMap <Integer,Object>();
 	}
 
 	public void ingresarNodo(Integer nombre){
 		_nombres.add(nombre);
 		_nodos.put(nombre,new Object(x,y));
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
 	
 	public HashMap<Integer,Object> getNodos(){
 		return _nodos;
 	}
 	
 	public void setNodos(HashMap<Integer,Object > muchos){
 		_nodos=muchos;
 	}
 	
 	public ArrayList<Integer> getNombres(){
 		return _nombres;
 	}
 	
 	public Object getNodo(Integer nombre){
 		return (Object)_nodos.get(nombre);
 	}
 
 	public ArrayList<Arco> getAristas() {
 		return _aristas;
 	}
 
 	public void setAristas(ArrayList<Arco> aristas) {
 		this._aristas = aristas;
 	}
 
 	public void setNombres(ArrayList<Arco> nombres) {
 		this._nombres = nombres;
 	}
}
