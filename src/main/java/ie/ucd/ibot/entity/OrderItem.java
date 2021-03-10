package ie.ucd.ibot.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private CustomerOrder customerOrder;

    @Column
    private int quantity;

    @Column
    private double salePrice;
}
