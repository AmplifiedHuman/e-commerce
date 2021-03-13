package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.Cart;
import ie.ucd.ibot.entity.CartItem;
import ie.ucd.ibot.entity.Result;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    private final CartService cartService;

    @Autowired
    public CartController(Cart cart, CartService cartService) {
        this.cartService = cartService;
        cart.setCartItems(new ArrayList<>());
        this.cart = cart;
    }

    @GetMapping("/")
    public String showCart(Model model, @AuthenticationPrincipal User user) {
        if (user == null) {
            model.addAttribute("cart", cart);
        } else {
            // fetch latest cart from db
            model.addAttribute("cart", cartService.getCartByUserId(user.getId()));
        }
        return "cart";
    }

    @ResponseBody
    @GetMapping("/total")
    public String getTotal(@AuthenticationPrincipal User user) {
        Numbers numbers = new Numbers(Locale.US);
        return numbers.formatCurrency(user == null ? cart.getTotal() :
                cartService.getCartByUserId(user.getId()).getTotal());
    }

    @ResponseBody
    @PostMapping("/add")
    public Map<String, ?> addItem(@RequestParam Long productID, @RequestParam Integer quantity,
                                  @AuthenticationPrincipal User user) {
        CartItem item;
        // session cart
        if (user == null) {
            item = cartService.addSessionCartItem(productID, quantity, cart);
        } else {
            item = cartService.addCartItem(user.getId(), productID, quantity);
        }
        if (item != null) {
            return getStringMap(item);
        }
        return new HashMap<>();
    }

    @ResponseBody
    @PostMapping("/remove")
    public Map<String, ?> removeItem(@RequestParam Long productID, @RequestParam Integer quantity,
                                     @AuthenticationPrincipal User user) {
        CartItem item;
        // session cart
        if (user == null) {
            item = cartService.removeSessionCartItem(productID, quantity, cart);
        } else {
            item = cartService.removeCartItem(user.getId(), productID, quantity);
        }
        return getStringMap(item);
    }

    private Map<String, ?> getStringMap(CartItem item) {
        Map<String, Object> map = new HashMap<>();
        map.put("count", item.getQuantity());
        map.put("price", new Numbers(Locale.US).formatCurrency(item.getPrice()));
        return map;
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result deleteItem(@AuthenticationPrincipal User user, @RequestParam Long productID) {
        try {
            // session cart
            if (user == null) {
                cartService.deleteSessionCartItem(cart, productID);
            } else {
                // db cart
                cartService.deleteCartItem(user.getId(), productID);
            }
            return new Result(Collections.singletonList("true"), true);
        } catch (RuntimeException e) {
            return new Result(Collections.singletonList("false"), false);
        }
    }
}
