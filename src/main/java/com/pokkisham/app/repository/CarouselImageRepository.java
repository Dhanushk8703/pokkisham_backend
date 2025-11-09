package com.pokkisham.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokkisham.app.model.CarouselImages;


@Repository
public interface CarouselImageRepository extends JpaRepository<CarouselImages,Long> {
    
}
