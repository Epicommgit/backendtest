package com.app.prayer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.prayer.entity.Category;
import com.app.prayer.entity.Prayer_Request;
import com.app.prayer.entity.User;

public interface PrayerRequestRepository extends JpaRepository<Prayer_Request, Integer>{
	
	
	List<Prayer_Request> findByUser(User user);
	List<Prayer_Request> findByCategory(Category category);	
	
	@Query("select p from Prayer_Request p where p.prayer_request_note like :key")	
	List<Prayer_Request> searchByRequest(@Param("key") String prayer_request);  
	
	
}