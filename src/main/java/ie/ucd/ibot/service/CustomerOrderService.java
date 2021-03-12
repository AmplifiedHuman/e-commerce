package ie.ucd.ibot.service;

import ie.ucd.ibot.entity.*;
import ie.ucd.ibot.repository.CustomerOrderRepository;
import ie.ucd.ibot.repository.OrderItemRepository;
import ie.ucd.ibot.repository.ProductRepository;
import ie.ucd.ibot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {
    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final OrderItemRepository orderItemRepository;

    private final CustomerOrderRepository customerOrderRepository;

    public Optional<CustomerOrder> getOrderById(Long id) {
        return customerOrderRepository.findById(id);
    }

    @Transactional
    public void createCustomerOrder(User user, String paymentId, String shippingAddress) {
        CustomerOrder order = new CustomerOrder();
        order.setOrderItems(new ArrayList<>());
        if (user.getCustomerOrders() == null) {
            user.setCustomerOrders(new ArrayList<>());
        }
        for (CartItem cartItem : user.getCart().getCartItems()) {
            Product p = cartItem.getProduct();
            OrderItem orderItem = new OrderItem();
            p.addOrderItem(orderItem);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSalePrice(p.getPrice());
            orderItemRepository.save(orderItem);
            order.addItem(orderItem);
            p.decrementQuantity(orderItem.getQuantity());
            productRepository.save(p);
        }
        // clear cart
        user.getCart().getCartItems().clear();
        order.setStatus(OrderStatus.NEW);
        order.setUser(user);
        order.setShippingAddress(shippingAddress);
        order.setPaymentId(paymentId);
        user.getCustomerOrders().add(order);
        userRepository.save(user);
    }

    @Transactional
    public void updateOrder(Long id, OrderStatus orderStatus) {
        Optional<CustomerOrder> customerOrder = customerOrderRepository.findById(id);
        if (customerOrder.isPresent()) {
            CustomerOrder order = customerOrder.get();
            order.setStatus(orderStatus);
            customerOrderRepository.save(order);
        }
    }

    public List<CustomerOrder> findCustomerOrderByStatus(OrderStatus orderStatus) {
        return customerOrderRepository.findCustomerOrdersByStatus(orderStatus);
    }

    public Long countOrdersByUserId(Long userId) {
        return customerOrderRepository.countCustomerOrderByUserId(userId);
    }
}
