package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class StockDB implements StockDAO {
	private static final String findProductQ = "bla bla bla";
	private PreparedStatement findProduct;

	public StockDB() throws DataAccessException {
		try {
			findProduct = ConnectDB.getInstance().getConnection().prepareStatement(findProductQ);
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not prepare statement");
		}
	}

	@Override
	public Product findProduct(int id, int amount, String stockName) throws DataAccessException {
		try {
			findProduct.setInt(1, id);
			ResultSet rs = findProduct.executeQuery();
			Product p = null;
			if (rs.next()) {
				p = buildObject(rs);
			}
			return p;
		} catch (SQLException e) {
			throw new DataAccessException(e, "Could not find by id = " + id);
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
				rs.getInt("id"));
		return p;
	}

}
