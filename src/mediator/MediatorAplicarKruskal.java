package mediator;

import javax.swing.SwingWorker;

import cluster.Grafo;
import kruskal.AlgoritmoKruskal;

public class MediatorAplicarKruskal extends SwingWorker<Grafo, Long>{
	private Grafo _g;
	private Mediator _m;

	public MediatorAplicarKruskal(Mediator m, Grafo g) {
		_g = g;
		_m = m;
	}

	@Override
	protected Grafo doInBackground() throws Exception {
		_g = AlgoritmoKruskal.kruskal(_g);
		return _g;
	}
	
	public void done() {
			try {
				if (!this.isCancelled()) {
					_m.cambiarTextoEstado("Kruskal aplicado");
					_m.mostrarArcos();
				}
			} catch (Exception e) {
				System.out.println("ERROR: " + e);
			}
	}
	

}
