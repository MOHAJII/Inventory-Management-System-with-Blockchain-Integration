package inventoryManagement.dao;

import com.mongodb.client.MongoCollection;

import java.util.List;
import java.util.Optional;

public interface DAO<T, U> {
    public MongoCollection<T> getCollection();

    public U save(T o);

    public Optional<T> getById(U id);

    public Optional<T> update(T o);

    public void deleteById(U id);

    public List<T> getAll();
}
