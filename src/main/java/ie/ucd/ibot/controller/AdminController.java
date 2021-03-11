package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.CustomerOrder;
import ie.ucd.ibot.entity.OrderStatus;
import ie.ucd.ibot.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
