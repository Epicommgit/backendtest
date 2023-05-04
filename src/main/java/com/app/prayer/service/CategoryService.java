package com.app.prayer.service;

import java.util.List;

import com.app.prayer.dto.CategoryDto;

public interface CategoryService {
	// create
			CategoryDto createCategory(CategoryDto categoryDto);

			
			// get
			CategoryDto getCategory(Integer categoryid);

			// get All

			List<CategoryDto> getCategories();
}
