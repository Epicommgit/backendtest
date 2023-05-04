package com.app.prayer.service;

import java.util.List;

import com.app.prayer.dto.FilterBean;
import com.app.prayer.dto.PrayerRequestDto;

public interface RequestService {
	
	PrayerRequestDto createRequest(PrayerRequestDto requestDto,Integer userid,Integer categoryid);
	
	List<PrayerRequestDto> getAllRequest();
	
	PrayerRequestDto getRequestByid(Integer id);
	
	List<PrayerRequestDto> getRequestByCategory(Integer categoryid);
	
	List<PrayerRequestDto> getRequestByUser(Integer userid);
	
	//search posts
	List<PrayerRequestDto> searchRequest(FilterBean filterBean);
}
