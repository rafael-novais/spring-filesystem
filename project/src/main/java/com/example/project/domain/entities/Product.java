package com.example.project.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="ProductName", nullable = false, columnDefinition = "nvarchar")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "SupplierId", nullable = false)
    private Supplier suplierId;

    @Column(name="UnitPrice", nullable = true, columnDefinition = "decimal")
    private Double unitPrice;

    @Column(name="Package", nullable = true, length = 40, columnDefinition = "nvarchar")
    private String pacote;

    @Column(name="IsDiscontinued", nullable = true, length = 40)
    private Boolean isDiscontinued;
   
    @Column(name="Images", nullable = true, length = 500)
    private String images;

}