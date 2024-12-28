package inventoryManagement.test.testDAO;

import inventoryManagement.dao.ITransactionDAO;
import inventoryManagement.dao.TransactionDAO;
import inventoryManagement.dao.entities.Transaction;
import inventoryManagement.dao.entities.enums.TransactionType;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Map;

public class TestTransactionDAO {
    public static void main(String[] args) {
        TransactionDAO transactionDAO = new ITransactionDAO();
        Transaction transaction = new Transaction(TransactionType.SALE, "Sale", new ObjectId(), "Bill", new Date(), Map.of(new ObjectId().toString(), 20.0));
        // Save transaction
        //transactionDAO.save(transaction);
        // Get transaction
        transactionDAO.getAll().forEach(System.out::println);
        // Get byId
        System.out.println("Get By ID");
        transactionDAO.getById(new ObjectId("676ac64ed28ae87e7f682813")).ifPresent(System.out::println);

    }
}
