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
import java.awt.Cursor;

@SuppressWarnings({ "unchecked", "rawtypes" })
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
	private JButton btnCargarArchivo;
	private JButton btnDibujarGrafoCompleto;
	
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
		mediator = null;
		setupFrame();
		setupMapContainer();
		setupPanelDeUsuario();
		addPanelDeUsuarioEvents();
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
		setupBtnEliminarArco();
		setupMenuSeleccionArchivo();
		setupBtnCargarArchivo();
		setupBtnDibujarGrafoCompleto();
		frame.getContentPane().add(panelDeUsuario);
	}

	private void setupBtnEliminarArco() {
		btnEliminarArco = new JButton("Eliminar Arista Pesada");
		btnEliminarArco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarArco.setBounds(591, 269, 183, 22);
		btnEliminarArco.setEnabled(false);
		panelDeUsuario.add(btnEliminarArco);
	}
	
	private void setupBtnCargarArchivo() {
		btnCargarArchivo = new JButton("Cargar archivo");
		btnCargarArchivo.setBounds(591, 65, 183, 23);
		btnCargarArchivo.setEnabled(false);
		panelDeUsuario.add(btnCargarArchivo);
	}
	
	private void setupBtnDibujarGrafoCompleto(){
		btnDibujarGrafoCompleto = new JButton("Completar Grafo");
		btnDibujarGrafoCompleto.setBounds(591, 99, 183, 23);
		btnDibujarGrafoCompleto.setEnabled(false);
		panelDeUsuario.add(btnDibujarGrafoCompleto);
	}

	private void cargarArchivo() {
		mediator = new Mediator((String) menuSeleccionArchivo.getSelectedItem());
		showMapMarkers();
	}
	
	private void dibujarGrafoCompleto() {
		if (!mediator.isCompleto())
			mediator.completarGrafo();
		mostrarArcos();
	}

	private void limpiarMapa() {
		mapa.removeAllMapMarkers();
		mapa.removeAllMapPolygons();
	}

	private void setupMenuSeleccionArchivo() {
		menuSeleccionArchivo = new JComboBox();
		menuSeleccionArchivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuSeleccionArchivo.setToolTipText("Lista de archivos");
		String[] archivos = setupModelMenuArchivo();
		DefaultComboBoxModel model = new DefaultComboBoxModel(archivos);
		menuSeleccionArchivo.setModel(model);
		menuSeleccionArchivo.setSelectedIndex(0);
		menuSeleccionArchivo.setBounds(591, 32, 183, 22);
		panelDeUsuario.add(menuSeleccionArchivo);
	}

	private String[] setupModelMenuArchivo() {
		String[] files = Mediator.getArchivos();
		String[] ret = new String[files.length+1];
		ret[0] = "Seleccione un archivo...";
		for (int i = 1; i < ret.length; i++)
			ret[i] = files[i-1];
		return ret;
	}

	private void addPanelDeUsuarioEvents() {
		btnEliminarArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediator.eliminarArcoMasPesado();;
				mostrarArcos();
			}
		});

		btnCargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mediator != null) {
					limpiarMapa();
				}
				cargarArchivo();
				activarBtnDibujarGrafoCompleto();
			}
		});
		
		menuSeleccionArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuSeleccionArchivo.getSelectedIndex() == 0) {
					desactivarBtnCargarArchivo();
				} else {
					activarBtnCargarArchivo();
				}
				
			}
			
		});

		btnDibujarGrafoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dibujarGrafoCompleto();
				desactivarBtnEliminarArco();
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
	
	private void mostrarArcos() {
		mapa.removeAllMapPolygons();
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
	
	private void desactivarBtnCargarArchivo() {
		btnCargarArchivo.setEnabled(false);
	}

	private void activarBtnCargarArchivo() {
		btnCargarArchivo.setEnabled(true);
	}
	
	private void desactivarBtnDibujarGrafoCompleto() {
		btnDibujarGrafoCompleto.setEnabled(false);
	}

	private void activarBtnDibujarGrafoCompleto() {
		btnDibujarGrafoCompleto.setEnabled(true);
	}

	private void desactivarBtnEliminarArco() {
		btnEliminarArco.setEnabled(false);
	}

	private void activarBtnEliminarArco() {
		btnEliminarArco.setEnabled(true);
	}
	
}
