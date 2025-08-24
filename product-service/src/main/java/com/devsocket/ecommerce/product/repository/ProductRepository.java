package com.devsocket.ecommerce.product.repository;

import com.devsocket.ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.name = :name")
    List<Product> findByCategoryName(@Param("name") String name);

}
