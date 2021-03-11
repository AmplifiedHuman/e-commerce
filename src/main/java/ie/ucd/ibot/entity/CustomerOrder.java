package ie.ucd.ibot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class CustomerOrder implements Serializable, Comparable<CustomerOrder> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @Column
    private String paymentId;

    @Column
    private String shippingAddress;

    public void addItem(OrderItem item) {
        orderItems.add(item);
        item.setCustomerOrder(this);
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
        item.setCustomerOrder(this);
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getPrice();
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CustomerOrder)) return false;
        CustomerOrder customerOrder = (CustomerOrder) obj;
        return customerOrder.getId() == id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public int compareTo(CustomerOrder o) {
        return o.getCreatedDate().compareTo(createdDate);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
