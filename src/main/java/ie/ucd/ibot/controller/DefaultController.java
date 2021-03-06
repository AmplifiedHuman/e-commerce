package ie.ucd.ibot.controller;

import ie.ucd.ibot.entity.InstantSearchResult;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.entity.UserRole;
import ie.ucd.ibot.service.ProductService;
import ie.ucd.ibot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class DefaultController {
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public DefaultController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal User sessionUser) {
        if (sessionUser != null) {
            User user = userService.getUserById(sessionUser.getId());
            model.addAttribute("user", user);
        }
        Page<Product> featuredProducts;
        Page<Product> offersProducts;
        featuredProducts = productService.findByCategoryPaginated("FEATURED", PageRequest.of(0, 4));
        offersProducts = productService.findByCategoryPaginated("OFFERS", PageRequest.of(0, 4));
        model.addAttribute("featured", featuredProducts.toList());
        model.addAttribute("offers", offersProducts.toList());

        return "index";
    }

    @GetMapping("/browse")
    public String browse(Model model, @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size, Optional<String> category) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(7);
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
                         @RequestParam("size") Optional<Integer> size, @RequestParam Optional<String> name,
                         @AuthenticationPrincipal User sessionUser) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(7);
        String s = name.orElse("");
        Page<Product> productPage;
        if (sessionUser == null || sessionUser.getUserRole().equals(UserRole.ROLE_USER)) {
            productPage = productService.findByName(s, PageRequest.of(currentPage - 1, pageSize), false);
        } else {
            productPage = productService.findByName(s, PageRequest.of(currentPage - 1, pageSize), true);
        }
        model.addAttribute("products", productPage.toList());
        model.addAttribute("total", productPage.getTotalElements());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", productPage.getTotalPages());
        model.addAttribute("searchString", s);
        return "search";
    }

    @ResponseBody
    @GetMapping("/instant-search")
    public List<InstantSearchResult> instantSearch(@RequestParam("size") Optional<Integer> size,
                                                   @RequestParam Optional<String> name,
                                                   @AuthenticationPrincipal User sessionUser) {
        // limit suggestions to top 5
        int pageSize = size.orElse(5);
        String s = name.orElse("");
        Page<Product> productPage;
        if (sessionUser == null || sessionUser.getUserRole().equals(UserRole.ROLE_USER)) {
            productPage = productService.findByName(s, PageRequest.of(0, pageSize), false);
        } else {
            productPage = productService.findByName(s, PageRequest.of(0, pageSize), true);
        }
        return productPage.map(
                (product -> new InstantSearchResult(product.getId(), product.getName(), product.getPrice()))).toList();
    }


    @GetMapping("/product/{id}")
    public String product(Model model, @PathVariable Long id) {
        Optional<Product> product = productService.findByID(id);
        if (!product.isPresent()) return "error";
        model.addAttribute("stock", product.get().getQuantity());
        model.addAttribute("product", product.get());
        return "product";
    }
}
