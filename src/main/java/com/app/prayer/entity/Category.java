package com.app.prayer.entity;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryid;

  	@Column(name = "category_name")
  	private String category_name;
    
  	@Column(name="creation_date")
    private String creation_date;
  	
  	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Prayer_Request> prayer_request = new ArrayList<>();

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

	public List<Prayer_Request> getPrayer_request() {
		return prayer_request;
	}

	public void setPrayer_request(List<Prayer_Request> prayer_request) {
		this.prayer_request = prayer_request;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int categoryid, String category_name, String creation_date, List<Prayer_Request> prayer_request) {
		super();
		this.categoryid = categoryid;
		this.category_name = category_name;
		this.creation_date = creation_date;
		this.prayer_request = prayer_request;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", category_name=" + category_name + ", creation_date="
				+ creation_date + ", prayer_request=" + prayer_request + "]";
	}

}
