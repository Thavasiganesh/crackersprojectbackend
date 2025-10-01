package com.youth_tech.crackersprojectbackend.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<Product, Long> {

//	List<Product> findByProductCategoryIgnoreCase(String category);

	@Query("SELECT p FROM Product p WHERE LOWER(p.productType) LIKE LOWER(CONCAT('%', :type, '%'))")
	List<Product> findByProductType(@Param("type") String type);
}
