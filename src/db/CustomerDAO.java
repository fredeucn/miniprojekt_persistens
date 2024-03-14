package db;
import model.*;

public interface CustomerDAO {
	Customer findCustomer(String phoneNumber) throws DataAccessException;
}
