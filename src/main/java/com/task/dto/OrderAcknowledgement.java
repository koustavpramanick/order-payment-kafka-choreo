package com.task.dto;

import com.task.model.Order;
import com.task.model.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAcknowledgement {

	private Order order;
	private Status orderStatus;
}
