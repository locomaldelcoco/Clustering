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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

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
	private JButton btnDibujarGrafoCompleto;
	private JButton btnKruskal; 
	private	JButton btnAgregarVertice;
	private boolean modoAgregarVertice;
	private JButton btnEliminarArcos;
	private JButton btnEliminarVertices;
	private JButton btnGuardarGrafo;
	private JButton btnCargarGrafo;
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
		mediator = new Mediator();
		setupFrame();
		setupMapContainer();
		addMapaEvents();
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
		panelDeUsuario.setLayout(null);
		setupBtnEliminarArco();
		setupMenuSeleccionArchivo();
		setupBtnDibujarGrafoCompleto();
		setupBtnKruskal();
		setupBtnAgregarVertice();
		setupBtnEliminarArcos();
		setupBtnEliminarVertices();
		setupBtnGuardarGrafo();
		setupBtnCargarGrafo();
	}

	private void setupBtnGuardarGrafo() {
		frame.getContentPane().add(panelDeUsuario);
			btnGuardarGrafo = new JButton("Guardar Grafo");
			btnGuardarGrafo.setBounds(591, 237, 183, 23);
			panelDeUsuario.add(btnGuardarGrafo);
	}

	private void setupBtnCargarGrafo() {
		frame.getContentPane().add(panelDeUsuario);
			btnCargarGrafo = new JButton("Cargar Grafo");
			btnCargarGrafo.setEnabled(false);
			btnCargarGrafo.setBounds(591, 203, 183, 23);
			panelDeUsuario.add(btnCargarGrafo);
	}

	private void setupBtnEliminarVertices() {
		btnEliminarVertices = new JButton("Eliminar Vértices");
		btnEliminarVertices.setBounds(591, 411, 183, 23);
		panelDeUsuario.add(btnEliminarVertices);
	}

	private void setupBtnEliminarArcos() {
		btnEliminarArcos = new JButton("Eliminar Arcos");
		btnEliminarArcos.setBounds(591, 377, 183, 23);
		panelDeUsuario.add(btnEliminarArcos);
	}

	private void setupBtnAgregarVertice() {
		btnAgregarVertice = new JButton("");
		btnAgregarVertice.setBorder(new EmptyBorder(2, 2, 2, 2));
		btnAgregarVertice.setToolTipText("Agregar vértice");
		btnAgregarVertice.setOpaque(false);
		btnAgregarVertice.setIcon(new ImageIcon("img\\vertice.png"));
		btnAgregarVertice.setBounds(591, 517, 33, 33);
		panelDeUsuario.add(btnAgregarVertice);
	}

	private void setupBtnEliminarArco() {
		btnEliminarArco = new JButton("Eliminar Arista Pesada");
		btnEliminarArco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarArco.setBounds(591, 269, 183, 22);
		btnEliminarArco.setEnabled(false);
		panelDeUsuario.add(btnEliminarArco);
	}
	
	private void setupBtnDibujarGrafoCompleto(){
		btnDibujarGrafoCompleto = new JButton("Completar Grafo");
		btnDibujarGrafoCompleto.setBounds(591, 99, 183, 23);
		btnDibujarGrafoCompleto.setEnabled(false);
		panelDeUsuario.add(btnDibujarGrafoCompleto);
	}
	
	private void setupBtnKruskal() {
		btnKruskal = new JButton("Aplicar Kruskal");
		btnKruskal.setBounds(591, 133, 183, 23);
		panelDeUsuario.add(btnKruskal);
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
	
	private void addMapaEvents() {
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coordinate coord = (Coordinate) mapa.getPosition(e.getPoint());
				if (modoAgregarVertice && mediator.agregarVertice(coord.getLat(), coord.getLon()))
					mapa.addMapMarker(new MapMarkerDot(coord.getLat(), coord.getLon()));
			}
		});
	}

	private void addPanelDeUsuarioEvents() {
		btnEliminarArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediator.eliminarArcoMasPesado();;
				mostrarArcos();
			}
		});

		menuSeleccionArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuSeleccionArchivo.getSelectedIndex() == 0) {
					desactivarBtnCargarGrafo();
				} else {
					activarBtnCargarGrafo();
				}
			}
		});

		btnDibujarGrafoCompleto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dibujarGrafoCompleto();
				activarBtnEliminarArco();
			}
		});

		btnKruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicarKruskal();
			}
		});
		
		btnAgregarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoAgregarVertice = modoAgregarVertice ? false : true;
			}
		});
		
		btnEliminarArcos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediator.eliminarArcos();
				mapa.removeAllMapPolygons();
			}
		});

		btnEliminarVertices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediator.eliminarVertices();
				mediator.eliminarArcos();
				limpiarMapa();
			}
		});

		btnGuardarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(mediator.guardarGrafo());
			}
		});

		btnCargarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediator.cargarGrafo((String) menuSeleccionArchivo.getSelectedItem());
				limpiarMapa();
				showMapMarkers();
				mostrarArcos();
			}
		});
	}

	private void aplicarKruskal() {
		mediator.completarGrafo();
		mediator.aplicarKruskal();
		mostrarArcos();
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
	
	private void desactivarBtnCargarGrafo() {
		btnCargarGrafo.setEnabled(false);
	}

	private void activarBtnCargarGrafo() {
		btnCargarGrafo.setEnabled(true);
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
