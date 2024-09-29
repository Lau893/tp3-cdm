package vista;

import javax.swing.*;

import logica.Grafo;
import logica.Vertice;
import recursos.Arista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class VentanaMenu extends JFrame {
    private static final long serialVersionUID = 1L;
	private DefaultListModel<String> modeloVertices;
    private DefaultListModel<String> modeloAristas;

    public VentanaMenu(Grafo grafo) {
        
        setTitle("Ventana Principal");
        setSize(456, 343);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon icono = new ImageIcon(getClass().getResource("/recursos/icon.png"));
		setIconImage(icono.getImage());
		
        modeloVertices = new DefaultListModel<>();
        modeloAristas = new DefaultListModel<>();
       
        llenarModeloVertices(grafo.getVertices());
        llenarModeloAristas(grafo.getAristas());
               
        JList<String> listaVertices = new JList<>(modeloVertices);
        JList<String> listaAristas = new JList<>(modeloAristas);
        
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JScrollPane(listaVertices));
        panel.add(new JScrollPane(listaAristas));

        getContentPane().add(panel);
        
        JButton botonVerGrafo = new JButton("Ver Grafo");
        botonVerGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
				VentanaGrafo display = new VentanaGrafo();
				display.setResizable(false);
				display.setVisible(true);
				display.setLocationRelativeTo(null);
            }
        });
        getContentPane().add(botonVerGrafo, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void llenarModeloVertices(Set<Vertice> set) {
        for (Vertice vertice : set) {
            modeloVertices.addElement("Vértice " + vertice.getId());
        }
    }
    private void llenarModeloAristas(Set<Arista> set) {
        for (Arista arista : set) {
            modeloAristas.addElement("Arista " + arista.getOrigen().getId() + " <-> " + arista.getDestino().getId());
        }
    }
}
