package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.CustomerOrder;
import ie.ucd.ibot.entity.OrderStatus;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CustomerOrderService customerOrderService;

    @RequestMapping("/orders")
    public String viewOrders(Model model) {
        List<CustomerOrder> orderNew = customerOrderService.findCustomerOrderByStatus(OrderStatus.NEW);
        List<CustomerOrder> orderShipped = customerOrderService.findCustomerOrderByStatus(OrderStatus.SHIPPED);
        List<CustomerOrder> orderCancelled = customerOrderService.findCustomerOrderByStatus(OrderStatus.CANCELLED);
        List<CustomerOrder> orderDelivered = customerOrderService.findCustomerOrderByStatus(OrderStatus.DELIVERED);
        if (orderNew != null) {
            model.addAttribute("newOrders", orderNew);
        }
        if (orderShipped != null) {
            model.addAttribute("shipped", orderShipped);
        }
        if (orderCancelled != null) {
            model.addAttribute("cancelled", orderCancelled);
        }
        if (orderDelivered != null) {
            model.addAttribute("delivered", orderDelivered);
        }
        return "admin/orders";
    }

    @GetMapping("/order/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        // only allow users to view their own order
        Optional<CustomerOrder> customerOrder = customerOrderService.getOrderById(id);
        if (customerOrder.isEmpty()) {
            return "error";
        }
        model.addAttribute("order", customerOrder.get());
        return "shared/order";
    }

    @PostMapping("/order/update")
    public String updateOrder(@RequestParam Long id, @RequestParam OrderStatus newOrderStatus) {
        if(id == null || newOrderStatus == null) return "error";
        Optional<CustomerOrder> customerOrder = customerOrderService.getOrderById(id);
        if (customerOrder.isEmpty()) {
            return "error";
        }
        customerOrderService.updateOrder(id, newOrderStatus);
        return "shared/order";
    }
}
