package com.pokkisham.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokkisham.app.model.Product;
import com.pokkisham.app.service.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        if (product.getImages() != null) {
            product.getImages().forEach(image -> image.setProduct(product));
        }
        Product newProduct = productService.createProduct(product);

        return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {

        Optional<Product> product = productService.getProductById(productId);

        if (product.isPresent()) {
            return new ResponseEntity<Product>(product.get(), HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> editProductById(@PathVariable String productId, @RequestBody Product product) {
        // TODO: process PUT request
        Product updatedProduct = productService.updateProduct(productId, product);

        if (updatedProduct == null) {
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> deleteProductById(@PathVariable String productId) {
        Optional<Product> product = productService.getProductById(productId);

        if (product.isPresent()) {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
