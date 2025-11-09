package com.pokkisham.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokkisham.app.model.CarouselImages;
import com.pokkisham.app.model.OfferPanelImages;
import com.pokkisham.app.service.CarouselImageService;
import com.pokkisham.app.service.OfferPanelImageService;

@RestController
@RequestMapping("/api/images/offer-panel")
public class OfferPanelImageController {
    
    
    @Autowired
    private OfferPanelImageService offerPanelImageService;

    

    @GetMapping
    public ResponseEntity<List<OfferPanelImages>> getAllImages() {
        
        List<OfferPanelImages> images = offerPanelImageService.getAllImages();
        if(images.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<OfferPanelImages>>(images, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferPanelImages> addOfferPanelImage(@RequestBody OfferPanelImages image) 
    {
        offerPanelImageService.addOfferPanelImages(image);
        return new ResponseEntity<OfferPanelImages>(image , HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/{imageId}")
    public ResponseEntity<OfferPanelImages> deleteImageById(@PathVariable Long imageId)
    {
        Optional<OfferPanelImages> image = offerPanelImageService.getImageById(imageId);

        if(image.isPresent())
        {
            offerPanelImageService.deleteOfferPanelImageById(imageId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
