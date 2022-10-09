package interfaz;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
import java.awt.Font;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("rawtypes")
public class Interfaz {

	private JFrame frame;
	private JPanel mapContainer;
	private JMapViewer mapa;
	private JButton btnEliminarArco;
	private JPanel panelDeUsuario;
	private Mediator mediator;
	private JComboBox menuSeleccionArchivo;
	private JButton btnDibujarGrafoCompleto;
	private JButton btnKruskal; 
	private JButton btnEliminarAllArcos;
	private JButton btnEliminarAllVertices;
	private JButton btnGuardarGrafo;
	private JButton btnCargarGrafo;
	private JButton btnAgregarArco;
	private JButton btnAgregarVertice;

	private boolean modoAgregarVertice;
	private boolean modoAgregarArco;
	protected boolean modoEliminarVertice;
	
	private Coordinate[] arcoEnConstruccion;
	private JLabel lblBarraDeEstado;
	private JButton btnEliminarVertice;
	
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
		updateFrame();
		arcoEnConstruccion = new Coordinate[2];
	}

	private void showMapMarkers() {
		ArrayList<Coordinate> coords = mediator.getCoordenadas();
		for(Coordinate c : coords) {
			mapa.setDisplayPosition(c, mapa.getZoom());
			MapMarker m = new MapMarkerDot(c);
			mapa.addMapMarker(m);
		}
		System.out.println(coords.size());
	}

	private void setupPanelDeUsuario() {
		//setBounds panelDeUsuario ToDo
		panelDeUsuario = new JPanel();
		panelDeUsuario.setLayout(null);

		menuSeleccionArchivo = Recurso.setupMenuSeleccionArchivo();
		btnCargarGrafo = Recurso.setupBtnCargarGrafo();
		btnGuardarGrafo = Recurso.setupBtnGuardarGrafo();
		btnDibujarGrafoCompleto = Recurso.setupBtnDibujarGrafoCompleto();
		btnKruskal = Recurso.setupBtnKruskal(); 
		btnEliminarArco = Recurso.setupBtnEliminarArco();
		btnEliminarAllArcos = Recurso.setupBtnEliminarAllArcos();
		btnEliminarAllVertices = Recurso.setupBtnEliminarAllVertices();
		btnAgregarVertice = Recurso.setupBtnAgregarVertice();
		btnAgregarArco = Recurso.setupBtnAgregarArco(); 
		btnEliminarVertice = Recurso.setupBtnEliminarVertice();

		panelDeUsuario.add(menuSeleccionArchivo);
		panelDeUsuario.add(btnCargarGrafo);
		panelDeUsuario.add(btnGuardarGrafo);
		panelDeUsuario.add(btnDibujarGrafoCompleto);
		panelDeUsuario.add(btnKruskal);
		panelDeUsuario.add(btnEliminarArco);
		panelDeUsuario.add(btnEliminarAllArcos);
		panelDeUsuario.add(btnEliminarAllVertices);
		panelDeUsuario.add(btnAgregarVertice);
		panelDeUsuario.add(btnAgregarArco);
		panelDeUsuario.add(btnEliminarVertice);
		frame.getContentPane().add(panelDeUsuario);
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

	private void addMapaEvents() {
		mapa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//agregar vertice
				Coordinate coord = (Coordinate) mapa.getPosition(e.getPoint());
				if (modoAgregarVertice && mediator.agregarVertice(coord.getLat(), coord.getLon()))
					mapa.addMapMarker(new MapMarkerDot(coord.getLat(), coord.getLon()));

				//agregar arco
				MapMarker marker = masCercano(e.getPoint());
			    Coordinate cercano = marker != null ? marker.getCoordinate() : null;
				if (modoAgregarArco && cercano != null && mediator.existeCoordenada(cercano)) {
					lblBarraDeEstado.setText("Seleccione el vértice " + (arcoEnConstruccion[0] == null ? "destino" : "origen"));
					for (int i = 0; i < arcoEnConstruccion.length; i++) {
						if (cercano != null && arcoEnConstruccion[i] == null) {
							arcoEnConstruccion[i] = cercano;
							break;
						}
					}
					if(arcoEnConstruccion[1] != null) {
						if(arcoEnConstruccion[0] != arcoEnConstruccion[1] && mediator.agregarArco(arcoEnConstruccion))
							mostrarArcos();
						resetearArcoEnConstruccion();
					}
				}
					
				if (modoEliminarVertice && cercano != null && mediator.existeCoordenada(cercano)) {
						mediator.eliminarVertice(masCercano(e.getPoint()).getCoordinate());
						limpiarMapa();
						showMapMarkers();
						mostrarArcos();
			}

			mapa.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					if((modoEliminarVertice || modoAgregarArco) && masCercano(e.getPoint()) != null) 
						mapContainer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					 else 
					mapContainer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
		}
	});
	}

	private MapMarker masCercano(Point punto) {
		MapMarker ret = null;
			for (MapMarker marker : mapa.getMapMarkerList()) {
				Point p = mapa.getMapPosition(marker.getLat(), marker.getLon(), true);
				if (p != null) {
					int r = mapa.getRadius(marker, p);
					Rectangle rect = new Rectangle(p.x - r, p.y - r, r + r, r + r);
					if (rect.contains(punto)) {
						ret = marker;
						break;
					}
				}
			}
		return ret;
	}
	
	private void resetearArcoEnConstruccion() {
		arcoEnConstruccion[0] = null;
		arcoEnConstruccion[1] = null;
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
		
		btnEliminarAllArcos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediator.eliminarArcos();
				mapa.removeAllMapPolygons();
			}
		});

		btnEliminarAllVertices.addActionListener(new ActionListener() {
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
		
		btnAgregarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoAgregarArco = false;
				modoEliminarVertice = false;
				modoAgregarVertice = modoAgregarVertice? false : true;
				lblBarraDeEstado.setText("Modo agregar vértice " + (modoAgregarVertice ? "ON" : "OFF"));
			}
		});

		btnAgregarArco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoAgregarVertice = false;
				modoEliminarVertice = false;
				modoAgregarArco = modoAgregarArco ? false : true;
				lblBarraDeEstado.setText("Modo agregar arco " + (modoAgregarArco ? "ON - Seleccione vértice origen" : "OFF"));
			}
		});
		
		btnEliminarVertice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modoAgregarVertice = false;
				modoAgregarArco = false;
				modoEliminarVertice = modoEliminarVertice ? false : true;
				lblBarraDeEstado.setText("Modo eliminar vértice " + (modoEliminarVertice ? "ON" : "OFF"));
			}
		});
	}

	private void aplicarKruskal() {
		mediator.completarGrafo();
		mediator.aplicarKruskal();
		mostrarArcos();
	}

	private void setupMapContainer() {
		mapContainer = Recurso.setupMapContainer();
		mapa = Recurso.setupMapa();
		mapContainer.add(mapa);
		lblBarraDeEstado = Recurso.setupLblBarraDeEstado();
		mapa.add(lblBarraDeEstado);
	}

	private void setupFrame() {
		frame = Recurso.setupFrame();

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void updateFrame() {
		SwingUtilities.updateComponentTreeUI(panelDeUsuario);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}
}
