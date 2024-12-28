package inventoryManagement.dao.entities;

import inventoryManagement.dao.entities.enums.TransactionType;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Map;

public class Transaction {
    private ObjectId id;
    private TransactionType transactionType;
    private String description;
    private ObjectId userId;
    private String bill;
    private Date date;
    private Map<String, Double> inventories; // Representing the inventories that changed
    // Inventories<ProductId, Quantity> And then we map all the products and we change
    // the inventory of that product

    public Transaction() {
    }

    public Transaction(TransactionType transactionType, String description, ObjectId userId, String bill, Date date, Map<String, Double> inventories) {
        this.transactionType = transactionType;
        this.description = description;
        this.userId = userId;
        this.bill = bill;
        this.date = date;
        this.inventories = inventories;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, Double> getInventories() {
        return inventories;
    }

    public void setInventories(Map<String, Double> inventories) {
        this.inventories = inventories;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", bill='" + bill + '\'' +
                ", date=" + date +
                ", inventories=" + inventories +
                '}';
    }
}
