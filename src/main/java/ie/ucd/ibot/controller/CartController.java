package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.Cart;
import ie.ucd.ibot.entity.CartItem;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Numbers;

import java.util.*;

@Controller
@Scope("session")
@RequestMapping("/cart")
public class CartController {

    private final Cart cart;

    private final ProductService productService;

    @Autowired
    public CartController(Cart cart, ProductService productService) {
        cart.setCartItems(new ArrayList<>());
        this.cart = cart;
        this.productService = productService;
    }

    @GetMapping("/")
    public String showCart(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }

    @ResponseBody
    @GetMapping("/total")
    public String getTotal() {
        Numbers numbers = new Numbers(Locale.US);
        return numbers.formatCurrency(cart.getTotal());
    }

    @ResponseBody
    @PostMapping("/add")
    public Map<String, ?> addItem(@RequestParam long productID, @RequestParam int quantity) {
        Optional<Product> product = productService.findByID(productID);
        CartItem item = cart.getCartItem(productID);
        int cartItemCount = item == null ? 0 : item.getQuantity();
        if (product.isPresent() && product.get().getQuantity() >= quantity + cartItemCount) {
            Product p = product.get();
            if (item == null) {
                item = new CartItem();
                item.setProduct(p);
                item.setQuantity(quantity);
                cart.addItem(item);
            } else {
                item.incrementQuantity(quantity);
            }
        }
        assert item != null;
        return getStringMap(item);
    }

    @ResponseBody
    @PostMapping("/remove")
    public Map<String, ?> removeItem(@RequestParam long productID, @RequestParam int quantity) {
        Optional<Product> product = productService.findByID(productID);
        CartItem item = cart.getCartItem(productID);
        if (product.isPresent() && item != null && item.getQuantity() - quantity >= 1) {
            item.decrementQuantity(quantity);
        }
        assert item != null;
        return getStringMap(item);
    }

    private Map<String, ?> getStringMap(CartItem item) {
        assert item != null;
        Map<String, Object> map = new HashMap<>();
        map.put("count", item.getQuantity());
        map.put("price", new Numbers(Locale.US).formatCurrency(item.getPrice()));
        return map;
    }

    @ResponseBody
    @PostMapping("/delete")
    public String deleteItem(@RequestParam long productID) {
        Optional<Product> product = productService.findByID(productID);
        if (product.isPresent()) {
            cart.removeCartItem(productID);
            return "true";
        }
        return "false";
    }
}
