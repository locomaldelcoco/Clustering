package agm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo<Arco> {
	List <Integer> nombres;//vertices
	List <Arco> aristas;
	Map<Integer,Object> nodos;
 
 	public Grafo()
 	{
 		nombres=new ArrayList<Integer>();
 		aristas=new ArrayList <Arco>();
 		nodos=new HashMap <Integer,Object>();
 	}
 
 	public void ingresarNodo(Integer nombre){
 		nombres.add(nombre);
 		nodos.put(nombre,new Object(x,y));
 	}
 	
 	public void adicionarEnlace(String nodoInicial,String nodoTerminal,float peso){
 		Arco nuevo = new Arco(nodoInicial,nodoTerminal,peso);
 		int i = buscarIndice(nuevo.getPeso());
 
 		if(i == -1)
 			aristas.add(nuevo);
 		else 
 			aristas.add(i,nuevo);
 
 		nodos.get(nodoInicial).agregarEnlace(nodoTerminal,peso);
 		nodos.get(nodoTerminal).agregarEnlace(nodoInicial,peso);
 	}
 	
 	public boolean busarArista(Arco arco){
 		for(int i=0;i<aristas.size();i++)
 		{
 			Arco otro=aristas.get(i);
 			if(arco.getInicial().equals(otro.getInicial())&&arco.getTerminal().equals(otro.getTerminal())&&arco.getPeso()==otro.getPeso())
 			{
 				aristas.remove(otro);
 				return true;
 			}
 		}
 		return false;
 	}
 	
 	public int buscarIndice(float peso){
 		for(int i=0;i<aristas.size();i++)
 		{
  			if(peso<aristas.get(i).getPeso())
 				return i;
 		}
 		return -1;
 	}
 	
 	public HashMap<Integer,Object> getNodos(){
 		return nodos;
 	}
 	
 	public void setNodos(HashMap<Integer,Object > muchos){
 		nodos=muchos;
 	}
 	
 	public ArrayList<Integer> getNombres(){
 		return nombres;
 	}
 	
 	public Object getNodo(Integer nombre){
 		return (Object)nodos.get(nombre);
 	}
 
 	public ArrayList<Arco> getAristas() {
 		return aristas;
 	}
 
 	public void setAristas(ArrayList<Arco> aristas) {
 		this.aristas = aristas;
 	}
 
 	public void setNombres(ArrayList<Arco> nombres) {
 		this.nombres = nombres;
 	}
}
