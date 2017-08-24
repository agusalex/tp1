package com.mycompany.app.presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaEtiqueta
{
	private JFrame frame;
	private JTable tablaEtiquetas;
	private JButton btnAgregarEtiqueta;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelEtiquetas;
	private  String[] nombreColumnas = {"Nombre"};

	public VistaEtiqueta() 
	{
		super();
		initialize();
	}


	public JButton getBtnEditar() {
		return btnEditar;
	}

	private void initialize()
	{
		frame = new JFrame("Etiquetas");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spEtiquetas = new JScrollPane();
		spEtiquetas.setBounds(10, 11, 414, 182);
		panel.add(spEtiquetas);
		
		modelEtiquetas = new DefaultTableModel(null,nombreColumnas);
		tablaEtiquetas = new JTable(modelEtiquetas);
		


		
		spEtiquetas.setViewportView(tablaEtiquetas);
		
		btnAgregarEtiqueta = new JButton("Agregar");
		btnAgregarEtiqueta.setBounds(10, 228, 89, 23);
		panel.add(btnAgregarEtiqueta);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);

	}
	
	public void show()
	{
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregarEtiqueta()
	{
		return btnAgregarEtiqueta;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}

	
	public DefaultTableModel getModelEtiquetas()
	{
		return modelEtiquetas;
	}
	
	public JTable getTablaEtiquetas()
	{
		return tablaEtiquetas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}
}