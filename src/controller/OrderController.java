package controller;
import java.sql.SQLException;
import java.util.Iterator;

import db.DataAccessException;
import db.OrderDB;
import model.*;

public class OrderController {
	private StockController stockController;
	private CustomerController customerController;
	private OrderDB orderDB;
	private Order currentOrder;
	
	public OrderController() throws DataAccessException {
		stockController = new StockController();
		customerController = new CustomerController();
		orderDB = new OrderDB();
	}
	
	public void createOrder() {
		currentOrder = new Order();
	}
	
	// this needs to be fixed, it is not merging order lines
	public void addProduct(int barcode, int amount, String stockName) throws DataAccessException {
		Product product = stockController.findProduct(barcode, amount, stockName);
		
		boolean isFound = false;
		for (int i = 0; i < currentOrder.getOrderLines().size() && !isFound; i++) {
			OrderLine currentOrderLine = currentOrder.getOrderLines().get(i);
			if (currentOrderLine.getProduct().equals(product)) {
				currentOrderLine.setAmount(currentOrderLine.getAmount() + amount);
				isFound = true;
			}
		}
		if (!isFound) {
			OrderLine orderLine = new OrderLine(amount, product);
			currentOrder.addOrderLine(orderLine);
		}
	}
	
	public void addCustomer(String phoneNumber) throws DataAccessException {
		Customer customer = customerController.findCustomer(phoneNumber);
		currentOrder.setCustomer(customer);
	}
	
	public void finishOrder() throws DataAccessException, SQLException {
		orderDB.finishOrder(currentOrder, null);
	}

}
