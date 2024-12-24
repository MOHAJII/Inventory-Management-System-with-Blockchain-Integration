package inventoryManagement.dao.entities;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class Transaction {
    private ObjectId id;
    private TransactionType transactionType;
    private String description;
    private ObjectId userId;
    private String bill;
    private Date date;
    private List<Inventory> inventories; // List of all inventory ( each inventory referenced by product id )

    public Transaction() {
    }

    public Transaction(TransactionType transactionType, String description, ObjectId userId, String bill, Date date, List<Inventory> inventories) {
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

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
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
