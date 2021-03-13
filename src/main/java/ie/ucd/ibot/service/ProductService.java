package ie.ucd.ibot.service;

import ie.ucd.ibot.entity.Category;
import ie.ucd.ibot.entity.Product;
import ie.ucd.ibot.repository.CategoryRepository;
import ie.ucd.ibot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageService imageService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          ImageService imageService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imageService = imageService;
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

    public Page<Product> findByName(String searchString, Pageable pageable, boolean isAdmin) {
        if (isAdmin) {
            return productRepository.findByNameContainingIgnoreCase(searchString, pageable);
        }
        return productRepository.findByNameContainingIgnoreCaseAndIsHidden(searchString, false, pageable);
    }

    public Optional<Product> findByID(long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void updateProduct(Product product) throws IOException {
        // save once to generate id
        productRepository.save(product);
        if (product.getTempImage() != null && !product.getTempImage().isEmpty()) {
            String imageURL = imageService.processImage(product.getTempImage(), product.getId().toString());
            product.setImageURL(imageURL);
        }
        productRepository.save(product);
    }

    @Transactional
    public void removeProduct(Product product) {
        productRepository.delete(product);
    }
}
