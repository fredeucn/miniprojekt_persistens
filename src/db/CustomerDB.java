package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class CustomerDB implements CustomerDAO {
	private static final String findCustomerQuery = "SELECT phone_number, name, type, city.zip, street_name, street_number, city "
			+ "FROM customers INNER JOIN addresses ON customers.address_id = addresses.id "
			+ "INNER JOIN city ON addresses.zip = city.zip "
			+ "WHERE phone_number = ?";
	private PreparedStatement findCustomer;

	public CustomerDB() throws DataAccessException {
		try {
			Connection connection = ConnectDB.getInstance().getConnection();
			findCustomer = connection.prepareStatement(findCustomerQuery);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}
	
	@Override
	public Customer findCustomer(String phoneNumber) throws DataAccessException {
		try {
			findCustomer.setString(1, phoneNumber);
			ResultSet resultSet = findCustomer.executeQuery();
			Customer customer = null;
			if (resultSet.next()) {
				customer = buildObject(resultSet);
			}
			return customer;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find customer by phoneNumber = " + phoneNumber);
		}
	}

	private Customer buildObject(ResultSet resultSet) throws SQLException {
		Customer customer = new Customer(
				resultSet.getString("name"),
				resultSet.getString("street_name") + " " + resultSet.getString("street_number"),
				resultSet.getString("zip"),
				resultSet.getString("city"),
				resultSet.getString("phone_number"),
				resultSet.getString("type"));
		return customer;
	}
}
