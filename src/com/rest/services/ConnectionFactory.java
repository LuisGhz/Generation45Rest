package com.rest.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	///Singleton Design Pattern
	
	//2.Instance of the class must be private and static
	private static ConnectionFactory conn = null;
	private static Connection driverConnection = null;
	
	//Declare my properties objects
	private static Properties props = new Properties();
	private static InputStream in = null;
	
	private static String driver = "";
	private static String urlServer = "";
	private static String username = "";
	private static String password = "";
	
	
	//1. Private constructor
	private ConnectionFactory() {}
	
	//3. getInstance method
	public static ConnectionFactory getInstance()
	{
		if (conn == null)
		{
			conn = new ConnectionFactory();
		}
		
		return conn;
	}
	
	public static Connection getConnection()
	{
		in = conn.getClass().getClassLoader().getResourceAsStream("DAO.properties");
		try {
			props.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(props != null)
		{
			driver = props.getProperty("driver");
			urlServer = props.getProperty("urlServer");
			username = props.getProperty("username");
			password = props.getProperty("password");
		}
		
		try 
		{
			Class.forName(driver);
			
			driverConnection = DriverManager.getConnection(urlServer, username, password);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return driverConnection;
	}
}
