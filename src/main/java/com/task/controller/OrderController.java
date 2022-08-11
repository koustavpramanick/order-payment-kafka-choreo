package com.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.OrderAcknowledgement;
import com.task.dto.OrderRequest;
import com.task.model.Order;
import com.task.model.Status;
import com.task.service.*;

@RestController
public class OrderController {
	
	@Autowired
	OrderService orderService;
    
	@PostMapping("/placeOrder")
    public ResponseEntity<OrderAcknowledgement> placeOrder(@RequestBody OrderRequest request) {
		OrderAcknowledgement orderAck = orderService.placeOrder(request);
		
        return (orderAck.getOrder() != null && orderAck.getOrderStatus().equals(Status.PROCESSING))?
        		ResponseEntity.ok(orderAck):ResponseEntity.notFound().build();
    }
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable int id){
		Order order = orderService.getOrderforId(id);
		return (order != null)?ResponseEntity.ok(order):ResponseEntity.noContent().build();
	}
	
	@GetMapping("/order")
	public ResponseEntity<List<Order>> getOrderById(){
		List<Order> order = orderService.getOrder();
		return (order != null)?ResponseEntity.ok(order):ResponseEntity.noContent().build();
	}
	
}
