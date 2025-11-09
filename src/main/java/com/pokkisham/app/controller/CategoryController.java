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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokkisham.app.model.Category;
import com.pokkisham.app.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
	   private CategoryService categoryService;
	
	  @PostMapping
	    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
	        return ResponseEntity.ok(categoryService.addCategory(category));
	    }
	  
	  @GetMapping
	  public ResponseEntity<List<Category>> getAllCategories() {
	  	List<Category> allCategories = categoryService.getAllCategory();
	  	
	  	if(allCategories.isEmpty())
	  	{
	  		return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
	  	}
	  	else
	  	{
	  		return new ResponseEntity<List<Category>>(allCategories,HttpStatus.OK);
	  	}
	  }
       @PutMapping("/{categoryId}")
	  public ResponseEntity<Category> editCategoryById(@PathVariable String categoryId, @RequestBody Category category) {
	  	//TODO: process PUT request
	  	Category updateExistingCategory = categoryService.updateCategory(categoryId, category);
	  	if(updateExistingCategory != null)
	  	{
	  		return new ResponseEntity<Category>(updateExistingCategory,HttpStatus.OK);
	  	}
	  	else
	  	{
	  		return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
	  	}
	  }

      @GetMapping("/{categoryId}")
		public ResponseEntity<Category> getCategoryById(@PathVariable String categoryId) {
			Optional<Category> getCategory = categoryService.getCategoryById(categoryId);
			if(getCategory.isPresent())
			{
				return new ResponseEntity<Category>(getCategory.get(),HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
			}
		}

        @DeleteMapping("/{categoryId}")
        public ResponseEntity<Category> deleteById(@PathVariable String categoryId)
        {
            Optional<Category> category = categoryService.getCategoryById(categoryId);

            if(category.isPresent())
            {
				categoryService.deleteCategory(categoryId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
}
