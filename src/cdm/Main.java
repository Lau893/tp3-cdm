package cdm;

import logica.Grafo;
import logica.LectorArchivo;
import vista.VentanaMenu;

public class Main {
	public static void main(String[] args) {
		Grafo grafo=LectorArchivo.leerArchivo("grafo.txt");
		VentanaMenu ventana= new VentanaMenu(grafo);
		ventana.setResizable(false);
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);
	}
}
