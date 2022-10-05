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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Interfaz {

	private JFrame frame;
	private JPanel mapContainer;
	private JMapViewer mapa;
	private Toolkit miPantalla;
	private Image miIcono;
	private JButton btnEliminarArco;
	private JPanel panelDeUsuario;
	private Mediator mediator;
	private JComboBox menuSeleccionArchivo;
	
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
		mediator = new Mediator(6);
		setupFrame();
		setupMapContainer();
		setupPanelDeUsuario();
		addPanelDeUsuarioEvents();
		showMapMarkers();
		recorrerArcos();

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

	private void setupPanelDeUsuario() {
		panelDeUsuario = new JPanel();
		panelDeUsuario.setBounds(0, 0, 195, 550);
		panelDeUsuario.setLayout(null);
		btnEliminarArco = new JButton("Eliminar Arista Pesada");
		btnEliminarArco.setBounds(591, 269, 183, 22);
		panelDeUsuario.add(btnEliminarArco);
		frame.getContentPane().add(panelDeUsuario);
		
		setupMenuSeleccionArchivo();
		
	}
	
	private void setupMenuSeleccionArchivo() {
		menuSeleccionArchivo = new JComboBox();
		String[] archivos = mediator.getArchivos();
		DefaultComboBoxModel model = new DefaultComboBoxModel(archivos);
		menuSeleccionArchivo.setModel(model);
		menuSeleccionArchivo.setBounds(591, 32, 183, 22);
		panelDeUsuario.add(menuSeleccionArchivo);
	}

	private void addPanelDeUsuarioEvents() {
		btnEliminarArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapa.removeAllMapPolygons();;
				mediator.eliminarArcoMasPesado();;
				recorrerArcos();
			}
		});
	}

	private void setupMapContainer() {
		mapContainer = new JPanel();
		mapContainer.setBounds(0, 0, 571, 561);
		mapContainer.setLayout(null);
		mapa = new JMapViewer();
		mapa.setBounds(0, 0, 571, 561);
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
		
	

	private void recorrerArcos() {
		ArrayList<Coordinate[]> arcos = mediator.getCoordArcos();
		
		for (Coordinate[] c : arcos) {
			dibujarArco(c[0], c[1]);
		}
		
		System.out.println(arcos.size());
	}

	private void dibujarArco(Coordinate c1, Coordinate c2) {
		MapPolygon map = new MapPolygonImpl(c1, c2, c2);
		mapa.addMapPolygon(map);
		
	}
	
	
}
