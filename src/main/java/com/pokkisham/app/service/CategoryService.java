package com.pokkisham.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokkisham.app.model.Category;
import com.pokkisham.app.model.Product;
import com.pokkisham.app.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	private String generateCategoryId() {
		Optional<Category> lastCategory = categoryRepo.findTopByOrderByCategoryIdDesc();

		if (lastCategory.isPresent()) {
			String lastId = lastCategory.get().getCategoryId();
			int lastNumber = Integer.parseInt(lastId.substring(6)); // Extract numeric part after "mc_pl_"
			int newNumber = lastNumber + 1;
			return String.format("PK_CT_%04d", newNumber); // Pad with leading zeros to 4 digits
		} else {
			return "PK_CT_0001"; // Initial ID with 4-digit padding
		}
	}

	public Category addCategory(Category category) {
		category.setCategoryId(generateCategoryId());
		return categoryRepo.save(category);
	}

	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	public Category updateCategory(String categoryId, Category category) {
		Optional<Category> existingCategory = categoryRepo.findById(categoryId);
		if (existingCategory.isPresent()) {
			Category updateExistingCategory = existingCategory.get();
			updateExistingCategory.setCategoryName(category.getCategoryName());
			updateExistingCategory.setCategoryDescription(category.getCategoryDescription());
			// Keep old image if new one is null or empty
			if (category.getImageUrl() != null && !category.getImageUrl().isEmpty()) {
				updateExistingCategory.setImageUrl(category.getImageUrl());
			}

			return categoryRepo.save(updateExistingCategory);
		} else {
			throw new RuntimeException("Category With ID: " + categoryId + " Not Found");
		}
	}

	public Optional<Category> getCategoryById(String categoryId) {
		Optional<Category> getCategory = categoryRepo.findById(categoryId);
		if (getCategory.isPresent()) {
			return getCategory;
		} else {
			throw new RuntimeException("Category with Category id : " + categoryId + "Not found");
		}
	}

	public void deleteCategory(String categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found"));

		category.getProducts().clear();

		categoryRepo.delete(category);
	}

}
