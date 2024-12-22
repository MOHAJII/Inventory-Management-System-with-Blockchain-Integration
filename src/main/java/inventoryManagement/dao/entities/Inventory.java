package inventoryManagement.dao.entities;

import org.bson.types.ObjectId;

public class Inventory {
    private ObjectId id;
    private ObjectId productId;
    private int quantity;
    private String location;
    private int reorderThreshold; // Minimum Quantity before restocking
    private int reorderQuantity; // Suggestion quantity to reorder

    public Inventory() {
    }

    public Inventory(ObjectId productId, String location, int quantity, int reorderThreshold, int reorderQuantity) {
        this.productId = productId;
        this.location = location;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
        this.reorderQuantity = reorderQuantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    public int getReorderQuantity() {
        return reorderQuantity;
    }

    public void setReorderQuantity(int reorderQuantity) {
        this.reorderQuantity = reorderQuantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", reorderThreshold=" + reorderThreshold +
                ", reorderQuantity=" + reorderQuantity +
                '}';
    }
}
