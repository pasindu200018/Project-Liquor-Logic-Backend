package com.liquorlogic.posservice.entity;


import com.liquorlogic.posservice.enums.OrderStatus;
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
@Table(name = "tbl_order")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID orderId;
    @Column(name = "ITEM_ID")
    private UUID itemId;
    @Column(name = "USER_ID")
    private UUID userId;
    @Column(name = "QTY")
    private int qty;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "TOTAL_AMOUNT")
    private Double totalAmount;
    @Column(name = "STATUS")
    private OrderStatus status;


}
