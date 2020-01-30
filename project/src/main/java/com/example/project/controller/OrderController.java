package com.example.project.controller;

import java.util.List;

import com.example.project.domain.entities.Orders;
import com.example.project.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrdersService service;

	@GetMapping(value = "/list")
	public ResponseEntity<List<Orders>> list() {
		return ResponseEntity.ok(service.list());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Orders> buscaPorId(@RequestParam Integer id) {
		return ResponseEntity.ok(service.buscaPorId(id));
	}
}