package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import recursos.Arista;

public class LectorArchivo {

	public static Grafo leerArchivo(String nombreArchivo) {
	    Grafo grafo = new Grafo();
	    try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
	        while (scanner.hasNextLine()) {
	            String linea = scanner.nextLine();
	            procesarLinea(linea, grafo);
	            
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    return grafo;
	}

    private static void procesarLinea(String linea, Grafo grafo) {
        String[] partes = linea.split(" ");
			if (partes.length == 2) {
			int id1 = Integer.parseInt(partes[0]);
            if (!grafo.existeVertice(id1)) {
            	Vertice vertice = new Vertice(id1);
                grafo.agregarVertice(vertice);
            }			
			int id2 = Integer.parseInt(partes[1]);
			if (!grafo.existeVertice(id2)) {
            	Vertice vertice = new Vertice(id2);
                grafo.agregarVertice(vertice);
            }
			Vertice ver1 = grafo.buscarVertice(id1);
			Vertice ver2 = grafo.buscarVertice(id2);
			Arista ar = new Arista(ver1,ver2);
			grafo.agregarArista(ar);
			grafo.hacerVecinos(ver1, ver2);;
		}
	}

}
