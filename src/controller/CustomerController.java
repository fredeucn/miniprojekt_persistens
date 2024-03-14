package controller;

import db.CustomerDB;
import db.DataAccessException;
import model.Customer;

public class CustomerController {
	private CustomerDB customerDB;
	
	public CustomerController() throws DataAccessException {
		this.customerDB = new CustomerDB();
	}

	public Customer findCustomer(String phoneNumber) throws DataAccessException {
		return customerDB.findCustomer(phoneNumber);
	}
	
}
