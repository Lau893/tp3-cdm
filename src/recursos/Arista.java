package recursos;

import logica.Vertice;

public class Arista {
	private Vertice origen;
	private Vertice destino;

	public Arista(Vertice or, Vertice des) {
		this.origen=or;
		this.destino=des;
	}

	public Vertice getOrigen() {
		return origen;
	}

	public void setOrigen(Vertice origen) {
		this.origen = origen;
	}

	public Vertice getDestino() {
		return destino;
	}

	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	@Override
	public String toString() {
	    StringBuilder datosDeLaArista = new StringBuilder();
	    datosDeLaArista.append("Arista{")
	      .append("origen=").append(origen)
	      .append(", destino=").append(destino)
	      .append('}');
	    return datosDeLaArista.toString();
	}

}
