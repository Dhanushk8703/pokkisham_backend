package com.pokkisham.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokkisham.app.model.CarouselImages;
import com.pokkisham.app.repository.CarouselImageRepository;


@Service
public class CarouselImageService {
    

    @Autowired
    private CarouselImageRepository carouselImageRepository;

    public List<CarouselImages> getAllImages()
    {
        return carouselImageRepository.findAll();
    }

    public CarouselImages addCarouselImages(CarouselImages images)
    {
        return carouselImageRepository.save(images);
    }

    public void deleteCarouselImageById(Long carouselImageId)
    {
        Optional<CarouselImages> image = carouselImageRepository.findById(carouselImageId);

        if (image.isPresent())
        {
            carouselImageRepository.deleteById(carouselImageId);
        }
    }

    public Optional<CarouselImages> getImageById(Long imageId)
    {
        Optional<CarouselImages> image = carouselImageRepository.findById(imageId);

        if(image.isPresent())
        {
            return image;
        }
        else{
            return null;
        }
    }


}
