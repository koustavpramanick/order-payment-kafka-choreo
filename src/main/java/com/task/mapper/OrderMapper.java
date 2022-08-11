package com.task.mapper;

import org.mapstruct.Mapper;

import com.task.dto.OrderRequest;
import com.task.model.Order;

@Mapper
public interface OrderMapper {

	Order mapRequestToOrder(OrderRequest orderRequest);
}
