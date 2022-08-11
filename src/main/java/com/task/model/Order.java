package com.task.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String orderName;
	private Double price;
	private int quantity;
	@Enumerated(EnumType.STRING)
	private Status paymentStatus;
	private Date orderDate;
	/*
	 * @Enumerated(EnumType.STRING) private Status orderStatus;
	 */
}
