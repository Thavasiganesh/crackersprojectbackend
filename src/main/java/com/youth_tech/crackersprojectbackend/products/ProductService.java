package com.youth_tech.crackersprojectbackend.products;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {
	private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        List<Product> items=productRepository.findAll();
        for(Product p:items) {
        	if(p.getProductType().equalsIgnoreCase("Ground Crackers, New Varieties")) {
        		p.setProductType("Ground Crackers");
        	}
        }
        return items;
    }

    public List<Product> getProductsByCategory(String category) {
        List<Product> items= productRepository.findByProductType(category);
        if(category.equalsIgnoreCase("ground crackers")) {
	        for(int i=0;i<items.size();i++) {
	        	Product p=items.get(i);
	        	if(p.getProductType().equalsIgnoreCase("Ground Crackers, New Varieties"))
	        		p.setProductType("Ground Crackers");
	        }
        }
        if(category.equalsIgnoreCase("New Varieties")) {
        	for(int i=0;i<items.size();i++) {
	        	Product p=items.get(i);
	        	if(p.getProductType().equalsIgnoreCase("Ground Crackers, New Varieties"))
	        		p.setProductType("New Varieties");
	        }
        }
        return items;
    }
}
