package inventoryManagement.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import inventoryManagement.dao.entities.Transaction;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ITransactionDAO implements TransactionDAO {
    @Override
    public MongoCollection<Transaction> getCollection() {
        MongoDatabase mongoDatabase = DBConnection.getDatabase();
        return mongoDatabase.getCollection("transactions", Transaction.class);
    }

    @Override
    public ObjectId save(Transaction transaction) {
        getCollection().insertOne(transaction);
        return transaction.getId();
    }

    @Override
    public Optional<Transaction> getById(ObjectId id) {
        return Optional.ofNullable(getCollection().find(Filters.eq("_id", id)).first());
    }

    @Override
    public Optional<Transaction> update(Transaction transaction) {
        return Optional.ofNullable(getCollection().findOneAndReplace(Filters.eq("_id", transaction.getId()), transaction));
    }

    @Override
    public void deleteById(ObjectId id) {
        getCollection().findOneAndDelete(Filters.eq("_id", id));
    }

    @Override
    public List<Transaction> getAll() {
        MongoCursor<Transaction> cursor = getCollection().find().cursor();
        List<Transaction> transactions = new ArrayList<>();
        while (cursor.hasNext()) {
            transactions.add(cursor.next());
        }
        return transactions;
    }
}
