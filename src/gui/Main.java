package gui;

import java.sql.SQLException;

import controller.OrderController;
import db.CustomerDB;
import db.DataAccessException;
import db.StockDB;
import model.Customer;
import model.OrderLine;
import model.Product;

public class Main {
	public static void main(String[] args) throws DataAccessException, SQLException {
		
		OrderController orderController = new OrderController();
		orderController.createOrder();
		orderController.addCustomer("10203040");
		orderController.addProduct(1, 2, "Garage");
		orderController.finishOrder();
	}
}
