package com.example.project.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="CompanyName", nullable = false, length = 50, columnDefinition = "nvarchar")
    private String companyName;

    @Column(name="ContactName", nullable = true, length = 50, columnDefinition = "nvarchar")
    private String contactName;

    @Column(name="ContactTitle", nullable = true, length = 40, columnDefinition = "nvarchar")
    private StringArrayDeserializer contactTitle;

    @Column(name="City", nullable = true, length = 40, columnDefinition = "nvarchar")
    private String city;

    @Column(name="Country", nullable = true, length = 40, columnDefinition = "nvarchar")
    private String country;

    @Column(name="Phone", nullable = true, length = 30, columnDefinition = "nvarchar")
    private String phone;

    @Column(name="Fax", nullable = true, length = 30, columnDefinition = "nvarchar")
    private String fax;

}