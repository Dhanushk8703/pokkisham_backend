package com.pokkisham.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokkisham.app.model.CarouselImages;
import com.pokkisham.app.service.CarouselImageService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/images/carousel")
public class CarouselImageController {
    
    @Autowired
    private CarouselImageService carouselImageService;

    

    @GetMapping
    public ResponseEntity<List<CarouselImages>> getAllImages() {
        
        List<CarouselImages> images = carouselImageService.getAllImages();
        if(images.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<CarouselImages>>(images, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarouselImages> addCarouselImage(@RequestBody CarouselImages image) 
    {
        carouselImageService.addCarouselImages(image);
        return new ResponseEntity<CarouselImages>(image , HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/{imageId}")
    public ResponseEntity<CarouselImages> deleteImageById(@PathVariable Long imageId)
    {
        Optional<CarouselImages> image = carouselImageService.getImageById(imageId);

        if(image.isPresent())
        {
            carouselImageService.deleteCarouselImageById(imageId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
