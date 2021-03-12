package ie.ucd.ibot.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @Column
    @NotEmpty(message = "Product name cannot be empty.")
    private String name;

    @Column(length = 2000)
    @Size(min = 1, max = 2000, message = "Description must be between 1 and 2000 characters.")
    private String description;

    @Column(length = 300)
    private String imageURL;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> categories;

    @Column
    @Positive(message = "Quantity must be greater than 1")
    private int quantity;

    @Column
    @Positive(message = "Price must be greater than 1")
    private double price;

    @Column
    private boolean isHidden;

    @Column
    @Min(value = 0, message = "Discount Rate should not be less than 0")
    @Max(value = 1, message = "Discount Rate should not be greater than 1")
    private double discountRate;

    @Transient
    private double originalPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<CartItem> items;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price * (1 - discountRate);
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginalPrice() {
        return price;
    }

    public void setOriginalPrice(double originalPrice) {
        this.price = originalPrice;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addItem(CartItem item) {
        items.add(item);
        item.setProduct(this);
    }

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setProduct(this);
    }

    public void decrementQuantity(int n) {
        if (quantity - n >= 0) {
            quantity -= n;
        }
    }
}
