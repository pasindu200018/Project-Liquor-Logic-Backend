package com.liquorlogic.inventoryservice.dto;

import com.liquorlogic.inventoryservice.enums.SupplierStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class SupplierDTO {

    private UUID id;

    private String item_id;

    private String supplier_name;

    private String contact;

    private String email;

    private int qty_revieved_items;

    private int qty_reterned_items;

    private int total_qty;

    private double buying_price;

    private double payment;

    private String payment_method;

    private SupplierStatus status;

    private Date create;

    private Date update;
}
