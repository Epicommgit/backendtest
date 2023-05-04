package com.app.prayer.serviceImp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.prayer.dto.CategoryDto;
import com.app.prayer.entity.Category;
import com.app.prayer.exception.ResourceNotFoundException;
import com.app.prayer.repository.CategoryListRepository;
import com.app.prayer.service.CategoryService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryListRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Date date = new Date();
 		SimpleDateFormat formatter= new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm:ss");
 		String currentdate = formatter.format(date);
 	    System.out.println("currentdate   ::::     "+currentdate);
 	    
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		cat.setCreation_date(currentdate);
		Category addedCat = this.categoryRepository.save(cat);
		addedCat.getCreation_date();
		System.out.println("createCategory "+addedCat.getCreation_date());
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}
	@Override
	public CategoryDto getCategory(Integer categoryid) {
		Category cat = this.categoryRepository.findById(categoryid)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryid));

		return this.modelMapper.map(cat, CategoryDto.class);
	}
	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoryDto> catDtos = categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());

		return catDtos;
	}

	
	

}