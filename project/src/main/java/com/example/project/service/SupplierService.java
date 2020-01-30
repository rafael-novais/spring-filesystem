package com.example.project.service;

import java.util.List;

import com.example.project.domain.entities.Supplier;
import com.example.project.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public List<Supplier> list() {
        return repository.findAll();
    }

	public Supplier buscaPorId(Integer id) {
		return repository.findById(id).get();
	}
}