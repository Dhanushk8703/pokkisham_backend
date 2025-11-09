package com.pokkisham.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokkisham.app.model.Product;
import com.pokkisham.app.repository.ProductRepository;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

      private String generateProductId() {
		Optional<Product> lastProduct = productRepository.findTopByOrderByProductIdDesc();

        if (lastProduct.isPresent()) {
            String lastId = lastProduct.get().getProductId();
            int lastNumber = Integer.parseInt(lastId.substring(7));
            int newNumber = lastNumber + 1;
            return String.format("PK_PR_%04d", newNumber); // Pad with leading zeros to 4 digits
        } else {
            return "PK_PR_0001"; // Initial ID with 4-digit padding
        }
	}

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        product.setProductId(generateProductId());
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedProduct.getName());
                    existing.setCategory(updatedProduct.getCategory());
                    existing.setDescription(updatedProduct.getDescription());
                    existing.setPrice(updatedProduct.getPrice());
                    existing.setOfferPrice(updatedProduct.getOfferPrice());
                    existing.setBadgeName(updatedProduct.getBadgeName());
                    existing.setBadgeColor(updatedProduct.getBadgeColor());
                    existing.setImages(updatedProduct.getImages());
                    return productRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }
}
