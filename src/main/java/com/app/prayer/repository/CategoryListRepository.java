package com.app.prayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.prayer.entity.Category;

public interface CategoryListRepository extends JpaRepository<Category, Integer>{

}
