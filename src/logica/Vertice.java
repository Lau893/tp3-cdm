package logica;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
	private int id;
	private double longitud, latitud;
	private List<Vertice> vecinos;

	public Vertice(int id) {
		idValido(id);
		this.id=id;
		this.longitud=Math.random() * (177.261864 - 164.078271) + 164.078271;
		this.latitud=Math.random() * (82.690736 - 79.894227) + 79.894227;
		this.vecinos= new ArrayList<Vertice>();
	}

	public void agregarVecino(Vertice i) {
		vecinos.add(i);
	}
	
	public void quitarVecino(Vertice i) {
		vecinos.remove(i);
	}
	
	public boolean existeVecino(Vertice i) {
		return vecinos.contains(i);
	}
	
	public boolean tieneHojas() {
		boolean ret = false;
		for (Vertice vertice : vecinos) 
			ret |= vertice.getVecinos().size() == 1;
		return ret;
	}
	
	private void idValido(int id) {
		if(id < 0) 
			throw new IllegalArgumentException("el id del vertice no puede ser negativo");			
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return "V:" + id;
	}

	public double getLongitud() {
		return longitud;
	}

	public double getLatitud() {
		return latitud;
	}
	
	public List<Vertice> getVecinos() {
		return vecinos;
	}
	
	@Override
	public String toString() {
	    StringBuilder datosDelVertice = new StringBuilder();
	    datosDelVertice.append("Vertice id:").append(id);
	    return datosDelVertice.toString();
	}

}
