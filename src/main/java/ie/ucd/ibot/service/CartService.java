package ie.ucd.ibot.service;

import ie.ucd.ibot.entity.Cart;
import ie.ucd.ibot.entity.CartItem;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.repository.CartItemRepository;
import ie.ucd.ibot.repository.CartRepository;
import ie.ucd.ibot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Cart getCartByUserId(Long userID) {
        return cartRepository.getCartByUserId(userID);
    }

    @Transactional
    public void deleteCartItem(Long userID, Long productID) {
        Cart cart = cartRepository.getCartByUserId(userID);
        CartItem item = cartItemRepository.getByCartAndProductId(cart, productID);
        if (item == null) {
            throw new RuntimeException("Cannot delete cart item");
        }
        cartItemRepository.delete(item);
        cartItemRepository.flush();
    }

    @Transactional
    public boolean isCartCurrentlyValid(Cart cart) {
        for (CartItem item : cart.getCartItems()) {
            Product p = item.getProduct();
            if (item.getQuantity() > p.getQuantity() || p.isHidden()) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    public void removeInvalidItems(Cart cart) {
        Set<CartItem> removedItems = new HashSet<>();
        for (CartItem item : cart.getCartItems()) {
            Product p = item.getProduct();
            if (item.getQuantity() > p.getQuantity() || p.isHidden()) {
                removedItems.add(item);
            }
        }
        cart.getCartItems().removeAll(removedItems);
    }

    @Transactional
    public CartItem addCartItem(Long userID, Long productID, Integer quantity) {
        Cart cart = cartRepository.getCartByUserId(userID);
        Optional<Product> product = productRepository.findById(productID);
        CartItem item = cartItemRepository.getByCartAndProductId(cart, productID);
        if (cart == null || !product.isPresent()) {
            throw new RuntimeException("Invalid cart or product");
        }
        item = addCartItemHelper(product.get(), item, cart, quantity);
        return item;
    }

    public CartItem addSessionCartItem(Long productID, Integer quantity, Cart sessionCart) {
        Optional<Product> product = productRepository.findById(productID);
        CartItem item = getSessionCartItem(sessionCart, productID);
        if (!product.isPresent()) {
            throw new RuntimeException("Invalid product");
        }
        return addCartItemHelper(product.get(), item, sessionCart, quantity);
    }

    @Transactional
    public CartItem removeCartItem(Long userID, Long productID, Integer quantity) {
        Cart cart = cartRepository.getCartByUserId(userID);
        Optional<Product> product = productRepository.findById(productID);
        CartItem item = cartItemRepository.getByCartAndProductId(cart, productID);
        if (cart == null || !product.isPresent() || item == null) {
            throw new RuntimeException("Invalid cart, item or product");
        }
        item = removeCartItemHelper(product.get(), item, cart, quantity);
        return item;
    }

    public CartItem removeSessionCartItem(Long productID, Integer quantity, Cart sessionCart) {
        Optional<Product> product = productRepository.findById(productID);
        CartItem item = getSessionCartItem(sessionCart, productID);
        if (!product.isPresent() || item == null) {
            throw new RuntimeException("Invalid product or item");
        }
        return removeCartItemHelper(product.get(), item, sessionCart, quantity);
    }

    @Transactional
    public CartItem removeCartItemHelper(Product product, CartItem item, Cart cart, Integer quantity) {
        int cartItemCount = item.getQuantity();
        if (item.getQuantity() - quantity >= 1) {
            item.decrementQuantity(quantity);
        }
        return item;
    }

    @Transactional
    public CartItem addCartItemHelper(Product product, CartItem item, Cart cart, Integer quantity) {
        int cartItemCount = item == null ? 0 : item.getQuantity();
        if (product.getQuantity() >= quantity + cartItemCount) {
            if (item == null) {
                item = new CartItem();
                product.addItem(item);
                cart.addItem(item);
                item.setQuantity(quantity);
            } else {
                item.incrementQuantity(quantity);
            }
        }
        return item;
    }

    public CartItem getSessionCartItem(Cart sessionCart, long productID) {
        for (CartItem item : sessionCart.getCartItems()) {
            if (item.getProduct().getId() == productID) {
                return item;
            }
        }
        return null;
    }

    public void deleteSessionCartItem(Cart sessionCart, long productID) {
        CartItem removed = null;
        for (CartItem item : sessionCart.getCartItems()) {
            if (item.getProduct().getId() == productID) {
                removed = item;
            }
        }
        if (removed == null) {
            throw new RuntimeException("Cannot delete session cart item");
        }
        sessionCart.getCartItems().remove(removed);
        removed.setCart(null);
    }
}
