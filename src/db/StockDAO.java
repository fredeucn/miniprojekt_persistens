package db;
import model.*;


public interface StockDAO {
	Product findProduct(int barcode, int amount, String stockName) throws DataAccessException;
}
