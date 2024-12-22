package inventoryManagement.dao.entities;

import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public class Order {
    private ObjectId id;
    private ObjectId supplierId;
    private List<OrderItem> orderItems;
    private Date orderDate;
    private OrderStatus orderStatus;

    public Order() {

    }

    public Order(ObjectId supplierId, List<OrderItem> orderItems, Date orderDate, OrderStatus orderStatus) {
        this.supplierId = supplierId;
        this.orderItems = orderItems;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getTotalPrice();
        }
        return total;
    }

    public ObjectId getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(ObjectId supplierId) {
        this.supplierId = supplierId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public enum OrderStatus {
        PENDING, APPROVED, REJECTED, SHIPPED, DELIVERED
    }
}
