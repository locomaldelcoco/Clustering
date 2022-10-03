package interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import cluster.Arco;
import cluster.Grafo;
import cluster.Vertice;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz {

	private JFrame frame;
	private JPanel mapContainer;
	private JPanel btnsContainer;
	private JMapViewer mapa;
	private Toolkit miPantalla;
	private Image miIcono;
	private JButton btnEliminarArco;
	
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
		setupFrame();
		
		setupMapContainer();

		Grafo g = new Grafo();
		g.crearGrafo("6");
		g.completarGrafo();

		setupBtnsContainer(g);
		showMapMarkers(g);
		recorrerArcos(g);

	}

	private void showMapMarkers(Grafo g) {
		ArrayList<Vertice> vertices = g.getVertices();
		for(Vertice v : vertices) {
			Coordinate c = new Coordinate(v.get_x(), v.get_y());
			mapa.setDisplayPosition(c, 12);
			MapMarker m = new MapMarkerDot(c);
			mapa.addMapMarker(m);
		}
		System.out.println(g.getArcos().size());
	}

	private void setupBtnsContainer(Grafo g) {
		btnsContainer = new JPanel();
		btnEliminarArco = new JButton("Eliminar Arco");
		btnEliminarArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();;
				
				//##START eliminarArcoPesado
				//-- Primer acercamiento
				int indexArcoPesado = 0;
				
				ArrayList<Arco> arcos = g.getArcos();
				
				for (int i = 0; i < arcos.size(); i++) {
					if (arcos.get(i).getDistancia() > arcos.get(indexArcoPesado).getDistancia()) {
						indexArcoPesado = i;
					}
				}
				//##END eliminarArcoPesado
				
				g.eliminarArco(indexArcoPesado);
				System.out.println(g.getArcos().size());
				recorrerArcos(g);
			}
		});
		btnsContainer.add(btnEliminarArco);
		frame.getContentPane().add(btnsContainer, BorderLayout.NORTH);
	}

	private void setupMapContainer() {
		mapContainer = new JPanel();
		mapContainer.setBounds(1, 1, 798, 598);
		mapContainer.setLayout(null);
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 798, 528);
		mapContainer.add(mapa);
		frame.getContentPane().add(mapContainer);
	}

	private void setupFrame() {
		miPantalla = Toolkit.getDefaultToolkit();
		miIcono = miPantalla.getImage("src/icono.png");

		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Clustering con AGM");

		frame.setIconImage(miIcono);
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void recorrerArcos(Grafo g) {
		for (int i = 0; i < g.getArcos().size(); i++) {
			dibujarArco(g.getArcos().get(i));
		}
	}

	private void dibujarArco(Arco a) {
		Coordinate c1 = new Coordinate(a.getVerticeA().get_x(), a.getVerticeA().get_y());
		Coordinate c2 = new Coordinate(a.getVerticeB().get_x(), a.getVerticeB().get_y());
		MapPolygon map = new MapPolygonImpl(c1, c2, c2);
		mapa.addMapPolygon(map);
		
	}
	
}
