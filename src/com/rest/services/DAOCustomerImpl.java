package com.rest.services;

import java.sql.Connection;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.io.InputStream;

import com.rest.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;


@Path("Customer")
public class DAOCustomerImpl implements DAOCustomer {
	
	Connection conn = null;
	PreparedStatement pstmnt = null;
	ResultSet rs = null;
	
	public Connection getConnection()
	{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		
		return conn;
	}

	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void SaveCustomer(Customer customer) {
		String sentenciaSQL = "INSERT INTO Customers (nameCustomer, addressCustomer, ageCustomer, heightCustomer, weightCustomer, isSingle) VALUES (?,?,?,?,?,?)";
		conn = getConnection();
		try {
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, customer.getNameCustomer());
			pstmnt.setString(2, customer.getAddressCustomer());
			pstmnt.setByte(3, customer.getAgeCustomer());
			pstmnt.setDouble(4, customer.getHeightCustomer());
			pstmnt.setDouble(5, customer.getWeightCustomer());
			pstmnt.setBoolean(6, customer.getIsSingle());
			pstmnt.executeUpdate();
			
			System.out.println("Record added to database!!" + customer.getIsSingle());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try 
		{
			conn.close();			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean UpdateCustomer(Customer customer) {
		String sentenciaSQL = "";
		conn = getConnection();
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("DAO.properties");
		int rowsAffected = 0;
		
		try
		{
			props.load(in);
			
			if (props != null)
			{
				sentenciaSQL = props.getProperty("SQLUpdateCustomer");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try {
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, customer.getNameCustomer());
			pstmnt.setString(2, customer.getAddressCustomer());
			pstmnt.setByte(3, customer.getAgeCustomer());
			pstmnt.setDouble(4, customer.getHeightCustomer());
			pstmnt.setDouble(5, customer.getWeightCustomer());
			pstmnt.setBoolean(6, customer.getIsSingle());
			pstmnt.setInt(7, customer.getIdCustomer());
			rowsAffected = pstmnt.executeUpdate();
			
			System.out.println("Record updated in the database!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try 
		{
			conn.close();	
			pstmnt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return (rowsAffected!=0)?true:false;
	}

	@Override
	@DELETE
	public boolean DeleteCustomer(Customer customer) {
		String sentenciaSQL = "DELETE FROM Customers WHERE idCustomer=?";
		conn = getConnection();
		
		try {
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setInt(1, customer.getIdCustomer());
			pstmnt.executeUpdate();
			
			System.out.println("Record deleted from the database!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try 
		{
			pstmnt.close();
			conn.close();			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@GET
	@Path("/single/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Customer ReadCostumer(@PathParam("id") int idCustomer) {
		String sentenciaSQL = "SELECT * FROM Customers WHERE idCustomer=?";
		conn = getConnection();
		PreparedStatement ptsmnt = null;
		ResultSet rs = null;
		Customer myCustomer = new Customer();
		
		try
		{			
			ptsmnt = conn.prepareStatement(sentenciaSQL);
			ptsmnt.setInt(1, idCustomer);
			rs = ptsmnt.executeQuery();
			rs.next();
			myCustomer.setIdCustomer(rs.getInt("idCustomer"));
			myCustomer.setNameCustomer(rs.getString("nameCustomer"));
			myCustomer.setAgeCustomer(rs.getByte("ageCustomer"));
			myCustomer.setAddressCustomer(rs.getString("addressCustomer"));
			myCustomer.setHeightCustomer(rs.getDouble("heightCustomer"));
			myCustomer.setWeightCustomer(rs.getDouble("weightCustomer"));
			myCustomer.setIsSingle(rs.getBoolean("isSingle"));
			
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			conn.close();
			rs.close();
			ptsmnt.close();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return myCustomer;
	}

	@Override
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Customer> ReadAll() {
		String sql = "";
		
		conn = getConnection();
		PreparedStatement pstmnt = null;
		ResultSet rs = null;
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("DAO.properties");
		Properties props = new Properties();
		List<Customer> myCustomers = new ArrayList<Customer>();
		
		try 
		{
			props.load(in);
			
			if (props != null)
			{
				sql = props.getProperty("SQLReadAllCutomers");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			pstmnt = conn.prepareStatement(sql);
			rs = pstmnt.executeQuery();
			
			while (rs.next())
			{
				Customer customer = new Customer();
				
				customer.setIdCustomer(rs.getInt("idCustomer"));
				customer.setNameCustomer(rs.getString("nameCustomer"));
				customer.setAgeCustomer(rs.getByte("ageCustomer"));
				customer.setAddressCustomer(rs.getString("addressCustomer"));
				customer.setHeightCustomer(rs.getDouble("heightCustomer"));
				customer.setWeightCustomer(rs.getDouble("weightCustomer"));
				customer.setIsSingle(rs.getBoolean("isSingle"));
				
				myCustomers.add(customer);
			}
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			conn.close();
			pstmnt.close();
			rs.close();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return myCustomers;
	}

}
