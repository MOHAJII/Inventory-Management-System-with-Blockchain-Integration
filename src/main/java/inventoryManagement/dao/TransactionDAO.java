package inventoryManagement.dao;

import inventoryManagement.dao.entities.Order;
import inventoryManagement.dao.entities.Supplier;
import inventoryManagement.dao.entities.Transaction;
import org.bson.types.ObjectId;

public interface TransactionDAO extends DAO<Transaction, ObjectId> {
}
