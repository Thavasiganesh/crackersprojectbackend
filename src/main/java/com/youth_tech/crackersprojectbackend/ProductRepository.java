package com.youth_tech.crackersprojectbackend;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByProductCategoryIgnoreCase(String category);
}
