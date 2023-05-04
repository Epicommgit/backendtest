package com.app.prayer.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {
	private int categoryid;

	@NotEmpty
	private String category_name;

	private String creation_date;

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(int categoryid, String category_name, String creation_date) {
		super();
		this.categoryid = categoryid;
		this.category_name = category_name;
		this.creation_date = creation_date;
	}

	
}
