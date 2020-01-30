package com.example.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;

import com.example.project.domain.entities.Product;
import com.example.project.domain.entities.Supplier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.qos.logback.classic.sift.SiftAction;

@Service
public class FileService {

    @Autowired
    ProductService productService;

    public String uploadFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get("C:\\Users\\rafael.amorim\\Pictures\\Screenshots\\teste\\" + fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/product/files/download/")
                .toUriString();

        return fileDownloadUri;
    }

    public Resource downloadFile(String file) {

        Path path = Paths.get("C:\\Users\\rafael.amorim\\Pictures\\Screenshots\\teste\\" + file);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return resource;

    }

	public String uploadExcel(MultipartFile file) {

        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                Product produto = new Product();
                Integer count = 0;

                Supplier supplier = new Supplier();

            
                if(currentRow.getRowNum() > 0) {
                    while (cellIterator.hasNext()) {

                        Cell currentCell = cellIterator.next();
                        
                        if(count == 0) System.out.println("ID ignorado com sucesso!");
                        if(count == 1) produto.setProductName(currentCell.getStringCellValue());
                        if(count == 2) {
                            Double num2 = currentCell.getNumericCellValue();
                            supplier.setId(num2.intValue());
                            produto.setSuplierId(supplier);
                        }
                        if(count == 3) produto.setUnitPrice(currentCell.getNumericCellValue());
                        if(count == 4) produto.setPacote(currentCell.getStringCellValue());
                        if(count == 5) {

                            int num = (int) currentCell.getNumericCellValue();

                            if(num == 0){
                                produto.setIsDiscontinued(false);
                            }else if(num == 1){
                                produto.setIsDiscontinued(true);
                            }

                            
                        }
                        if(count == 6) produto.setImages(currentCell.getStringCellValue());
    
                        count++;
                        
                    }
                    productService.salvar(produto);
                }
                
                count = 0;
                
                System.out.println("NOME => " + produto.getProductName());
                System.out.println("Sid => " + produto.getSuplierId());
                System.out.println("preco => " + produto.getUnitPrice());
                System.out.println("pacote => " + produto.getPacote());
                System.out.println("flag => " + produto.getIsDiscontinued());
                System.out.println("img => " + produto.getImages());
                System.out.println();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		return null;
	}

    

}