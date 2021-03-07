package ie.ucd.ibot.repository;

import ie.ucd.ibot.entity.Cart;
import ie.ucd.ibot.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem getByCartAndProductId(Cart cart, Long productId);
}
