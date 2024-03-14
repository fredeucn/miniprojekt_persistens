package db;
import java.sql.SQLException;

import model.*;

public interface OrderDAO {
	public boolean finishOrder(Order order, Invoice invoice) throws DataAccessException, SQLException;
}
