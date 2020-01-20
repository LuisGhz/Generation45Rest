package com.rest.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ArrayList;
import java.io.InputStream;

import com.rest.model.Product;

import java.io.IOException;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("/Products")
public class DAOProductsImpl {
	Connection conn = null;
	PreparedStatement ptsmnt = null;
	ResultSet rs = null;
	int rowsAffected = 0;
	String SQLSentence = "";
	Properties props = new Properties();
	InputStream in = this.getClass().getClassLoader().getResourceAsStream("DAO.properties");
	
	public Connection getConnection()
	{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		
		return conn;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveProduct(Product myProduct)
	{
		conn = getConnection();
		
		try
		{
			props.load(in);
			
			if (props != null)
			{
				SQLSentence = props.getProperty("SQLSaveProduct");
			}
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			ptsmnt = conn.prepareStatement(SQLSentence);
			
			ptsmnt.setString(1, myProduct.getProduct());
			ptsmnt.setString(2, myProduct.getProductName());
			ptsmnt.setDouble(3, myProduct.getProductPrice());
			
			rowsAffected = ptsmnt.executeUpdate();
			
			if (rowsAffected > 0)
			{
				System.out.println("Update executed!");
			} else
			{
				System.out.println("Something is wrong!");
			}
			
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			conn.close();
			ptsmnt.close();

		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateProduct(Product myProduct)
	{
		conn = getConnection();
		
		try
		{
			props.load(in);
			
			if (props != null)
			{
				SQLSentence = props.getProperty("SQLUpdateProduct");
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			ptsmnt = conn.prepareStatement(SQLSentence);
			
			ptsmnt.setString(1, myProduct.getProduct());
			ptsmnt.setString(2, myProduct.getProductName());
			ptsmnt.setDouble(3, myProduct.getProductPrice());
			ptsmnt.setInt(4, myProduct.getIdProduct());
			
			rowsAffected = ptsmnt.executeUpdate();
			
			if (rowsAffected > 0)
				System.out.println("Update executed!");
			else
				System.out.println("Something is wrong!");
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			conn.close();
			ptsmnt.close();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteProduct(Product product)
	{
		conn = getConnection();
		
		try
		{
			props.load(in);
			
			if(in != null)
			{
				SQLSentence = props.getProperty("SQLDeleteProduct");
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			ptsmnt = conn.prepareStatement(SQLSentence);
			
			ptsmnt.setInt(1, product.getIdProduct());
			
			rowsAffected = ptsmnt.executeUpdate();
			
			if(rowsAffected > 0)
				System.out.println("Delete executed!");
			else
				System.out.println("Something is wrong!");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			conn.close();
			ptsmnt.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/Single/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Product readProcut(Product product)
	{
		return null;
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public ArrayList<Product> readAllProducts()
	{
		return null;
	}
}
