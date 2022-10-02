package interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import cluster.Arco;
import cluster.Grafo;
import cluster.Vertice;

public class Interfaz {

	private JFrame frame;
	private JMapViewer mapa;
	private Toolkit miPantalla;
	private Image miIcono;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mapa = new JMapViewer();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Clustering con AGM");
		miPantalla = Toolkit.getDefaultToolkit();
		miIcono = miPantalla.getImage("src/icono.png");
		frame.setIconImage(miIcono);
		
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mapa);
		
		Grafo g = new Grafo();
		g.crearGrafo("5");
		g.completarGrafo();
		
		ArrayList<Vertice> vertices = g.getVertices();
		
		for(Vertice v : vertices) {
			Coordinate c = new Coordinate(v.get_x(), v.get_y());
			mapa.setDisplayPosition(c, 12);
			MapMarker m = new MapMarkerDot(c);
			mapa.addMapMarker(m);
		}
		
		for (int i = 0; i < g.getArcos().size(); i++)
			dibujarArco(g.getArcos().get(i));

	}

	private void dibujarArco(Arco a) {
		Coordinate c1 = new Coordinate(a.getVerticeA().get_x(), a.getVerticeA().get_y());
		Coordinate c2 = new Coordinate(a.getVerticeB().get_x(), a.getVerticeB().get_y());
		MapPolygon p = new MapPolygonImpl(c1, c2, c2);
		mapa.addMapPolygon(p);
	}
	
}
