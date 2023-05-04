package com.app.prayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.prayer.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByEmail( String email);
}
