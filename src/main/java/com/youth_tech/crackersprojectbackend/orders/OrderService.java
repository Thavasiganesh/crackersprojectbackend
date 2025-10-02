package com.youth_tech.crackersprojectbackend.orders;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	@Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        // Generate unique order ID
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        // Create order entity
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerMobile(orderRequest.getCustomerMobile());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setCustomerAddress(orderRequest.getCustomerAddress());
        order.setCustomerCity(orderRequest.getCustomerCity());
        order.setCustomerState(orderRequest.getCustomerState());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setTotalItems(orderRequest.getTotalItems());
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        
        // Create order items
        if (orderRequest.getItems() != null) {
            order.setOrderItems(orderRequest.getItems().stream()
                .map(item -> {
                    OrderEntity orderItem = new OrderEntity();
                    orderItem.setOrder(order);
                    orderItem.setProductId(item.getProductId());
                    orderItem.setProductName(item.getProductName());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(item.getPrice());
                    return orderItem;
                })
                .collect(Collectors.toList())
            );
        }
        
        // Save order
        orderRepository.save(order);
        
        return new OrderResponse(orderId, "SUCCESS", "Order created successfully");
    }
	
}
