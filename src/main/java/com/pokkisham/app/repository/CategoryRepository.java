package com.pokkisham.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokkisham.app.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

	Optional<Category> findTopByOrderByCategoryIdDesc();
}

