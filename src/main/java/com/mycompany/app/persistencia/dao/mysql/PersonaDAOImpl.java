package com.mycompany.app.persistencia.dao.mysql;

import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.persistencia.conexion.Conexion;
import com.mycompany.app.persistencia.dao.interfaz.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class PersonaDAOImpl implements DAO
{
	private static final String insert = "INSERT INTO Personas(idPersona, nombre, telefono,calle,altura,piso,departamento,email,fechaNacimiento,idLocalidad,idEtiqueta) VALUES(?, ?, ?, ? , ?, ?, ?, ? ,? ,? ,?)";
	private static final String delete = "DELETE FROM Personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM Personas";


	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement=null;

		try 
		{

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4,persona.getCalle());
			statement.setInt(5,persona.getAltura());
			statement.setInt(6,persona.getPiso());
			statement.setString(7,persona.getDepartamento());
			statement.setString(8,persona.getEmail());
			statement.setDate(9,new java.sql.Date(persona.getFechaNacimmiento().getTime()));
			statement.setInt(10,persona.getIdLocalidad());
			statement.setInt(11,persona.getIdEtiqueta());



			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			if(statement!=null)
				System.out.println("error en la Sentencia SQL= "+statement.toString());
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}



	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<PersonaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return personas;
	}
}
