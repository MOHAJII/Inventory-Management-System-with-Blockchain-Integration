package inventoryManagement.dao.entities;

import inventoryManagement.dao.entities.enums.TransactionType;
import org.bson.types.ObjectId;

import java.security.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;

public class Transaction {
    private ObjectId id;
    private ObjectId productId;
    private String transactionType;
    private int quantity;
    private long timestamp;
    private double totalValue;
    private ObjectId userId;
    private String userName;
    private String billPath;

    public Transaction() {
    }

    public Transaction(ObjectId productId, String transactionType, int quantity, long timestamp, double totalValue, ObjectId userId, String userName, String billPath) {
        this.productId = productId;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.totalValue = totalValue;
        this.userId = userId;
        this.userName = userName;
        this.billPath = billPath;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public String getBillPath() {
        return billPath;
    }

    public void setBillPath(String billPath) {
        this.billPath = billPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", productId=" + productId +
                ", transactionType='" + transactionType + '\'' +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ", totalValue=" + totalValue +
                ", userId=" + userId +
                ", billPath='" + billPath + '\'' +
                '}';
    }
}
