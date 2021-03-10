package ie.ucd.ibot.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Cart cart;

    @Column
    private int quantity;

    public double getPrice() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void incrementQuantity(int n) {
        if (n > 0) {
            quantity += n;
        }
    }

    public void decrementQuantity(int n) {
        if (quantity - n >= 0) {
            quantity -= n;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
