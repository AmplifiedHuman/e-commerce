package ie.ucd.ibot.repository;

import ie.ucd.ibot.entity.Category;
import ie.ucd.ibot.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategories(@Param("category") Category c, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String searchString, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndIsHidden(String searchString, boolean isHidden, Pageable pageable);
}