package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.repository.ProductRepository;
import ie.ucd.ibot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DefaultController {
    private final ProductService productService;

    @Autowired
    public DefaultController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/browse")
    public String browse(Model model, @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size, Optional<String> category) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        Page<Product> productPage;
        if (category.isPresent() && !category.get().isEmpty()) {
            productPage = productService.findByCategoryPaginated(category.get(),
                    PageRequest.of(currentPage - 1, pageSize));
            model.addAttribute("category", category.get());
        } else {
            productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        }
        model.addAttribute("products", productPage.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", productPage.getTotalPages());
        return "browse";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size, @RequestParam Optional<String> name) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String s = name.orElse("");
        Page<Product> productPage = productService.findByName(s, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("products", productPage.toList());
        model.addAttribute("total", productPage.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", productPage.getTotalPages());
        model.addAttribute("searchString", s);
        return "search";
    }

    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable int id) {
        Optional<Product> product = productService.findByID(id);
        if(product.isEmpty()) return "error";
        model.addAttribute("product", product.get());
        return "product";
    }
}
