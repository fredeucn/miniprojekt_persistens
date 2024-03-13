package db;
import model.*;


public interface StockDAO {
	Product findProduct(int id, int amount, String stockName) throws DataAccessException;
}
