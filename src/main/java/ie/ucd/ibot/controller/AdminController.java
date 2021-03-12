package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.CustomerOrder;
import ie.ucd.ibot.entity.OrderStatus;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.repository.CategoryRepository;
import ie.ucd.ibot.repository.ProductRepository;
import ie.ucd.ibot.service.CustomerOrderService;
import ie.ucd.ibot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CustomerOrderService customerOrderService;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;

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

    @GetMapping("/edit/{id}")
    public String viewEditProduct(Model model, @PathVariable Integer id){
        Product product = productService.findByID(id).get();
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("product", product);
        return "admin/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(Model model, @PathVariable Integer id, @ModelAttribute("product") @Valid Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/edit";
        }
        productService.updateProduct(product);
        return "redirect:/browse";
    }



    @GetMapping ("/add")
    public String fillProducts(Model model){
        model.addAttribute("newProduct", new Product());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/add";
    }

    @PostMapping("/add")
    public String addProducts(Model model, @ModelAttribute("newProduct") @Valid Product newProduct, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/add";
        }
        productService.updateProduct(newProduct);
        return "redirect:/browse";
    }

}
