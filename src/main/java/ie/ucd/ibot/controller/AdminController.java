package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.*;
import ie.ucd.ibot.service.CustomerOrderService;
import ie.ucd.ibot.service.MessageService;
import ie.ucd.ibot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CustomerOrderService customerOrderService;
    private final MessageService messageService;
    private final UserService userService;

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

    @RequestMapping("/contact")
    public String viewMessages(Model model) {
        List<Message> messages = messageService.getAllMessages();

        if (messages != null) {
            Collections.sort(messages);
            model.addAttribute("messages", messages);
        }

        return "admin/contact";
    }

    @GetMapping("/contact/{id}")
    public String viewMessage(@PathVariable Long id, Model model) {
        // only allow users to view their own order
        Optional<Message> message = messageService.getMessageById(id);
        if (message.isEmpty()) {
            return "error";
        }
        model.addAttribute("message", message.get());
        return "shared/message";
    }

    @PostMapping("/contact/add")
    public String addMessage(@RequestParam Long id, @RequestParam String subject, @RequestParam String messageContent,
                             @RequestParam MessageType messageType, @RequestParam Long messageId) {
        if(id == null || subject == null || messageContent == null || messageType == null){
            return "error";
        }
        User user = userService.getUserById(id);
        messageService.getMessageById(messageId).get().setType(MessageType.ANSWERED);
        messageContent += "\n\nCUSTOMER WROTE:\n" + messageService.getMessageById(messageId).get().getMessageContent();
        messageService.createMessage(user, messageContent, subject, messageType);
        return "admin/contact";
    }
}
