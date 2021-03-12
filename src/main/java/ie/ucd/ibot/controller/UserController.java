package ie.ucd.ibot.controller;

import com.stripe.Stripe;
import ie.ucd.ibot.entity.ConfirmPaymentRequest;
import ie.ucd.ibot.entity.CustomerOrder;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.service.CustomerOrderService;
import ie.ucd.ibot.service.PaymentService;
import ie.ucd.ibot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private static final String stripeKey =
            "sk_test_51ISVypEwEmI24watTpBGrkUpeN43iz4aRE0MwRFeOumIt1iBpvOgC85qs7CaAKFRj5FYPBPRqrv38hZdP7Ph96Bw00HIj7zB6T";
    private final PaymentService paymentService;

    private final UserService userService;

    private final CustomerOrderService customerOrderService;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @ResponseBody
    @PostMapping("/pay")
    public Map<String, Object> processPayment(@RequestBody ConfirmPaymentRequest confirmPaymentRequest,
                                              @AuthenticationPrincipal User sessionUser, HttpServletResponse response) {
        try {
            Map<String, Object> responseData = paymentService.handleUserPayment(confirmPaymentRequest, sessionUser.getId());
            handleResponse(response, responseData);
            return responseData;
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error, please contact support.");
            return errorResponse;
        }
    }

    @GetMapping("/orders")
    public String viewOrders(@AuthenticationPrincipal User sessionUser, Model model) {
        User user = userService.getUserById(sessionUser.getId());
        List<CustomerOrder> orders = new ArrayList<>(user.getCustomerOrders());
        // sort orders so most recent appear first
        Collections.sort(orders);
        model.addAttribute("orders", orders);
        return "user/orders";
    }

    @GetMapping("/checkout")
    public String viewCheckout(@AuthenticationPrincipal User sessionUser, Model model) {
        User user = userService.getUserById(sessionUser.getId());
        if (user.getCart().getTotal() == 0) {
            return "error";
        }
        model.addAttribute("user", user);
        return "user/checkout";
    }

    @GetMapping("/order/{id}")
    public String viewOrder(@PathVariable Long id, @AuthenticationPrincipal User sessionUser, Model model) {
        // only allow users to view their own order
        Optional<CustomerOrder> customerOrder = customerOrderService.getOrderById(id);
        if (customerOrder.isEmpty() || !sessionUser.getId().equals(customerOrder.get().getUser().getId())) {
            return "error";
        }
        model.addAttribute("order", customerOrder.get());
        return "shared/order";
    }

    private void handleResponse(HttpServletResponse response,
                                Map<String, Object> responseMap) {
        response.setContentType("application/json");
        if (responseMap.containsKey("error") || responseMap.containsKey("invalid")) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else {
            response.setStatus(HttpStatus.OK.value());
        }
    }
}
