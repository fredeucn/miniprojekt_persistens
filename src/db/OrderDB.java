package db;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Invoice;
import model.Order;
import model.OrderLine;

public class OrderDB implements OrderDAO {
	private static final String insertOrderQuery = "INSERT INTO orders(customer_phone_number, delivery_status) VALUES"
			+ "(?, ?);";
	private static final String insertOrderLineQuery = "INSERT INTO order_lines(product_barcode, order_id, amount) VALUES"
			+ "(?, ?, ?);";
	private static final String insertInvoiceQuery = "INSERT INTO invoices(order_id, is_paid) VALUES"
			+ "(?, ?);";
	private PreparedStatement insertOrder, insertOrderLine, insertInvoice;

	public OrderDB() throws DataAccessException {
		try {
			Connection connection = ConnectDB.getInstance().getConnection();
			insertOrder = connection.prepareStatement(insertOrderQuery, java.sql.Statement.RETURN_GENERATED_KEYS);
			insertOrderLine = connection.prepareStatement(insertOrderLineQuery);
			insertInvoice = connection.prepareStatement(insertInvoiceQuery);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	
	@Override
	public boolean finishOrder(Order order, Invoice invoice) throws DataAccessException, SQLException {
		boolean success = true;
		
		// check if order has customer
		if (order.getCustomer() == null) {
			success = false;
		}
		
		// check if order has 1 order line
		if (order.getOrderLines().size() < 1) {
			success = false;
		}
		
		// check if order is already finished
		if (order.getDeliveryStatus() == "finished") {
			success = false;
		}
		
		// insert into database if order is valid
		if (success) {
			// run insert order into database get generated ID key
			insertOrder.setString(1, order.getCustomer().getPhoneNumber());
			insertOrder.setString(2, "finished");
			insertOrder.executeUpdate();

			ResultSet key = insertOrder.getGeneratedKeys();
		    if (key.next()) {
		        int orderId = key.getInt(1);
		        order.setOrderId(orderId);
		    }
		    key.close();
			
			// run insert order lines for each line in order use generated order ID key
			for (OrderLine currentOrderLine : order.getOrderLines()) {
				insertOrderLine.setInt(1, currentOrderLine.getProduct().getBarcode());
				insertOrderLine.setInt(2, order.getOrderId());
				insertOrderLine.setInt(3, currentOrderLine.getAmount());
				insertOrderLine.executeUpdate();
			}
			
			// run insert invoice using generated order ID key
			insertInvoice.setInt(1, order.getOrderId());
			insertInvoice.setBoolean(2, true);
			insertInvoice.executeUpdate();
		}
	
		// return if order is valid and successful
		return success;
	}
}
