package com.liquorlogic.posservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;


@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_item")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID itemId;
    @Column(name = "SUPPLIER_ID")
    private UUID supplierId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "BRAND")
    private String brand;
    @Column(name = "QTY")
    private int qty;
    @Column(name = "UNIT_PRICE")
    private double unitPrice;
    @Column(name = "MANUFACTURE_DATE")
    private Date manufactureDate;
    @Column(name = "EXPIRE_DATE")
    private Date expireDate;
    @Column(name = "USER_ID")
    private UUID userId;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
}
