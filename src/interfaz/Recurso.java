package interfaz;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import mediator.Mediator;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class Recurso {

	public static JFrame setupFrame(){
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Clustering con AGM");
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Image miIcono = miPantalla.getImage("src/icono.png");
		frame.setIconImage(miIcono);
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
	
	public static JPanel setupMapContainer(){
		JPanel mapContainer = new JPanel();
		mapContainer.setBounds(0, 0, 641, 561);
		mapContainer.setLayout(null);
		return mapContainer;
	}
	
	public static JLabel setupLblBarraDeEstado() {
		JLabel lblBarraDeEstado = new JLabel("Barra de estado");
		lblBarraDeEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBarraDeEstado.setBounds(10, 536, 621, 14);
		return lblBarraDeEstado;
	}
	
	public static JMapViewer setupMapa() {
		JMapViewer mapa = new JMapViewer();
		mapa.setBounds(0, 0, 641, 561);
		return mapa;
	}
	
	//public static JPanel setupPanelDeUsuario(){}
	
	public static JComboBox setupMenuSeleccionArchivo() {
		JComboBox menuSeleccionArchivo = new JComboBox();
		menuSeleccionArchivo.setBounds(650, 32, 124, 22);
		menuSeleccionArchivo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuSeleccionArchivo.setToolTipText("Lista de archivos");
		String[] archivos = setupModelMenuArchivo();
		DefaultComboBoxModel model = new DefaultComboBoxModel(archivos);
		menuSeleccionArchivo.setModel(model);
		menuSeleccionArchivo.setSelectedIndex(0);
		return menuSeleccionArchivo;
	}

	private static String[] setupModelMenuArchivo() {
		String[] files = Mediator.getArchivos();
		String[] ret = new String[files.length+1];
		ret[0] = "Seleccione un archivo...";
		for (int i = 1; i < ret.length; i++)
			ret[i] = files[i-1];
		return ret;
	}

	public static JButton setupBtnCargarGrafo() {
		JButton btnCargarGrafo = new JButton("Cargar Grafo");
		btnCargarGrafo.setBounds(650, 65, 124, 23);
		btnCargarGrafo.setEnabled(false);
		return btnCargarGrafo;
	}	

	public static JButton setupBtnGuardarGrafo() {
		JButton	btnGuardarGrafo = new JButton("Guardar Grafo");
		btnGuardarGrafo.setBounds(650, 99, 124, 23);
		return btnGuardarGrafo;
	}	

	public static JButton setupBtnDibujarGrafoCompleto(){
		JButton btnDibujarGrafoCompleto = new JButton("Completar Grafo");
		btnDibujarGrafoCompleto.setBounds(650, 151, 124, 23);
		btnDibujarGrafoCompleto.setEnabled(true);
		return btnDibujarGrafoCompleto;
	}
	
	public static JButton setupBtnKruskal() {
		JButton	btnKruskal = new JButton("Aplicar Kruskal");
		btnKruskal.setBounds(650, 185, 124, 23);
		return btnKruskal;
	}

	public static JButton setupBtnCrearCluster() {
		JButton btnEliminarArco = new JButton("Crear Cluster");
		btnEliminarArco.setBounds(650, 265, 124, 22);
		btnEliminarArco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarArco.setEnabled(false);
		return btnEliminarArco;
	}

	public static JButton setupBtnEliminarAllArcos() {
		JButton btnEliminarAllArcos = new JButton("Eliminar All Arcos");
		btnEliminarAllArcos.setBounds(650, 409, 124, 23);
		return btnEliminarAllArcos;
	}

	public static JButton setupBtnEliminarAllVertices() {
		JButton btnEliminarAllVertices = new JButton("Eliminar Vértices");
		btnEliminarAllVertices.setBounds(650, 443, 124, 23);
		return btnEliminarAllVertices;
	}

	public static JButton setupBtnAgregarVertice() {
		JButton btnAgregarVertice = new JButton("");
		btnAgregarVertice.setBounds(655, 517, 33, 33);
		btnAgregarVertice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregarVertice.setIcon(new ImageIcon(Interfaz.class.getResource("/vertice.png")));
		btnAgregarVertice.setToolTipText("Agregar vértice");
		btnAgregarVertice.setBorder(new EmptyBorder(2, 2, 2, 2));
		return btnAgregarVertice;
	}

	public static JButton setupBtnAgregarArco() {
		JButton btnAgregarArco = new JButton("");
		btnAgregarArco.setBounds(698, 517, 33, 33);
		btnAgregarArco.setIcon(new ImageIcon(Interfaz.class.getResource("/arco.png")));
		btnAgregarArco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAgregarArco.setOpaque(true);
		btnAgregarArco.setToolTipText("Agregar arco");
		btnAgregarArco.setBorder(new EmptyBorder(2, 2, 2, 2));
		return btnAgregarArco;
	}
	
	public static JButton setupBtnEliminarVertice() {
		JButton btnEliminarVertice = new JButton("");
		btnEliminarVertice.setIcon(new ImageIcon(Interfaz.class.getResource("/verticeX.png")));
		btnEliminarVertice.setToolTipText("Eliminar vértice");
		btnEliminarVertice.setBorder(new EmptyBorder(2, 2, 2, 2));
		btnEliminarVertice.setBounds(741, 517, 33, 33);
		return btnEliminarVertice;
	}
	
	public static JLabel setupLblCoordenadaMouse() {
		JLabel lblCoordenadaMouse = new JLabel("X = ...   Y = ...");
		lblCoordenadaMouse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCoordenadaMouse.setBounds(40, 10, 621, 14);
		return lblCoordenadaMouse;
	}
}

