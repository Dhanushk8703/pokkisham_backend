package com.pokkisham.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstagramVideo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long igId;

    private String videoUrl;

}
