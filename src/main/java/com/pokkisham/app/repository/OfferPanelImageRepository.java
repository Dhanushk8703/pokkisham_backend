package com.pokkisham.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokkisham.app.model.OfferPanelImages;

@Repository
public interface OfferPanelImageRepository  extends JpaRepository <OfferPanelImages,Long> {
    
}
