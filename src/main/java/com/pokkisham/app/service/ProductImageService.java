package com.pokkisham.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokkisham.app.model.Product;
import com.pokkisham.app.model.ProductImage;
import com.pokkisham.app.repository.ProductImageRepository;
import com.pokkisham.app.repository.ProductRepository;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductImage addImage(String productId, String imageUrl) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        ProductImage image = new ProductImage();
        image.setImageUrl(imageUrl);
        image.setProduct(product);

        return productImageRepository.save(image);
    }

    public List<ProductImage> getImagesByProduct(String productId) {
        return productImageRepository.findAll()
                .stream()
                .filter(img -> img.getProduct().getProductId().equals(productId))
                .toList();
    }

    public void deleteImage(Long id) {
        productImageRepository.deleteById(id);
    }

    public List<ProductImage> getImagesByProductId(String productId) {
        return productImageRepository.findByProduct_ProductId(productId);
    }

}
