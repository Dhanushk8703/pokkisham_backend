package com.pokkisham.app.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String productId;

    private String name;

    private String description;

    private double price;
    private double offerPrice;

    private String badgeName;
    private String badgeColor;

   
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JsonManagedReference
    List<ProductImage> images;

    @ManyToOne
	@JoinColumn(name = "categoryId",nullable = false)
	private Category category;
}
