package ie.ucd.ibot.service;

import ie.ucd.ibot.entity.Category;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.repository.CategoryRepository;
import ie.ucd.ibot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> findPaginated(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByCategoryPaginated(String category, Pageable pageable) {
        Optional<Category> c = categoryRepository.findById(category);
        if (c.isPresent()) {
            return productRepository.findByCategories(c.get(), pageable);
        }
        return Page.empty();
    }

    public Page<Product> findByName(String searchString, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(searchString, pageable);
    }
}
