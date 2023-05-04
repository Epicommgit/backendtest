package com.app.prayer.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "prayer_request")
public class Prayer_Request {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	@Column(name = "prayer_request_note")
  	private String prayer_request_note;
  		
	@Column(name = "country")
	private String country;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;
  	
  	@Column(name="creation_date")
    private String creation_date;

  	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userid", nullable = false)
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
  	
  	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
  	
  	
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPrayer_request_note() {
		return prayer_request_note;
	}

	public void setPrayer_request_note(String prayer_request_note) {
		this.prayer_request_note = prayer_request_note;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	
	public Prayer_Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Prayer_Request [id=" + id + ", prayer_request_note=" + prayer_request_note + ", country=" + country
				+ ", state=" + state + ", city=" + city + ", creation_date=" + creation_date + ", user=" + user
				+ ", category=" + category + "]";
	}

	public Prayer_Request(int id, String prayer_request_note, String country, String state, String city,
			String creation_date, User user, Category category) {
		super();
		this.id = id;
		this.prayer_request_note = prayer_request_note;
		this.country = country;
		this.state = state;
		this.city = city;
		this.creation_date = creation_date;
		this.user = user;
		this.category = category;
	}

	
	
}
