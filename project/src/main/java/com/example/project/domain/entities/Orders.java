package com.example.project.domain.entities;

import java.util.Date;

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
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="OrderDate", nullable = false)
    private Date orderDate;

    @Column(name="OrderNumber", nullable = true, length = 10, columnDefinition = "nvarchar")
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customerId;

    @Column(name="TotalAmount", nullable = true, columnDefinition = "decimal")
    private Double totalAmount;

}