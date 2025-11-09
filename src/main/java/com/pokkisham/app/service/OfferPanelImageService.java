package com.pokkisham.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokkisham.app.model.CarouselImages;
import com.pokkisham.app.model.OfferPanelImages;
import com.pokkisham.app.repository.CarouselImageRepository;
import com.pokkisham.app.repository.OfferPanelImageRepository;


@Service
public class OfferPanelImageService {
    
    
    @Autowired
    private OfferPanelImageRepository offerPanelImageRepository;

    public List<OfferPanelImages> getAllImages()
    {
        return offerPanelImageRepository.findAll();
    }

    public OfferPanelImages addOfferPanelImages(OfferPanelImages images)
    {
        return offerPanelImageRepository.save(images);
    }

    public void deleteOfferPanelImageById(Long offerPanelImageId)
    {
        Optional<OfferPanelImages> image = offerPanelImageRepository.findById(offerPanelImageId);

        if (image.isPresent())
        {
            offerPanelImageRepository.deleteById(offerPanelImageId);
        }
    }

    public Optional<OfferPanelImages> getImageById(Long imageId)
    {
        Optional<OfferPanelImages> image = offerPanelImageRepository.findById(imageId);

        if(image.isPresent())
        {
            return image;
        }
        else{
            return null;
        }
    }
}
