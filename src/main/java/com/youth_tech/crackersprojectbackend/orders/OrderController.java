package com.youth_tech.crackersprojectbackend.orders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins="*")
public class OrderController {
	
	private final OrderService orderService;
	
	

	public OrderController(OrderService orderService ) {
		this.orderService = orderService;
		
	}
	
	@PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Save order to database
            OrderResponse orderResponse = orderService.createOrder(orderRequest);
            
            // Send automated thank you message via WhatsApp
            
            
            return ResponseEntity.ok(orderResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
	
}
