package inventoryManagement.service;

import inventoryManagement.dao.ITransactionDAO;
import inventoryManagement.dao.TransactionDAO;
import inventoryManagement.dao.entities.Transaction;
import org.bson.types.ObjectId;

import java.util.List;

public class TransactionService {
    private final ITransactionDAO transactionDAO = new ITransactionDAO();

    public ObjectId save(Transaction transaction) {
        return transactionDAO.save(transaction);
    }

    public List<Transaction> getAll() {
        return transactionDAO.getAll();
    }
}
