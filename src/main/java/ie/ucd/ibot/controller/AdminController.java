package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.*;
import ie.ucd.ibot.repository.CategoryRepository;
import ie.ucd.ibot.service.CustomerOrderService;
import ie.ucd.ibot.service.MessageService;
import ie.ucd.ibot.service.ProductService;
import ie.ucd.ibot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CustomerOrderService customerOrderService;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
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

    @PostMapping("/order")
    @ResponseBody
    public Result updateOrder(@RequestParam Long id, @RequestParam OrderStatus newOrderStatus) {
        try {
            customerOrderService.updateOrder(id, newOrderStatus);
            return new Result(Collections.singletonList("success"), true);
        } catch (Exception e) {
            return new Result(Collections.singletonList("failure"), false);
        }
    }

    @GetMapping("/edit/{id}")
    public String viewEditProduct(Model model, @PathVariable Long id) {
        if (id == null) return "error";
        Optional<Product> productOptional = productService.findByID(id);
        if (productOptional.isEmpty()) {
            return "error";
        }
        Product product = productOptional.get();
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("product", product);
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(Model model, @ModelAttribute("product") @Valid Product product, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("categories", categoryRepository.findAll());
                return "admin/edit";
            }
            productService.updateProduct(product);
        } catch (IOException e) {
            return "error";
        }
        return "redirect:/browse";
    }

    @PostMapping("/delete")
    @ResponseBody
    public Result removeProduct(@RequestParam Long id) {
        try {
            Optional<Product> productOptional = productService.findByID(id);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                productService.removeProduct(product);
                return new Result(Collections.singletonList("success"), true);
            } else {
                return new Result(Collections.singletonList("failure"), false);
            }
        } catch (Exception e) {
            return new Result(Collections.singletonList("failure"), false);
        }
    }

    @GetMapping("/add")
    public String fillProducts(Model model) {
        model.addAttribute("newProduct", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/add";
    }

    @PostMapping("/add")
    public String addProducts(Model model, @ModelAttribute("newProduct") @Valid Product newProduct,
                              BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("categories", categoryRepository.findAll());
                return "admin/add";
            } else if (newProduct.getTempImage() == null || newProduct.getTempImage().isEmpty()) {
                bindingResult.addError(new ObjectError("tempImage", "Image cannot be empty"));
                model.addAttribute("categories", categoryRepository.findAll());
                return "admin/add";
            }
            productService.updateProduct(newProduct);
        } catch (IOException e) {
            return "error";
        }
        return "redirect:/browse";
    }

    @GetMapping("/contact")
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
        if (id == null || subject == null || messageContent == null || messageType == null) {
            return "error";
        }
        String newMessageContent = "ADMIN WROTE:\n" + messageContent;
        newMessageContent += "\n\nCUSTOMER WROTE:\n" + messageService.getMessageById(messageId).get().getMessageContent();
        messageService.updateMessage(messageId, newMessageContent, subject, messageType);
        return "admin/contact";
    }
}
