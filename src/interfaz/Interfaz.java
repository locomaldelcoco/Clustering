package interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;
import mediator.Mediator;
import cluster.Arco;
import cluster.Grafo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz {

	private JFrame frame;
	private JPanel mapContainer;
	private JMapViewer mapa;
	private Toolkit miPantalla;
	private Image miIcono;
	private JButton btnEliminarArco;
	private JPanel panelDeUsuario;
	private Mediator mediator;
	
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
		
		mediator = new Mediator(6);

		Grafo g = new Grafo();
		g.crearGrafo(6);
		g.completarGrafo();

		setupBtnsContainer(g);
		showMapMarkers();
		recorrerArcos(g);

	}

	private void showMapMarkers() {
		ArrayList<Coordinate> coords = mediator.getCoordenadas();
		for(Coordinate c : coords) {
			mapa.setDisplayPosition(c, 12);
			MapMarker m = new MapMarkerDot(c);
			mapa.addMapMarker(m);
		}
		System.out.println(coords.size());
	}

	private void setupBtnsContainer(Grafo g) {
		
		panelDeUsuario = new JPanel();
		panelDeUsuario.setBounds(579, 0, 195, 550);
		mapContainer.add(panelDeUsuario);
		panelDeUsuario.setLayout(null);
		
		btnEliminarArco = new JButton("Eliminar Arista Pesada");
		btnEliminarArco.setBounds(10, 270, 175, 22);
		panelDeUsuario.add(btnEliminarArco);
		btnEliminarArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();;
				g.eliminarArcoMasPesado();;
				System.out.println(g.getArcos().size());
				recorrerArcos(g);
			}
		});
	
		
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
