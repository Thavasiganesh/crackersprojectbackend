package com.youth_tech.crackersprojectbackend.products;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
	
	private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET all products
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) String category) {
    	
        if (category == null || category.equalsIgnoreCase("all")) {
            return productService.getAllProducts();
        } else {
        	String[] arr=category.split("-");
        	category=arr[0]+" "+arr[1];
            return productService.getProductsByCategory(category);
        }
    }
}
