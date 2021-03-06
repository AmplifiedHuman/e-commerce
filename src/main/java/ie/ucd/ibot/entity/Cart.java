package ie.ucd.ibot.entity;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Component
@SessionScope
@Entity
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public CartItem getCartItem(long productID) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productID) {
                return item;
            }
        }
        return null;
    }

    public void removeCartItem(long productID) {
        CartItem removed = null;
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == productID) {
                removed = item;
            }
        }
        if (removed != null) {
            cartItems.remove(removed);
            removed.setCart(null);
        }
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
