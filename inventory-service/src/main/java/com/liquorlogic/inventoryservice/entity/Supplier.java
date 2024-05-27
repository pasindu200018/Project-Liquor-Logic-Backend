package com.liquorlogic.inventoryservice.entity;

import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "ITEM_ID")
    private String item_id;

    @Column(name = "SUPPLIER_NAME")
    private String supplier_name;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "QTY_RECIEVED_ITEMS")
    private int qty_revieved_items;

    @Column(name = "QTY_RETERNED_ITEMS")
    private int qty_reterned_items;

    @Column(name = "TOTAL_QTY")
    private int total_qty;

    @Column(name = "BUYING_PRICE")
    private double buying_price;

    @Column(name = "PAYMENT")
    private double payment;

    @Column(name = "PAYMENT_METHOD")
    private String payment_method;

    @Column(name = "STATUS")
    private SupplierStatus status;

    @Column(name = "CREATE_DATE")
    private Date create;

    @Column(name = "UPDATE_DATE")
    private Date update;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stock> stocks;

}
