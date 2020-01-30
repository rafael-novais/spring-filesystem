package com.example.project.service;

import java.util.List;

import com.example.project.domain.entities.Product;
import com.example.project.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> list() {
        return repository.findAll();
    }

	public Product buscaPorId(Integer id) {
		return repository.findById(id).get();
	}

	public void gravarNomeDoArquivo(String nomeArquivo, Integer id) {
        Product produto = repository.findById(id).get();
        produto.setImages(nomeArquivo);

        repository.save(produto);
	}

	public String getImageBD(Integer idProduto) {

        Product produto = buscaPorId(idProduto);
        String image = produto.getImages();

		return image;
    }
    
    public Product salvar(Product produto){
        return repository.save(produto);
    }

}