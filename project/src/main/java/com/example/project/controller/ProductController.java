package com.example.project.controller;

import java.util.List;

import com.example.project.domain.entities.Product;
import com.example.project.service.FileService;
import com.example.project.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private FileService fileService;

	@GetMapping(value = "/list")
	public ResponseEntity<List<Product>> list() {
		return ResponseEntity.ok(service.list());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> buscaPorId(@RequestParam Integer id) {
		return ResponseEntity.ok(service.buscaPorId(id));
	}

	@PostMapping("/upload/{idProduto}")
	public ResponseEntity uploadImagem(@RequestParam("file") MultipartFile file, @PathVariable Integer idProduto) {
		
		String enderecoDasImagens = fileService.uploadFile(file);
		String nomeDoArquivo = StringUtils.cleanPath(file.getOriginalFilename());

		service.gravarNomeDoArquivo(nomeDoArquivo, idProduto);
		
		return ResponseEntity.ok(enderecoDasImagens + idProduto);
	}

	@GetMapping("/files/download/{idProduto}")
    public ResponseEntity downloadImagem(@PathVariable Integer idProduto) {
     
		String image = service.getImageBD(idProduto);
		Resource resource = fileService.downloadFile(image);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/png"))
                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
		         .body(resource);

	}

	@PostMapping("/upload/excel")
	public ResponseEntity uploadFromExcel(@RequestParam("file") MultipartFile file) {
		
		String enderecoDasImagens = fileService.uploadExcel(file);
		String nomeDoArquivo = StringUtils.cleanPath(file.getOriginalFilename());
		
		return ResponseEntity.ok(enderecoDasImagens);
	}
	


}