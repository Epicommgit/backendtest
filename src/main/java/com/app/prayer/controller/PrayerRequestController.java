package com.app.prayer.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.prayer.dto.FilterBean;
import com.app.prayer.dto.PrayerRequestDto;
import com.app.prayer.service.RequestService;

@RestController
@RequestMapping("/request")
public class PrayerRequestController {

	@Autowired
	private RequestService requestService;

	@PostMapping("/user/{userid}/category/{categoryid}/addrequest")
	public ResponseEntity<PrayerRequestDto> createPost(@RequestBody PrayerRequestDto requestDto,
			@PathVariable Integer userid, @PathVariable Integer categoryid) {
		PrayerRequestDto createPost = requestService.createRequest(requestDto, userid, categoryid);
		return new ResponseEntity<PrayerRequestDto>(createPost, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userid}/request")
	public ResponseEntity<List<PrayerRequestDto>> getPostsByUser(@PathVariable Integer userid) 
	{
		List<PrayerRequestDto> re = this.requestService.getRequestByUser(userid);
		return new ResponseEntity<List<PrayerRequestDto>>(re, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryid}/request")
	public ResponseEntity<List<PrayerRequestDto>> getPostsByCategory(@PathVariable Integer categoryid) 
	{
		List<PrayerRequestDto> re = this.requestService.getRequestByCategory(categoryid);
		return new ResponseEntity<List<PrayerRequestDto>>(re, HttpStatus.OK);
	}	

	@GetMapping("/")
	public ResponseEntity<List<PrayerRequestDto>> getAllRequest() 
	{
		List<PrayerRequestDto> realllist = this.requestService.getAllRequest();
		return new ResponseEntity<List<PrayerRequestDto>>(realllist, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <PrayerRequestDto> getRequestId(@PathVariable Integer id) 
	{
		PrayerRequestDto re = this.requestService.getRequestByid(id);
		return new ResponseEntity <PrayerRequestDto> (re, HttpStatus.OK);
	}	
	
	@PostMapping(path="/search", produces = "application/json")
    public ResponseEntity<List<PrayerRequestDto>> searchPostByTitle( @RequestBody FilterBean filterBean)
    {
		System.out.println("Userid: "+filterBean.getUserid());
		
		System.out.println("City: "+filterBean.getCity());
		
		System.out.println("State: "+filterBean.getState());
		//List<PrayerRequestDto> result = this.requestService.searchRequest(filterBean.getKeyword());		
		List<PrayerRequestDto> result = this.requestService.searchRequest(filterBean);
		return new ResponseEntity<List<PrayerRequestDto>>(result, HttpStatus.OK);
	}

}
