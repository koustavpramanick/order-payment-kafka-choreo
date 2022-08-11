package com.task.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.task.dto.OrderAcknowledgement;
import com.task.dto.OrderRequest;
import com.task.mapper.OrderMapper;
import com.task.model.Order;
import com.task.model.Status;
import com.task.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	KafkaTemplate<String,Order> kafkaTemplate;
	
	private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);
	
	@Transactional
	public OrderAcknowledgement placeOrder(OrderRequest request) {	
		
		Order order = mapper.mapRequestToOrder(request);
		order.setPaymentStatus(Status.PENDING);
		order.setOrderDate(new Date());
		
		Order savedOrder = null;
		try {
			savedOrder = orderRepo.save(order);
			System.out.println("Order saved with id:" + savedOrder.getOrderId());
			ListenableFuture<SendResult<String,Order>> sendOrder = kafkaTemplate.send("Order-Service", savedOrder);
			System.out.println("Order sent to Kafka:" + sendOrder.toString());
			return new OrderAcknowledgement(savedOrder,Status.PROCESSING);
		} catch (Exception e) {
			System.out.println("Error occured :" + e.getMessage());
			return new OrderAcknowledgement(order,Status.FAILED);
		}
		
	}

	public Order getOrderforId(int id) {
		Optional<Order> order = orderRepo.findById(id);
		return order.isPresent()?order.get():null;
		
	}

	public List<Order> getOrder() {
		return orderRepo.findAll();
	}

}
