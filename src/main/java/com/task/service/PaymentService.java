package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.task.model.Order;
import com.task.model.Status;
import com.task.repository.OrderRepository;




@Service
public class PaymentService {
	
	@Autowired
	OrderRepository orderRepo;
	
	@KafkaListener(topics = "Order-Service",groupId = "group-1")
	public void processOrder(Order order) {
		 
		orderRepo.findById(order.getOrderId()).map(odr->{
			odr.setPaymentStatus(Status.SUCCESS);
			System.out.println("Order Payment Success: " + order.toString());
			orderRepo.save(odr);
			return odr;		
		}).orElseThrow(()-> {
			System.out.println("Order Payment Failure: " + order.toString());
			throw new RuntimeException("Order payment");
			});
	}

}
