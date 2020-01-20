package com.rest.services;

import com.rest.model.Customer;
import java.util.List;
import java.sql.Connection;

public interface DAOCustomer {
	public void SaveCustomer(Customer customer);
	public boolean UpdateCustomer(Customer customer);
	public boolean DeleteCustomer(Customer customer);
	public Customer ReadCostumer(int idCustomer);
	public List<Customer> ReadAll();
}
