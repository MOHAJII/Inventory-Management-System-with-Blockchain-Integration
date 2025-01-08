package inventoryManagement.dao.entities;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private ObjectId id;
    private String UPCCode;
    private String name;
    private String description;
    private ObjectId categoryId;
    private List<ObjectId> suppliersId;
    private double price;
    private double wholeSalePrice; // The supplier price, how much we get the product
    private String imageSrc;

    public Product() {
    }

    public Product(String UPCCode, String description, String name, ObjectId categoryId, List<ObjectId> suppliersId, double price, double wholeSalePrice) {
        this.UPCCode = UPCCode;
        this.description = description;
        this.name = name;
        this.categoryId = categoryId;
        this.suppliersId = suppliersId;
        this.price = price;
        this.wholeSalePrice = wholeSalePrice;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUPCCode() {
        return UPCCode;
    }

    public void setUPCCode(String UPCCode) {
        this.UPCCode = UPCCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(ObjectId categoryId) {
        this.categoryId = categoryId;
    }

    public List<ObjectId> getSupplierId() {
        return suppliersId;
    }

    public void setSupplierId(List<ObjectId> supplierId) {
        this.suppliersId = supplierId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWholeSalePrice() {
        return wholeSalePrice;
    }

    public void setWholeSalePrice(double wholeSalePrice) {
        this.wholeSalePrice = wholeSalePrice;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", suppliersId=" + suppliersId +
                ", price=" + price +
                ", wholeSalePrice=" + wholeSalePrice +
                '}';
    }
}
