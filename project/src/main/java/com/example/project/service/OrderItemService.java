package com.example.project.service;

import java.util.List;

import com.example.project.domain.entities.OrderItem;
import com.example.project.repository.OrderItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository repository;

    public List<OrderItem> list() {
        return repository.findAll();
    }

	public OrderItem buscaPorId(Integer id) {
		return repository.findById(id).get();
	}
}