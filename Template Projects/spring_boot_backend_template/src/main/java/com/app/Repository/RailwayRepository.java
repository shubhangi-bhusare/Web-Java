package com.app.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.entity.Category;
import com.app.entity.Railway;


public interface RailwayRepository extends JpaRepository<Railway, Long> {
	List<Railway> findAllByname(String name);
	//Its Name should be match with database column name category in  (findByCategory)
	List<Railway> findAllByCategory(Category category);
	Railway findAllByName(String name);
	List<Railway> findAllByOrderByNameDesc();
}
