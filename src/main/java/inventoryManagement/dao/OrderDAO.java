package inventoryManagement.dao;

import inventoryManagement.dao.entities.Order;
import inventoryManagement.dao.entities.Supplier;
import org.bson.types.ObjectId;

public interface OrderDAO extends DAO<Order, ObjectId> {
}
