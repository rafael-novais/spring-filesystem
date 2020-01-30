package com.example.project.service;

import java.util.List;

import com.example.project.domain.entities.Orders;
import com.example.project.repository.OrdersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository repository;

    public List<Orders> list() {
        return repository.findAll();
    }

	public Orders buscaPorId(Integer id) {
		return repository.findById(id).get();
	}
}