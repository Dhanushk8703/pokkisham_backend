package com.pokkisham.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokkisham.app.model.ProductImage;
import com.pokkisham.app.service.ProductImageService;



@RestController
@RequestMapping("/api/images")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @PostMapping("/{productId}")
    public ResponseEntity<ProductImage> uploadImage(@PathVariable String productId, @RequestBody Map<String, String> body) 
    {
        String imageUrl = body.get("imageUrl");
        return ResponseEntity.ok(productImageService.addImage(productId, imageUrl));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<List<ProductImage>> getImages(@PathVariable String productId) 
    {
        return ResponseEntity.ok(productImageService.getImagesByProductId(productId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable Long id) 
    {
        productImageService.deleteImage(id);
        return ResponseEntity.ok("Image deleted successfully");
    }
}
