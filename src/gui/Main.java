package gui;

import db.CustomerDB;
import db.DataAccessException;
import db.StockDB;
import model.Customer;
import model.Product;

public class Main {
	public static void main(String[] args) throws DataAccessException {
		
		CustomerDB customerDB = new CustomerDB();
		Customer customer = customerDB.findCustomer("10203040");
		System.out.println(customer.toString());
		
		StockDB stockDB = new StockDB();
		Product product = stockDB.findProduct(1, 2, "Garage");
		System.out.println(product.toString());
	}
}
