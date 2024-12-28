package inventoryManagement.dao.entities;

import org.bson.types.ObjectId;

public class Inventory {
    private ObjectId id;
    private ObjectId productId;
    private int quantity;
    private String location;
    private int reorderThreshold; // Minimum Quantity before restocking
    private int reorderQuantity; // Suggestion quantity to reorder
    private boolean lowStock = false;
    private boolean outOfStock = false;

    public Inventory() {
    }

    public Inventory(ObjectId productId, String location, int quantity, int reorderThreshold, int reorderQuantity) {
        this.productId = productId;
        this.location = location;
        this.quantity = quantity;
        this.reorderThreshold = reorderThreshold;
        this.reorderQuantity = reorderQuantity;
        this.lowStock = this.quantity >= this.reorderThreshold;
        this.outOfStock = this.quantity == 0;
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
        this.lowStock = this.quantity >= this.reorderThreshold;
        this.outOfStock = this.quantity == 0;
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

    public boolean isLowStock() {
        return lowStock;
    }

    public boolean isOutOfStock() {
        return outOfStock;
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
                ", lowStock=" + lowStock +
                '}';
    }
}
