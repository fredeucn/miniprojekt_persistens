package controller;
import db.*;
import model.*;

public class StockController {
	private StockDB stockDB;
	
	public StockController() throws DataAccessException {
		stockDB = new StockDB();
	}
	
	public Product findProduct(int barcode, int amount, String stockName) throws DataAccessException {
		return stockDB.findProduct(barcode, amount, stockName);
	}
}
