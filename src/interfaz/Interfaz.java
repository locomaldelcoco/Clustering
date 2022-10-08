package interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		setupBtnCargarArchivo();
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
			btnGuardarGrafo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(mediator.guardarGrafo());
				}
			});
			btnGuardarGrafo.setBounds(591, 237, 183, 23);
			panelDeUsuario.add(btnGuardarGrafo);
	}

	private void setupBtnCargarGrafo() {
		frame.getContentPane().add(panelDeUsuario);
			btnCargarGrafo = new JButton("Cargar Grafo");
			btnCargarGrafo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(mediator.cargarGrafo((String) menuSeleccionArchivo.getSelectedItem()));
				}
			});
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
		btnAgregarVertice = new JButton("Agregar Vértice");
		btnAgregarVertice.setBounds(591, 516, 107, 34);
		panelDeUsuario.add(btnAgregarVertice);
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
	
	private void setupBtnKruskal() {
		btnKruskal = new JButton("Aplicar Kruskal");
		btnKruskal.setBounds(591, 133, 183, 23);
		panelDeUsuario.add(btnKruskal);
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
	
	private void addMapaEvents() {
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Coordinate coord = (Coordinate) mapa.getPosition(e.getPoint());
				if (modoAgregarVertice && mediator.agregarVertice(coord.getLat(), coord.getLon()))
					mapa.addMapMarker(new MapMarkerDot(coord.getLat(), coord.getLon()));
				modoAgregarVertice = false;
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
				modoAgregarVertice = true;
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
