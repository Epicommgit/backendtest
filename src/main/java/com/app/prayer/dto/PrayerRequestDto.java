package com.app.prayer.dto;

import javax.validation.constraints.NotEmpty;

public class PrayerRequestDto 
{
	@NotEmpty
	private String prayer_request;
	
  	private String creation_date;
  	
  	private CategoryDto category;

	private UserDto user;
  	
  	
	public String getPrayer_request() {
		return prayer_request;
	}
	public void setPrayer_request(String prayer_request) {
		this.prayer_request = prayer_request;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	

	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public PrayerRequestDto(@NotEmpty String prayer_request, String creation_date, CategoryDto category, UserDto user) {
		super();
		this.prayer_request = prayer_request;
		this.creation_date = creation_date;
		this.category = category;
		this.user = user;
	}
	public PrayerRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PrayerRequestDto [prayer_request=" + prayer_request + ", creation_date=" + creation_date + ", category="
				+ category + ", user=" + user + "]";
	}
	

}
