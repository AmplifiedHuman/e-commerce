package ie.ucd.ibot.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
public class CustomerOrder implements Serializable {
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
    private Timestamp createdDate;

    @Column
    private String paymentId;

    public void addItem(OrderItem item) {
        orderItems.add(item);
        item.setCustomerOrder(this);
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
        item.setCustomerOrder(this);
    }
}
