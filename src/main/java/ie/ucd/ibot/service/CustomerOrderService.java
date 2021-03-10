package ie.ucd.ibot.service;

import ie.ucd.ibot.entity.*;
import ie.ucd.ibot.repository.OrderItemRepository;
import ie.ucd.ibot.repository.ProductRepository;
import ie.ucd.ibot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomerOrderService {
    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void createCustomerOrder(User user, String paymentId) {
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
        order.setStatus(OrderStatus.RECEIVED);
        order.setUser(user);
        order.setPaymentId(paymentId);
        user.getCustomerOrders().add(order);
        userRepository.save(user);
    }
}
