package com.devsocket.ecommerce.product.service;

import com.devsocket.ecommerce.event.models.product.ProductEvent;
import com.devsocket.ecommerce.event.models.product.ProductEventType;
import com.devsocket.ecommerce.kafka.messaging.KafkaProducer;
import com.devsocket.ecommerce.product.dtos.CategoryResponse;
import com.devsocket.ecommerce.product.dtos.ProductRequest;
import com.devsocket.ecommerce.product.dtos.ProductResponse;
import com.devsocket.ecommerce.product.entity.Category;
import com.devsocket.ecommerce.product.entity.Product;
import com.devsocket.ecommerce.product.repository.CategoryRepository;
import com.devsocket.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final KafkaProducer<ProductEvent> eventProducer;

    public ProductResponse createProduct(ProductRequest request) {
        // Convert CategoryRequest DTOs to Category entities
        List<Category> categories = request.getCategories().stream()
                .map(dto -> resolveOrCreateCategory(dto.name()))
                .toList();

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .availableQuantity(request.getAvailableQuantity())
                .categories(categories)
                .build();

        Product saved = repository.save(product);
        eventProducer.publish(mapProductEvent(saved, ProductEventType.CREATED));
        return mapToResponse(product);
    }

    public ProductResponse updateProduct(String id, ProductRequest request) {
        // Convert CategoryRequest DTOs to Category entities
        List<Category> categories = request.getCategories().stream()
                .map(dto -> resolveOrCreateCategory(dto.name()))
                .toList();

        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setAvailableQuantity(request.getAvailableQuantity());
        product.setCategories(categories);

        repository.save(product);

        eventProducer.publish(mapProductEvent(product, ProductEventType.UPDATED));

        return mapToResponse(product);
    }


    public void deleteProduct(String id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        repository.delete(product);

        eventProducer.publish(mapProductEvent(product, ProductEventType.DELETED));
    }

    private ProductEvent mapProductEvent(Product product, ProductEventType eventType) {
        return new ProductEvent(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(), product.getAvailableQuantity(),
                mapCategoryEntityToEvent(product.getCategories()), eventType, Instant.now());
    }

    private List<com.devsocket.ecommerce.event.models.product.Category> mapCategoryEntityToEvent(List<Category> entities) {
        return entities.stream()
                .map(entity -> new com.devsocket.ecommerce.event.models.product.Category(entity.getId(), entity.getName()))
                .toList();
    }
    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .categories(mapCategoryToCategoryResponse(product.getCategories()))
                .build();
    }

    private List<CategoryResponse> mapCategoryToCategoryResponse(List<Category> category){
        return category.stream()
                .map(entity -> new CategoryResponse(entity.getId(), entity.getName()))
                .toList();

    }
    private Category resolveOrCreateCategory(String name) {
        return categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category newCategory = new Category();
                    newCategory.setName(name);
                    return categoryRepository.save(newCategory);
                });
    }

}
