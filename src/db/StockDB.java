package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class StockDB implements StockDAO {
	private static final String findProductQuery = "SELECT * FROM stock_products "
			+ "INNER JOIN products ON product_barcode = products.barcode "
			+ "INNER JOIN stocks ON stock_id = stocks.id "
			+ "WHERE product_barcode = ? AND stocks.name = ?";
	private static final String updateStockQuery = "UPDATE stock_products "
			+ "SET amount = amount - ? "
			+ "WHERE product_barcode = ? "
			+ "AND stock_id = ( "
			+ "SELECT stocks.id FROM stocks "
			+ "WHERE stocks.name = ?)";
	private PreparedStatement findProduct, updateStock;

	public StockDB() throws DataAccessException {
		try {
			Connection connection = ConnectDB.getInstance().getConnection();
			findProduct = connection.prepareStatement(findProductQuery);
			updateStock = connection.prepareStatement(updateStockQuery);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}

	@Override
	public Product findProduct(int barcode, int amount, String stockName) throws DataAccessException {
		try {
			findProduct.setInt(1, barcode);
			findProduct.setString(2, stockName);
			ResultSet resultSet = findProduct.executeQuery();
			Product product = null;
			if (resultSet.next()) {
				if (resultSet.getInt("amount") >= amount) {
					product = buildObject(resultSet);
					updateStock.setInt(1, amount);
					updateStock.setInt(2, barcode);
					updateStock.setString(3, stockName);
					updateStock.executeUpdate();
				}
			}
			return product;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find product by barcode = " + barcode);
		}
	}

	private Product buildObject(ResultSet rs) throws SQLException {
		Product p = new Product(
				rs.getString("name"),
				rs.getFloat("purchase_price"),
				rs.getFloat("sales_price"),
				rs.getFloat("rent_price"),
				rs.getString("country_of_origin"),
				rs.getInt("min_stock"),
				rs.getInt("barcode"));
		return p;
	}

}
