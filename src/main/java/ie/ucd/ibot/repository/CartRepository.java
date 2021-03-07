package ie.ucd.ibot.repository;

import ie.ucd.ibot.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartByUserId(Long user_id);
}
