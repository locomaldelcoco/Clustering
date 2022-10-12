package mediator;

import javax.swing.SwingWorker;

import cluster.Grafo;

public class MediatorCompletarGrafo extends SwingWorker<Boolean, Long>{
	private Grafo _g;
	private Mediator _m;

	public MediatorCompletarGrafo(Mediator m, Grafo g) {
		_g = g;
		_m = m;
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		_g.completarGrafo();
		return true;
	}
	
	public void done() {
			try {
				if (!this.isCancelled()) {
					_m.cambiarTextoEstado("Grafo completado");
					_m.mostrarArcos();
				}
			} catch (Exception e) {
				System.out.println("ERROR: " + e);
			}
	}
	

}
