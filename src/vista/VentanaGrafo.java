package vista;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import logica.AlgoritmoGoloso;
import logica.Grafo;
import logica.LectorArchivo;
import logica.Vertice;
import recursos.Arista;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;

public class VentanaGrafo extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMapViewer mapViewer;
	private JPanel panelPrincipal;

	public VentanaGrafo() {
		setTitle("Visualizador del Grafo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/icon.png"));
		setIconImage(icono.getImage());

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);

		mapViewer = new JMapViewer();
		mapViewer.setDisplayPosition(7900, 750, 5);
		mapViewer.setZoom(10);
		mapViewer.setZoomControlsVisible(false);
		mapViewer.setBounds(291, 11, 535, 539);
		panelPrincipal.add(mapViewer);

		JLabel lblNewLabel = new JLabel("Vertices del \r\nConjunto Dominante Minimo");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 11));
		lblNewLabel.setBounds(23, 11, 271, 42);
		panelPrincipal.add(lblNewLabel);

		JList<String> list = new JList<String>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setBounds(10, 49, 268, 424);
		panelPrincipal.add(list);

		DefaultListModel<String> listModel = new DefaultListModel<>();
		list.setModel(listModel);
		
		calcularGrupoDominante(listModel);

		JButton btnCalcularGrupo = new JButton("Recalcular Conjunto Dominante");
		btnCalcularGrupo.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCalcularGrupo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				calcularGrupoDominante(listModel);
			}
		});
		btnCalcularGrupo.setBounds(10, 484, 274, 66);
		panelPrincipal.add(btnCalcularGrupo);
	}

	private void calcularGrupoDominante(DefaultListModel<String> listModel) {
		Grafo grafo = LectorArchivo.leerArchivo("grafo.txt");

		Set<Vertice> cdm = AlgoritmoGoloso.conjuntoDominanteMinimo(grafo);

		mapViewer.removeAllMapMarkers();
		mapViewer.removeAllMapPolygons();

		Set<Vertice> vertices = grafo.getVertices();
		for (Vertice v : vertices) {
			agregarMarcador(v, Color.yellow);
		}

		Set<Arista> aristas = grafo.getAristas();
		for (Arista a : aristas) {
			agregarLinea(a);
		}

		for (Vertice v : cdm) {
			agregarMarcador(v, Color.red);
			listModel.addElement(v.toString());
		}
		mapViewer.setDisplayToFitMapMarkers();
	}

	private void agregarLinea(Arista a) {
		Vertice origen = a.getOrigen();
		Vertice destino = a.getDestino();
		double lat1 = origen.getLatitud();
		double lon1 = origen.getLongitud();
		double lat2 = destino.getLatitud();
		double lon2 = destino.getLongitud();
		List<Coordinate> coords = Arrays.asList(new Coordinate(lat1, lon1), new Coordinate(lat2, lon2),
				new Coordinate(lat2, lon2));
		MapPolygonImpl line = new MapPolygonImpl(coords);
		line.setColor(Color.black);
		mapViewer.addMapPolygon(line);
	}
	
	private void agregarMarcador(Vertice v, Color color) {
		MapMarkerDot marker = new MapMarkerDot(v.getLatitud(), v.getLongitud());
		marker.setBackColor(color);
		marker.setName(v.getNombre());
		mapViewer.addMapMarker(marker);
	}
}
