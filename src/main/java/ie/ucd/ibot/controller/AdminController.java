package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.CustomerOrder;
import ie.ucd.ibot.entity.OrderStatus;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.entity.Result;
import ie.ucd.ibot.repository.CategoryRepository;
import ie.ucd.ibot.service.CustomerOrderService;
import ie.ucd.ibot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/edit";
        }
        productService.updateProduct(product);
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
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/add";
        }
        productService.updateProduct(newProduct);
        return "redirect:/browse";
    }
}
